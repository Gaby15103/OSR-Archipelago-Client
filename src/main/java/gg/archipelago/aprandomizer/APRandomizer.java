package gg.archipelago.aprandomizer;

import com.google.gson.Gson;
import dev.koifysh.archipelago.network.client.BouncePacket;
import gg.archipelago.aprandomizer.ap.APClient;
import gg.archipelago.aprandomizer.ap.storage.APMCData;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import gg.archipelago.aprandomizer.data.WorldData;
import gg.archipelago.aprandomizer.managers.GoalManager;
import gg.archipelago.aprandomizer.managers.advancementmanager.AdvancementManager;
import gg.archipelago.aprandomizer.managers.Itemmanager.ItemManager;
import gg.archipelago.aprandomizer.managers.questManager.QuestManager;
import gg.archipelago.aprandomizer.managers.recipemanager.RecipeManager;
import gg.archipelago.aprandomizer.managers.teammanager.TeamHelper;
import gg.archipelago.aprandomizer.modifiers.APStructureModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(APRandomizer.MODID)
public class APRandomizer {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "aprandomizer";

    //store our APClient
    static private APClient APClient;

    static public MinecraftServer server;
    static private AdvancementManager advancementManager;
    static private QuestManager questManager;
    static private TeamHelper teamHelper;
    static private RecipeManager recipeManager;
    static private ItemManager itemManager;
    static private GoalManager goalManager;
    static private APMCData apmcData;
    static private final Set<Integer> validVersions = new HashSet<>() {{
        this.add(9); //mc 1.19
    }};
    static private boolean jailPlayers = true;
    static private BlockPos jailCenter = BlockPos.ZERO;
    static private WorldData worldData;
    static private double lastDeathTimestamp;
    static private String apmcFileName;

