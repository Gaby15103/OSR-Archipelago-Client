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
                "botania", "blacker_lotus")))), 1.0);

    }};
    private static final LootReward epicReward = new LootReward() {{

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

    }};
    private static final LootReward basicReward = new LootReward() {{

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

    }};
    private static final LootReward artifactReward = new LootReward() {{

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

    }};
    private static final LootReward greaterReward = new LootReward() {{

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

    }};
    private static final LootReward goodReward = new LootReward() {{

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

    }};
    private static final LootReward randomReward = new LootReward() {{

        put(new ItemStack(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(new ResourceLocation(
                "botania", "blacker_lotus")))), 1.0);

    }};
    private static final LootReward decentReward = new LootReward() {{

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

