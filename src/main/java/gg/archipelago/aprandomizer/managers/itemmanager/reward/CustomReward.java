package gg.archipelago.aprandomizer.managers.itemmanager.reward;

import gg.archipelago.aprandomizer.APStructures;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;

public class CustomReward {
    private static final LootReward legendaryReward = new LootReward() {{
        CompoundTag durablilityNbt = new CompoundTag();
        durablilityNbt.putInt("Damage", 0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

        ItemStack manaTabletFull = new ItemStack(Objects.requireNonNull(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation("botania", "mana_tablet"))));
        CompoundTag mana_full = new CompoundTag();
        mana_full.putDouble("mana", 500000.0);
        manaTabletFull.setTag(mana_full);
        put(manaTabletFull, 1.0);
        put(new ItemStack(Items.NETHER_STAR), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "projecte", "watch_of_flowing_time")))), 1.0);

        ItemStack kitchenKnife = new ItemStack(Objects.requireNonNull(
                ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                        "mysticalagriculture", "awakened_supremium_sword"))));
        kitchenKnife.setTag(durablilityNbt);
        EnchantedBookItem.addEnchantment(kitchenKnife, new EnchantmentInstance(Enchantments.SHARPNESS, 5));
        EnchantedBookItem.addEnchantment(kitchenKnife, new EnchantmentInstance(Enchantments.MOB_LOOTING, 3));
        EnchantedBookItem.addEnchantment(kitchenKnife, new EnchantmentInstance(Enchantments.FIRE_ASPECT, 2));
        EnchantedBookItem.addEnchantment(kitchenKnife, new EnchantmentInstance(Enchantments.UNBREAKING, 3));
        EnchantedBookItem.addEnchantment(kitchenKnife, new EnchantmentInstance(Enchantments.MENDING, 1));
        put(kitchenKnife, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "compacter", "cobbler")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ad_astra", "calorite_ingot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "projecte", "dark_matter_block")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "betterfurnacesreforged", "ultimate_ore_processing_upgrade")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "flight_augment")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "gaia_spirit_seeds")))), 1.0);


        ItemStack awaknedHelmet = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "awakened_supremium_helmet"))));
        awaknedHelmet.setTag(durablilityNbt);
        put(awaknedHelmet, 1.0);

        ItemStack awaknedChestPlate = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "awakened_supremium_chestplate"))));
        awaknedHelmet.setTag(durablilityNbt);
        put(awaknedChestPlate, 1.0);

        ItemStack awakenedLeggings = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "awakened_supremium_leggings"))));
        awakenedLeggings.setTag(durablilityNbt);
        put(awakenedLeggings, 1.0);

        ItemStack awakenedBoots = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "awakened_supremium_boots"))));
        awakenedBoots.setTag(durablilityNbt);
        put(awakenedBoots, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagradditions", "insanium_farmland")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mekanism", "ultimate_tier_installer")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "minecraft", "end_portal_frame"))),12), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "extendedcrafting", "the_ultimate_ingot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "minecraft", "warden_spawn_egg")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "powah", "thermo_generator_nitro")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "projecte", "harvest_goddess_band")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "infinity_catalyst")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "infinity_ingot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "kubejs", "demon_steel_tier_1")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "sc", "gold_coin"))),64), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "draconicevolution", "awakened_core")))), 1.0);

        ItemStack dislocator = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "draconicevolution", "dislocator"))));
        dislocator.setTag(durablilityNbt);
        put(dislocator, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "infinity")))), 1.0);

        ItemStack infinityTotem = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "infinity_totem"))));
        infinityTotem.setTag(durablilityNbt);
        put(infinityTotem, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "neutron_horse_armor")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botanypotstiers", "creative_terracotta_hopper_botany_pot")))), 1.0);

        ItemStack awakenedWateringCan = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "awakened_supremium_watering_can"))));
        CompoundTag activeWater = new CompoundTag();
        activeWater.putByte("Active",(byte) 0);
        activeWater.putByte("Water",(byte) 0);
        awakenedWateringCan.setTag(activeWater);
        put(awakenedWateringCan, 1.0);

        put(new ItemStack(Items.ELDER_GUARDIAN_SPAWN_EGG), 1.0);

        put(new ItemStack(Items.PANDA_SPAWN_EGG), 1.0);

        put(new ItemStack(Items.SNIFFER_SPAWN_EGG), 1.0);

        put(new ItemStack(Items.RAVAGER_SPAWN_EGG), 1.0);

        put(new ItemStack(Items.POWDER_SNOW_BUCKET), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "reliquary", "destruction_catalyst")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pipez", "infinity_upgrade")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "constructionwand", "infinity_wand")))), 1.0);

        ItemStack acidRainProof = new ItemStack(Items.ENCHANTED_BOOK);
        EnchantedBookItem.addEnchantment(acidRainProof,new EnchantmentInstance(Objects.requireNonNull(
                ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(
                        "ad_astra_giselle_addon","acid_rain_proof"))),1));
        put(acidRainProof, 1.0);

        ItemStack gravityNormalizing = new ItemStack(Items.ENCHANTED_BOOK);
        EnchantedBookItem.addEnchantment(gravityNormalizing,new EnchantmentInstance(Objects.requireNonNull(
                ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(
                        "ad_astra_giselle_addon","gravity_normalizing"))),1));
        put(gravityNormalizing, 1.0);

        ItemStack spaceBreathing = new ItemStack(Items.ENCHANTED_BOOK);
        EnchantedBookItem.addEnchantment(spaceBreathing,new EnchantmentInstance(Objects.requireNonNull(
                ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(
                        "ad_astra_giselle_addon","space_breathing"))),1));
        put(spaceBreathing, 1.0);

        ItemStack spaceFireProof = new ItemStack(Items.ENCHANTED_BOOK);
        EnchantedBookItem.addEnchantment(spaceFireProof,new EnchantmentInstance(Objects.requireNonNull(
                ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(
                        "ad_astra_giselle_addon","space_fire_proof"))),1));
        put(spaceFireProof, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagradditions", "insanium_apple"))),64), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "extendedcrafting", "crystaltine_nugget"))),64), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "draconicevolution", "heart")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "blacker_lotus")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "sprinklerz", "blacker_lotus")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "netherite_sprinkler")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "kubejs", "chaos_shard_catalyst")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "kubejs", "chaos_dust")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "kubejs", "chaos_ball")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "kubejs", "chaos_conglomerate")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "slabmachines", "tnt_slab"))),64), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "crystal_pickaxe")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "infinity_bucket")))), 1.0);
    }};
    private static final LootReward relicReward = new LootReward() {{
        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "amphibian_boot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "aqua_walker")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "arrow_quiver")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "bastion_ring")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "blazing_flask")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "chorus_inhibitor")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "drowned_belt")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "elytra_booster")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "amphibian_boot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "enders_hand")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "holy_locket")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "horse_flute")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "hunter_belt")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "ice_breaker")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "wool_mitten")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "infinity_ham")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "jellyfish_necklace")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "leather_belt")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "magic_mirror")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "magma_walker")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "midnight_robe")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "reflection_necklace")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "roller_skates")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "shadow_glaive")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "spatial_sign")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "relics", "spore_sack")))), 1.0);
    }};
    private static final LootReward epicReward = new LootReward() {{
        CompoundTag durablilityNbt = new CompoundTag();
        durablilityNbt.putInt("Damage", 0);

        put(new ItemStack(Items.BEDROCK), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "minecraft", "bedrock")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "bigreactors", "anglesite_crystal")))), 1.0);

        put(new ItemStack(Items.AXOLOTL_BUCKET), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "bigreactors", "benitoite_crystal")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "immersiveengineering", "buzzsaw")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "exchange_rod")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "enchanted_soil")))), 1.0);

        put(new ItemStack(Items.SCULK_SHRIEKER), 1.0);

        ItemStack soulStone = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "soulstone"))));
        soulStone.setTag(durablilityNbt);
        put(soulStone, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "storage_bag")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "charm_stealthpotion")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "ender_bag")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodextended", "jambalayaitem")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ad_astra", "ostrum_ingot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ad_astra", "desh_ingot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "chancecubes", "silk_touch_pendant")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "anvil")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "disenchanter")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "altar_destruction")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "netherite_lantern")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "powah", "capacitor_nitro")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ironchests", "netherite_chest")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "extradisks", "withering_processor")))), 1.0);

        put(new ItemStack(Items.NETHERITE_BLOCK), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cobblefordays", "tier_5")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "minecraft", "bedrock")))), 1.0);

        ItemStack elytra = new ItemStack(Items.ELYTRA);
        elytra.setTag(durablilityNbt);
        put(elytra, 1.0);

        put(new ItemStack(Items.TADPOLE_BUCKET), 1.0);

        put(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE, 16), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus"))),16), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodcore", "epicbaconitem")))), 1.0);

        put(new ItemStack(Items.WARDEN_SPAWN_EGG), 1.0);

        put(new ItemStack(Items.TURTLE_HELMET), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "draconicevolution", "wyvern_core")))), 1.0);

    }};
    private static final LootReward basicReward = new LootReward() {{
        CompoundTag durablilityNbt = new CompoundTag();
        durablilityNbt.putInt("Damage", 0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mekanism", "dirty_dust_iron")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ftbquests", "book")))), 1.0);

        put(new ItemStack(Items.BLACK_CANDLE), 1.0);

        put(new ItemStack(Items.COBBLESTONE), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ironchests", "dirt_chest")))), 1.0);

        put(new ItemStack(Items.FLOWERING_AZALEA), 1.0);

        put(new ItemStack(Items.AZALEA), 1.0);

        put(new ItemStack(Items.SUSPICIOUS_SAND), 1.0);

        put(new ItemStack(Items.SUSPICIOUS_GRAVEL), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mekanismadditions", "pink_balloon")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "handcrafted", "stackable_book")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "plate_launch")))), 1.0);

        put(new ItemStack(Items.LIGHTNING_ROD), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodcore", "chickennuggetitem")))), 1.0);

        put(new ItemStack(Items.GOLD_NUGGET), 1.0);

        put(new ItemStack(Items.IRON_NUGGET), 1.0);

        put(new ItemStack(Items.DRIED_KELP), 1.0);

        put(new ItemStack(Items.GRASS_BLOCK), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "crafting_stick")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "exdeorum", "stone_watering_can")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodextended", "bakedbeansitem")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "exdeorum", "mycelium_spores")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "handcrafted", "berry_jam_jar")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "carbon_paper")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mob_grinding_utils", "mob_swab")))), 1.0);

        ItemStack  slingshot = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "supplementaries", "slingshot"))));
        slingshot.setTag(durablilityNbt);
        put(slingshot, 1.0);

        ItemStack bubbleBlower = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "supplementaries", "bubble_blower"))));
        bubbleBlower.setTag(durablilityNbt);
        put(bubbleBlower, 1.0);

        put(new ItemStack(Items.DAMAGED_ANVIL), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "cosmetic_cat_ears")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "cosmetic_groucho_glasses")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "cosmetic_googly_eyes")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "cosmetic_black_tie")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "cosmetic_alien_antenna")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "cosmetic_thick_eyebrows")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "cosmetic_four_leaf_clover")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "cosmetic_tiny_potato_mask")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "cosmetic_eerie_mask")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "supplementaries", "key")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "usefulslime", "jello")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "infinity_nugget")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "supplementaries", "blacker_lotus")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "wind_vane")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "draconicevolution", "medium_chaos_frag")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "redstone_arsenal", "blacker_lotus")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "flux_metal_block")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "redstone_arsenal", "flux_gem_block")))), 1.0);

    }};
    private static final LootReward artifactReward = new LootReward() {{

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "kitty_slippers")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "pocket_piston")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "crystal_heart")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "superstitious_hat")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "power_glove")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "feral_claws")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "bunny_hoppers")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "fire_gauntlet")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "blacker_lotus")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "panic_necklace")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "lucky_scarf")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "thorn_pendant")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "golden_hook")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "reliquary", "holy_hand_grenade")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "novelty_drinking_hat")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "night_vision_goggles")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "cloud_in_a_bottle")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "steadfast_spikes")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "helium_flamingo")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "umbrella")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "flame_pendant")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "eternal_steak")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "cross_necklace")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "antidote_vessel")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "obsidian_skull")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "running_shoes")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "artifacts", "digging_claws")))), 1.0);
    }};
    private static final LootReward greaterReward = new LootReward() {{
        CompoundTag durablilityNbt = new CompoundTag();
        durablilityNbt.putInt("Damage", 0);

        put(new ItemStack(Items.REINFORCED_DEEPSLATE), 1.0);

        ItemStack soulJar = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "soul_jar"))));
        CompoundTag fishSoul = new CompoundTag();
        fishSoul.putDouble("Souls", 6.0);
        fishSoul.putString("Type", "mysticalagriculture:fish");
        soulJar.setTag(fishSoul);
        put(soulJar, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderio", "enderman_head")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pneumaticcraft", "nuke_virus")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "heart")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "heart_empty")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "betterfurnacesreforged", "copper_upgrade")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "crate_mini")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "industrialforegoing", "mob_imprisonment_tool")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "handcrafted", "creeper_trophy")))), 1.0);

        ItemStack fuelEfficencyUpgrade = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "betterfurnacesreforged", "fuel_efficiency_upgrade"))));
        fuelEfficencyUpgrade.setTag(durablilityNbt);
        put(fuelEfficencyUpgrade, 1.0);

        ItemStack oreProcessingUpgrade = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "betterfurnacesreforged", "ore_processing_upgrade"))));
        oreProcessingUpgrade.setTag(durablilityNbt);
        put(oreProcessingUpgrade, 1.0);

        ItemStack ironDolly = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ironchests", "iron_dolly"))));
        ironDolly.setTag(durablilityNbt);
        put(ironDolly, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "black_lotus")))), 1.0);

        put(new ItemStack(Items.LODESTONE), 1.0);

        put(new ItemStack(Items.RESPAWN_ANCHOR), 1.0);

        put(new ItemStack(Items.CONDUIT), 1.0);

        put(new ItemStack(Items.BELL), 1.0);

        put(new ItemStack(Items.END_CRYSTAL), 1.0);

        put(new ItemStack(Items.TOTEM_OF_UNDYING), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodextended", "friedriceitem")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodextended", "chocolatesprinklecakeitem")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderio", "frank_n_zombie")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderio", "sentient_ender")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mekanismadditions", "obsidian_tnt")))), 1.0);

        ItemStack hangGlider = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "hangglider", "hang_glider"))));
        hangGlider.setTag(durablilityNbt);
        put(hangGlider, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "redstone_arsenal", "flux_gem_block")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "redstone_arsenal", "flux_metal_block")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "redstone_arsenal", "flux_boots")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "redstone_arsenal", "flux_leggings")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "redstone_arsenal", "flux_helmet")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "redstone_arsenal", "blacker_lotus")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "extendedcrafting", "nether_star_block")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "betterfurnacesreforged", "extreme_upgrade")))), 1.0);

        ItemStack compressedNetheriteHammer = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "exdeorum", "compressed_netherite_hammer"))));
        compressedNetheriteHammer.setTag(durablilityNbt);
        put(compressedNetheriteHammer, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "kubejs", "demon_steel_tier_1")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "draconicevolution", "draconium_core")))), 1.0);

    }};
    private static final LootReward goodReward = new LootReward() {{

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "handcrafted", "oak_couch")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "handcrafted", "oak_fancy_bed")))), 1.0);

        put(new ItemStack(Items.CAULDRON), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "fan")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "elevatorid", "elevator_white")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pneumaticcraft", "lubricant_bucket")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderio", "enderios")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pneumaticcraft", "nuke_virus")))), 1.0);

        put(new ItemStack(Items.CHICKEN_SPAWN_EGG), 1.0);

        put(new ItemStack(Items.COW_SPAWN_EGG), 1.0);

        put(new ItemStack(Items.PIG_SPAWN_EGG), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

        put(new ItemStack(Items.SHEEP_SPAWN_EGG), 1.0);

        put(new ItemStack(Items.OBSERVER), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mekanismgenerators", "turbine_blade")))), 1.0);

        put(new ItemStack(Items.ANVIL), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mekanism", "blacker_lotus")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodextended", "hushpuppiesitem")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodextended", "banananutbreaditem")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderio", "zombie_electrode")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderio", "ender_resonator")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderio", "z_logic_controller")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderio", "skeletal_contractor")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderio", "guardian_diode")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "thermal", "ruby_gear")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "thermal", "sapphire_gear")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "thermal", "rose_gold_gear")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "thermal", "enderium_gear")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagradditions", "nether_star_shard")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "neutron_pile"))),8), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "betterfurnacesreforged", "raw_ore_processing_upgrade")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pneumaticcraft", "empty_pcb")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mekanism", "heavy_water_bucket")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "bigreactors", "verderium_bucket")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "cognizant_dust")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "usefulslime", "jello")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "appbot", "fluix_mana_pool")))), 1.0);

        put(new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ad_astra", "desh_ingot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ad_astra", "ostrum_ingot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ad_astra", "calorite_ingot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "crystal_matrix_ingot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "supplementaries", "hourglass")))), 1.0);

        put(new ItemStack(Items.LODESTONE), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "supplementaries", "slingshot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "usefulslime", "slime_sling")))), 1.0);

        put(new ItemStack(Items.GOAT_HORN), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ironchests", "diamond_dolly")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "naturescompass", "naturescompass")))), 1.0);

        ItemStack spaceBreathing = new ItemStack(Items.ENCHANTED_BOOK);
        EnchantedBookItem.addEnchantment(spaceBreathing,new EnchantmentInstance(Objects.requireNonNull(
                ForgeRegistries.ENCHANTMENTS.getValue(new ResourceLocation(
                        "ad_astra_giselle_addon","space_breathing"))),1));
        put(spaceBreathing, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "reliquary", "twilight_cloak")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "twilightforest", "magic_beans")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "farmingforblockheads", "chicken_nest")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "betterfurnacesreforged", "color_upgrade")))), 1.0);
    }};
    private static final LootReward randomReward = new LootReward() {{
        CompoundTag durablilityNbt = new CompoundTag();
        durablilityNbt.putInt("Damage", 0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ftbquests", "book")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "chancecubes", "chance_cube")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "chancecubes", "compact_giant_chance_cube")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "black_lotus")))), 1.0);

        put(new ItemStack(Items.NETHER_STAR), 1.0);

        put(new ItemStack(Items.DIRT), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "pump")))), 1.0);

        put(new ItemStack(Items.CALIBRATED_SCULK_SENSOR), 1.0);

        put(new ItemStack(Items.LECTERN), 1.0);

        put(new ItemStack(Items.DAYLIGHT_DETECTOR), 1.0);

        put(new ItemStack(Items.REDSTONE_BLOCK), 1.0);

        put(new ItemStack(Items.TNT), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "generator_solar")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodcore", "pumpkinbreaditem")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodcore", "carrotcakeitem")))), 1.0);

        put(new ItemStack(Items.BLACK_GLAZED_TERRACOTTA), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodcore", "carrotsoupitem")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodcore", "caramelappleitem")))), 1.0);

        put(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE), 1.0);

        put(new ItemStack(Items.GOLDEN_CARROT), 1.0);

        put(new ItemStack(Items.CHORUS_FRUIT), 1.0);

        put(new ItemStack(Items.PUFFERFISH), 1.0);

        put(new ItemStack(Items.SUSPICIOUS_STEW), 1.0);

        put(new ItemStack(Items.DIAMOND_HORSE_ARMOR), 1.0);

        put(new ItemStack(Items.SNOWBALL), 1.0);

        ItemStack refinedObsidianSword = new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mekanismtools", "refined_obsidian_sword"))));
        refinedObsidianSword.setTag(durablilityNbt);
        put(refinedObsidianSword, 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "aether", "candy_cane_sword")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "infinity_catalyst_essence")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "neutron_ingot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ae2", "singularity")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "spark")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botanypotstiers", "elite_terracotta_hopper_botany_pot")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "immersiveengineering", "chemthrower")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "immersiveengineering", "railgun")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "skull_fire_sword")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "infinity_nugget")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "draconicevolution", "dragon_heart")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "compressed_chest")))), 1.0);

    }};
    private static final LootReward decentReward = new LootReward() {{

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "pamhc2foodextended", "pizzasliceitem")))), 1.0);

        put(new ItemStack(Items.GLOW_INK_SAC), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderio", "cake_base")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "hopper")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mob_grinding_utils", "mob_swab")))), 1.0);

        put(new ItemStack(Items.DRIPSTONE_BLOCK), 1.0);

        put(new ItemStack(Items.POINTED_DRIPSTONE), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "refinedstorage", "raw_advanced_processor")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "refinedstorage", "raw_basic_processor")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "refinedstorage", "raw_improved_processor")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "starfield")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mekanismgenerators", "turbine_blade")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "prosperity_block")))), 1.0);

        put(new ItemStack(Items.TORCHFLOWER), 1.0);

        put(new ItemStack(Items.PITCHER_PLANT), 1.0);

        put(new ItemStack(Items.DISC_FRAGMENT_5), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "lunchbox")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagriculture", "infusion_crystal")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "torch_launcher")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "chunkloaders", "single_chunk_loader")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "no_soliciting")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "undergarden", "gloom_o_lantern")))), 1.0);

        put(new ItemStack(Items.BREWING_STAND), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "workbench")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "chancecubes", "scanner")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "sleeping_mat")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "handcrafted", "white_cup")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "anvil_magma")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "industrialforegoing", "mob_imprisonment_tool")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "avaritia", "neutron_pile")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mysticalagradditions", "nether_star_shard")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ae2", "printed_calculation_processor")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ae2", "printed_engineering_processor")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "ae2", "printed_logic_processor")))), 1.0);

        put(new ItemStack(Items.BEE_NEST), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "entropinnyum")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "orechid")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "enderstorage", "ender_chest")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "crate")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "anvil_void")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "doorbell")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "chancecubes", "scanner")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "thermal", "lightning_tnt")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "mob_grinding_utils", "solid_xp_baby"))),64), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "water_candle")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "cyclic", "sponge_lava")))), 1.0);

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

    }};
    private static final LootReward okayReward = new LootReward() {{

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

    }};
    private static final HashMap<Long, LootReward> lootRewardList = new HashMap<>() {{
        put(45053L, legendaryReward);
        put(45054L, relicReward);
        put(45055L, epicReward);
        put(45056L, basicReward);
        put(45057L, artifactReward);
        put(45058L, greaterReward);
        put(45059L, goodReward);
        put(45060L, randomReward);
        put(45061L, decentReward);
        put(45062L, okayReward);
    }};

    public static boolean hasLootReward(Long itemId) {
        return lootRewardList.containsKey(itemId);
    }

    public static LootReward getLootReward(Long itemId) {
        return lootRewardList.get(itemId);
    }
}

