package gg.archipelago.aprandomizer.modifiers;

import com.mojang.serialization.Codec;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.ap.storage.APMCData;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.world.ModifiableStructureInfo;
import net.minecraftforge.common.world.StructureModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.util.HashMap;
import java.util.Map;

public class APStructureModifier implements StructureModifier {

    public static HolderSet<Biome> overworldStructures;
    public static HolderSet<Biome> netherStructures;
    public static HolderSet<Biome> endStructures;
    public static HolderSet<Biome> noBiomes;

    public static HashMap<String, HolderSet<Biome>> structures = new HashMap<>();

    public APStructureModifier() {
    }

    public static void loadTags() {
        if (!structures.isEmpty()) return;
        if (APRandomizer.getApmcData().state == APMCData.State.MISSING) {
            APRandomizer.LOGGER.error("APMCData is missing, cannot load tags.");
            return;
        }
        APRandomizer.LOGGER.info("Loading Tags and Biome info.");

        Registry<Biome> biomeRegistry = ServerLifecycleHooks.getCurrentServer().registryAccess().registryOrThrow(Registries.BIOME);

        //get structure biome holdersets.
        TagKey<Biome> overworldTag = TagKey.create(Registries.BIOME, new ResourceLocation("aprandomizer","overworld_structure"));
        overworldStructures = biomeRegistry.getTag(overworldTag).orElseThrow();

        TagKey<Biome> netherTag = TagKey.create(Registries.BIOME, new ResourceLocation("aprandomizer","nether_structure"));
        netherStructures = biomeRegistry.getTag(netherTag).orElseThrow();

        TagKey<Biome> endTag = TagKey.create(Registries.BIOME, new ResourceLocation("aprandomizer","end_structure"));
        endStructures = biomeRegistry.getTag(endTag).orElseThrow();

        TagKey<Biome> noneTag = TagKey.create(Registries.BIOME, new ResourceLocation("aprandomizer","none"));
        noBiomes = biomeRegistry.getTag(noneTag).orElseThrow();

        APMCData data = APRandomizer.getApmcData();
        for (Map.Entry<String, String> entry : data.structures.entrySet()) {
            switch (entry.getKey()) {
                case "Overworld Structure 1", "Overworld Structure 2" ->
                        structures.put(entry.getValue(), APStructureModifier.overworldStructures);
                case "Nether Structure 1", "Nether Structure 2" ->
                        structures.put(entry.getValue(), APStructureModifier.netherStructures);
                case "The End Structure" ->
                        structures.put(entry.getValue(), APStructureModifier.endStructures);
            }
        }
    }

    public static final DeferredRegister<Codec<? extends StructureModifier>> structureModifiers = DeferredRegister.create(ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, APRandomizer.MODID);
    private static final RegistryObject<Codec<? extends StructureModifier>> SERIALIZER = RegistryObject.create(new ResourceLocation(APRandomizer.MODID, "ap_structure_modifier"), ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, APRandomizer.MODID);
    @Override
    public void modify(Holder<Structure> structure, Phase phase, ModifiableStructureInfo.StructureInfo.Builder builder) {
        if (!phase.equals(Phase.MODIFY) || structure.unwrapKey().isEmpty()) return;
        if (APRandomizer.getApmcData().state == APMCData.State.MISSING) {
            APRandomizer.LOGGER.error("APMCData is missing, cannot modify structures.");
            return;
        }
        if (structures.isEmpty()) loadTags();
        APRandomizer.LOGGER.debug("Altering biome list for " + structure.unwrapKey().get().location());

        HolderSet<Biome> biomes = structure.get().biomes();

        switch (structure.unwrapKey().get().location().toString()) {
            case "minecraft:village_plains", "minecraft:village_desert", "minecraft:village_savanna", "minecraft:village_snowy", "minecraft:village_taiga" -> {
                if (!structures.get("Village").equals(overworldStructures))
                    biomes = noBiomes;
            }
            case "aprandomizer:village_nether" -> {
                if (!structures.get("Village").equals(overworldStructures))
                    biomes = structures.get("Village");
            }
            case "minecraft:pillager_outpost" -> {
                if (!structures.get("Pillager Outpost").equals(overworldStructures))
                    biomes = noBiomes;
            }
            case "aprandomizer:pillager_outpost_nether" -> {
                if (!structures.get("Pillager Outpost").equals(overworldStructures))
                    biomes = structures.get("Pillager Outpost");
            }
            case "minecraft:fortress" -> biomes = structures.get("Nether Fortress");
            case "minecraft:bastion_remnant" -> biomes = structures.get("Bastion Remnant");
            case "minecraft:end_city" -> {
                if (structures.get("End City").equals(netherStructures))
                    biomes = noBiomes;
                else if (!structures.get("End City").equals(endStructures))
                    biomes = structures.get("End City");
            }
            case "aprandomizer:end_city_nether" -> {
                if (structures.get("End City").equals(netherStructures))
                    biomes = structures.get("End City");
            }
        }

        builder.getStructureSettings().setBiomes(biomes);
    }

    @Override
    public Codec<? extends StructureModifier> codec() {
        return SERIALIZER.get();
    }

    public static Codec<APStructureModifier> makeCodec() {
        return Codec.unit(APStructureModifier::new);
    }
}