    public APRandomizer() {
        LOGGER.info("Minecraft Ozone SkyBlock Reborn Archipelago 1.20.1 v0.1.2 Randomizer initializing...");

        // Register ourselves for server and other game events we are interested in
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.register(this);

        Gson gson = new Gson();
        try {
            Path path = Paths.get("./APData/");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                LOGGER.info("APData folder missing, creating.");
            }

            File[] files = new File(path.toUri()).listFiles((d, name) -> name.endsWith(".apmc"));
            assert files != null;
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));
            apmcFileName = files[0].getName();
            String b64 = Files.readAllLines(files[0].toPath()).get(0);
            String json = new String(Base64.getDecoder().decode(b64));
            apmcData = gson.fromJson(json, APMCData.class);
            if (!validVersions.contains(apmcData.client_version)) {
                apmcData.state = APMCData.State.INVALID_VERSION;
            }
            //LOGGER.info(apmcData.structures.toString());
        } catch (IOException | NullPointerException | ArrayIndexOutOfBoundsException | AssertionError e) {
            LOGGER.error("no .apmc file found. please place .apmc file in './APData/' folder.");
            if (apmcData == null) {
                apmcData = new APMCData();
                apmcData.state = APMCData.State.MISSING;
            }
        }

        // For registration and init stuff.
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        APStructures.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);
        APStructureModifier.structureModifiers.register(modEventBus);
        APStructureModifier.structureModifiers.register("ap_structure_modifier",APStructureModifier::makeCodec);
    }

    public static APClient getAP() {
        return APClient;
    }

    public static boolean isConnected() {
        return (APClient != null && APClient.isConnected());
    }

    public static AdvancementManager getAdvancementManager() {
        return advancementManager;
    }

    public static QuestManager getQuestManager(){ return questManager; }
    public static TeamHelper getTeamHelper(){ return teamHelper; }

    public static RecipeManager getRecipeManager() {
        return recipeManager;
    }

    public static APMCData getApmcData() {
        return apmcData;
    }

    public static MinecraftServer getServer() {
        return server;
    }

    public static ItemManager getItemManager() {
        return itemManager;
    }

    public static Set<Integer> getValidVersions() {
        return validVersions;
    }


    public static boolean isJailPlayers() {
        return jailPlayers;
    }

    public static void setJailPlayers(boolean jailPlayers) {
        APRandomizer.jailPlayers = jailPlayers;
        worldData.setJailPlayers(jailPlayers);
    }

    public static BlockPos getJailPosition() {
        return jailCenter;
    }

    public static boolean isRace() {
        return getApmcData().race;
    }

    public static void sendBounce(BouncePacket packet) {
        if(APClient != null)
            APClient.sendBounce(packet);
    }

    public static GoalManager getGoalManager() {
        return goalManager;
    }

    public static void setLastDeathTimestamp(double deathTime) {
        lastDeathTimestamp = deathTime;
    }

    public static double getLastDeathTimestamp() {
        return lastDeathTimestamp;
    }

    public static WorldData getWorldData() {return worldData;}

    public static void saveAPMCData() {
        Gson gson = new Gson();
        try {
            Path path = Paths.get("./APData/");
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            // Encode the APMCData object to JSON
            String json = gson.toJson(apmcData);
            // Encode JSON to Base64
            String b64 = Base64.getEncoder().encodeToString(json.getBytes());

            // Save it to the file
            Path filePath = path.resolve(apmcFileName);
            Files.write(filePath, Collections.singletonList(b64));
            LOGGER.info("APMC data saved successfully.");
        } catch (IOException e) {
            LOGGER.error("Failed to save APMC data: " + e.getMessage());
        }
    }


    @SubscribeEvent
    public void onServerAboutToStart(ServerAboutToStartEvent event) {
        if (apmcData.state != APMCData.State.VALID) {
            LOGGER.error("invalid APMC file");
        }
        server = event.getServer();
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        if (apmcData.state == APMCData.State.MISSING) {
            LOGGER.error("NO APMC FILE FOUND. PLEASE PLACE A VALID APMC FILE IN THE APDATA FOLDER.");
            return;
        }
        advancementManager = new AdvancementManager();
        questManager = new QuestManager();
        teamHelper = new TeamHelper();
        recipeManager = new RecipeManager();
        itemManager = new ItemManager();
        goalManager = new GoalManager();

        server.getGameRules().getRule(GameRules.RULE_LIMITED_CRAFTING).set(true, server);
        server.getGameRules().getRule(GameRules.RULE_KEEPINVENTORY).set(true, server);
        server.getGameRules().getRule(GameRules.RULE_ANNOUNCE_ADVANCEMENTS).set(false, server);
        server.setDifficulty(Difficulty.NORMAL, true);
        //fetch our custom world save data we attach to the worlds.
        worldData = WorldData.initialize(server.overworld().getDataStorage());
        jailPlayers = worldData.getJailPlayers();
        //advancementManager.setCheckedAdvancements(worldData.getLocations());
        questManager.setCheckedQuests(worldData.getLocations());

        //check if APMC data is present and if the seed matches what we expect
        if (apmcData.state == APMCData.State.VALID && !worldData.getSeedName().equals(apmcData.seed_name)) {
            //check to see if our worlddata is empty if it is then save the aproom data.
            if (worldData.getSeedName().isEmpty()) {
                worldData.setSeedName(apmcData.seed_name);
                //this is also our first boot so set this flag so we can do first boot stuff.
            }
            else {
                apmcData.state = APMCData.State.INVALID_SEED;
            }
        }

        //if no apmc file was found set our world data seed to invalid so it will force a regen of this blank world.
        if (apmcData.state == APMCData.State.MISSING) {
            worldData.setSeedName("Invalid");
        }

        if(apmcData.state == APMCData.State.VALID) {
            APClient = new APClient(server);
        }


        if(jailPlayers) {
            ServerLevel overworld = server.getLevel(Level.OVERWORLD);
            BlockPos spawn = overworld.getSharedSpawnPos();
            // alter the spawn box position, so it doesn't interfere with spawning
            StructureTemplate jail = overworld.getStructureManager().get(new ResourceLocation(MODID,"spawnjail")).get();
            BlockPos jailPos = new BlockPos(spawn.getX()+5, 300, spawn.getZ()+5);
            jailCenter = new BlockPos(jailPos.getX() + (jail.getSize().getX()/2),jailPos.getY() + 1, jailPos.getZ() + (jail.getSize().getZ()/2));
            jail.placeInWorld(overworld,jailPos,jailPos,new StructurePlaceSettings(), RandomSource.create(),2);
            server.getGameRules().getRule(GameRules.RULE_DAYLIGHT).set(false, server);
            server.getGameRules().getRule(GameRules.RULE_WEATHER_CYCLE).set(false, server);
            server.getGameRules().getRule(GameRules.RULE_DOFIRETICK).set(false, server);
            server.getGameRules().getRule(GameRules.RULE_RANDOMTICKING).set(0, server);
            server.getGameRules().getRule(GameRules.RULE_DO_PATROL_SPAWNING).set(false,server);
            server.getGameRules().getRule(GameRules.RULE_DO_TRADER_SPAWNING).set(false,server);
            server.getGameRules().getRule(GameRules.RULE_MOBGRIEFING).set(false,server);
            server.getGameRules().getRule(GameRules.RULE_DOMOBSPAWNING).set(false,server);
            server.getGameRules().getRule(GameRules.RULE_DO_IMMEDIATE_RESPAWN).set(true,server);
            server.getGameRules().getRule(GameRules.RULE_DOMOBLOOT).set(false,server);
            server.getGameRules().getRule(GameRules.RULE_DOENTITYDROPS).set(false,server);
            overworld.setDayTime(0);

        }

        if (apmcData.state == APMCData.State.VALID && apmcData.server != null) {

            APClient APClient = APRandomizer.getAP();
            APClient.setName(apmcData.player_name);
            String address = apmcData.server.concat(":" + apmcData.port);

            Utils.sendMessageToAll("Connecting to Archipelago server at " + address);

            try {
                APClient.connect(address);
            } catch (URISyntaxException e) {
                Utils.sendMessageToAll("unable to connect");
            }
        }
    }
    @SubscribeEvent
    public void onServerStarted(ServerStartedEvent event){
        questManager.initializeQuests();
    }

    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event) {
        if(APClient != null)
            APClient.close();
    }

    @SubscribeEvent
    public void onServerStopped(ServerStoppedEvent event) {
        if(APClient != null)
            APClient.close();
    }
}
