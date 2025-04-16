package gg.archipelago.aprandomizer.managers.recipemanager;

import gg.archipelago.aprandomizer.common.Utils.Utils;
import net.minecraft.world.item.crafting.Recipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RecipeData {

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    HashMap<Long, ProgressiveRecipe> progressiveRecipes = new HashMap<>() {};
    HashMap<Long, GroupRecipe> recipes = new HashMap<>() {{
        put(45000L, new GroupRecipe(45000, "iron_ingot", new String[]{
                "minecraft:iron_ingot_from_nuggets",
                "projecte:conversions/gold_ingot_to_iron_ingot",
                "exdeorum:iron_ore",
                "immersiveengineering:crafting/raw_hammercrushing_iron",
                "immersiveengineering:crafting/hammercrushing_iron",
                "minecraft:iron_ingot_from_iron_block",
                "hostilenetworks:living_matter/overworldian/iron_ingot",
                "elementaryores:ore_iron_blasting",
                "crafttweaker:iron_ingot_from_smelting_iron_ore",
                "enderio:smelting/minecraft/iron_ingot_from_smelting_deepslate_iron_ore",
                "crafttweaker:iron_ingot_from_smelting_iron_ore",
                "immersiveengineering:arcfurnace/ore_iron",
                "thermal:furnace_885540169",
                "thermal:machines/smelter/smelter_iron_ore",
                "mekanism:processing/iron/ingot/from_dust_blasting",
                "mekanism:processing/iron/ingot/from_dust_smelting",
                "enderio:smelting/mekanism/processing/iron/ingot/from_dust_smelting",
                "mekanism:processing/iron/ingot/from_dust_smelting",
                "immersiveengineering:arcfurnace/dust_iron",
                "thermal:furnace_943886073",
                "thermal:machines/smelter/smelter_iron_dust",
                "minecraft:iron_ingot_from_blasting_raw_iron",
                "minecraft:iron_ingot_from_smelting_raw_iron",
                "enderio:smelting/minecraft/iron_ingot_from_smelting_raw_iron",
                "minecraft:iron_ingot_from_smelting_raw_iron",
                "immersiveengineering:arcfurnace/raw_block_iron",
                "immersiveengineering:arcfurnace/raw_ore_iron",
                "thermal:furnace_1409496459",
                "thermal:machines/smelter/smelter_raw_iron"
        }));
        put(45001L, new GroupRecipe(45001, "gold_ingot", new String[]{
                "projecte:conversions/iron_ingot_to_gold_ingot",
                "minecraft:gold_ingot_from_nuggets",
                "projecte:conversions/diamond_to_gold_ingot",
                "minecraft:gold_ingot_from_gold_block",
                "hostilenetworks:living_matter/hellish/gold_ingot",
                "immersiveengineering:crafting/raw_hammercrushing_gold",
                "immersiveengineering:crafting/hammercrushing_gold",
                "exdeorum:gold_ore",
                "mekanism:processing/gold/ingot/from_dust_blasting",
                "minecraft:gold_ingot_from_blasting_raw_gold",
                "minecraft:gold_ingot_from_blasting_deepslate_gold_ore",
                "minecraft:gold_ingot_from_smelting_deepslate_gold_ore",
                "mekanism:processing/gold/ingot/from_dust_smelting",
                "minecraft:gold_ingot_from_smelting_raw_gold",
                "enderio:smelting/mekanism/processing/gold/ingot/from_dust_smelting",
                "enderio:smelting/minecraft/gold_ingot_from_smelting_deepslate_gold_ore",
                "enderio:smelting/minecraft/gold_ingot_from_smelting_raw_gold",
                "minecraft:gold_ingot_from_smelting_deepslate_gold_ore",
                "mekanism:processing/gold/ingot/from_dust_smelting",
                "minecraft:gold_ingot_from_smelting_raw_gold",
                "immersiveengineering:arcfurnace/raw_block_gold",
                "immersiveengineering:arcfurnace/ore_gold",
                "immersiveengineering:arcfurnace/dust_gold",
                "immersiveengineering:arcfurnace/raw_ore_gold",
                "thermal:furnace_156789559",
                "thermal:furnace_96913262",
                "thermal:furnace_81060396",
                "thermal:machines/smelter/smelter_gold_dust",
                "thermal:machines/smelter/smelter_raw_gold",
                "thermal:machines/smelter/smelter_gold_ore"
        }));
        put(45002L, new GroupRecipe(45002, "aluminium_ingot", new String[]{
                "immersiveengineering:crafting/nugget_aluminum_to_ingot_aluminum",
                "immersiveengineering:crafting/storage_aluminum_to_ingot_aluminum",
                "immersiveengineering:crafting/hammercrushing_aluminum",
                "immersiveengineering:crafting/raw_hammercrushing_aluminum",
                "exdeorum:ores/aluminum_tag",
                "crafttweaker:aluminum_ore",
                "immersiveengineering:smelting/ingot_aluminum_from_blasting",
                "immersiveengineering:smelting/ingot_aluminum_from_blasting3",
                "immersiveengineering:smelting/ingot_aluminum_from_dust_from_blasting",
                "immersiveengineering:smelting/ingot_aluminum3",
                "immersiveengineering:smelting/ingot_aluminum",
                "immersiveengineering:smelting/ingot_aluminum_from_dust",
                "enderio:smelting/immersiveengineering/smelting/ingot_aluminum_from_dust",
                "enderio:smelting/immersiveengineering/smelting/ingot_aluminum",
                "immersiveengineering:smelting/ingot_aluminum3",
                "immersiveengineering:smelting/ingot_aluminum",
                "immersiveengineering:smelting/ingot_aluminum",
                "immersiveengineering:smelting/ingot_aluminum_from_dust",
                "immersiveengineering:arcfurnace/ore_aluminum",
                "immersiveengineering:arcfurnace/raw_ore_aluminum",
                "immersiveengineering:arcfurnace/raw_block_aluminum",
                "immersiveengineering:arcfurnace/dust_aluminum",
                "thermal:furnace_1879446726",
                "thermal:furnace_1686756378",
                "thermal:furnace_1456483294",
                "thermal:compat/immersiveengineering/smelter_ie_raw_aluminum",
                "thermal:compat/immersiveengineering/smelter_ie_aluminum_ore",
                "thermal:compat/immersiveengineering/smelter_ie_aluminum_dust"
        }));
        put(45003L, new GroupRecipe(45003, "silver_ingot", new String[]{
                "immersiveengineering:crafting/storage_silver_to_ingot_silver",
                "immersiveengineering:crafting/nugget_silver_to_ingot_silver",
                "immersiveengineering:crafting/raw_hammercrushing_silver",
                "immersiveengineering:crafting/hammercrushing_silver",
                "crafttweaker:silver_ore",
                "immersiveengineering:smelting/ingot_silver_from_dust_from_blasting",
                "immersiveengineering:smelting/ingot_silver_from_blasting3",
                "immersiveengineering:smelting/ingot_silver_from_blasting",
                "immersiveengineering:smelting/ingot_silver_from_dust",
                "immersiveengineering:smelting/ingot_silver",
                "immersiveengineering:smelting/ingot_silver3",
                "enderio:smelting/immersiveengineering/smelting/ingot_silver_from_dust",
                "enderio:smelting/immersiveengineering/smelting/ingot_silver3",
                "immersiveengineering:smelting/ingot_silver_from_dust",
                "immersiveengineering:smelting/ingot_silver",
                "immersiveengineering:smelting/ingot_silver3",
                "immersiveengineering:arcfurnace/raw_ore_silver",
                "immersiveengineering:arcfurnace/ore_silver",
                "immersiveengineering:arcfurnace/dust_silver",
                "immersiveengineering:arcfurnace/raw_block_silver",
                "thermal:furnace_1760489455",
                "thermal:furnace_1423244119",
                "thermal:furnace_890272032",
                "thermal:machines/smelter/smelter_silver_ore",
                "thermal:machines/smelter/smelter_lead_ore",
                "thermal:machines/smelter/smelter_silver_dust",
                "thermal:machines/smelter/smelter_raw_silver"
        }));
        put(45004L, new GroupRecipe(45004, "lead_ingot", new String[]{
                "immersiveengineering:crafting/storage_lead_to_ingot_lead",
                "immersiveengineering:crafting/nugget_lead_to_ingot_lead",
                "exdeorum:ores/lead_tag",
                "immersiveengineering:crafting/hammercrushing_lead",
                "immersiveengineering:crafting/raw_hammercrushing_lead",
                "immersiveengineering:smelting/ingot_lead_from_blasting3",
                "mekanism:processing/lead/ingot/from_dust_blasting",
                "thermal:smelting/lead_ingot_from_deepslate_ore_blasting",
                "thermal:smelting/lead_ingot_from_deepslate_ore_smelting",
                "mekanism:processing/lead/ingot/from_dust_smelting",
                "immersiveengineering:smelting/ingot_lead3",
                "enderio:smelting/thermal/smelting/lead_ingot_from_deepslate_ore_smelting",
                "enderio:smelting/mekanism/processing/lead/ingot/from_dust_smelting",
                "enderio:smelting/immersiveengineering/smelting/ingot_lead3",
                "thermal:smelting/lead_ingot_from_deepslate_ore_smelting",
                "mekanism:processing/lead/ingot/from_dust_smelting",
                "immersiveengineering:smelting/ingot_lead3",
                "immersiveengineering:arcfurnace/raw_ore_lead",
                "immersiveengineering:arcfurnace/raw_block_lead",
                "immersiveengineering:arcfurnace/dust_lead",
                "immersiveengineering:arcfurnace/ore_lead",
                "thermal:furnace_1147847045",
                "thermal:furnace_1221431766",
                "thermal:furnace_1557914724",
                "thermal:machines/smelter/smelter_raw_lead",
                "thermal:machines/smelter/smelter_lead_plate_to_ingot",
                "thermal:machines/smelter/smelter_lead_ore",
                "thermal:machines/smelter/smelter_lead_dust"
        }));
        put(45005L, new GroupRecipe(45005, "nickel_ingot", new String[]{
                "immersiveengineering:crafting/nugget_nickel_to_ingot_nickel",
                "immersiveengineering:crafting/storage_nickel_to_ingot_nickel",
                "immersiveengineering:crafting/raw_hammercrushing_nickel",
                "immersiveengineering:crafting/hammercrushing_nickel",
                "crafttweaker:nickel_ore",
                "immersiveengineering:smelting/ingot_nickel_from_blasting",
                "immersiveengineering:smelting/ingot_nickel_from_dust_from_blasting",
                "immersiveengineering:smelting/ingot_nickel_from_blasting3",
                "immersiveengineering:smelting/ingot_nickel",
                "immersiveengineering:smelting/ingot_nickel3",
                "immersiveengineering:smelting/ingot_nickel_from_dust",
                "enderio:smelting/immersiveengineering/smelting/ingot_nickel",
                "enderio:smelting/immersiveengineering/smelting/ingot_nickel3",
                "enderio:smelting/immersiveengineering/smelting/ingot_nickel_from_dust",
                "immersiveengineering:smelting/ingot_nickel",
                "immersiveengineering:smelting/ingot_nickel3",
                "immersiveengineering:smelting/ingot_nickel_from_dust",
                "immersiveengineering:arcfurnace/dust_nickel",
                "immersiveengineering:arcfurnace/ore_nickel",
                "immersiveengineering:arcfurnace/raw_ore_nickel",
                "immersiveengineering:arcfurnace/raw_block_nickel",
                "thermal:furnace_596431463",
                "thermal:furnace_533765012",
                "thermal:furnace_1619264303",
                "thermal:machines/smelter/smelter_raw_nickel",
                "thermal:machines/smelter/smelter_nickel_dust",
                "thermal:machines/smelter/smelter_nickel_ore"
        }));
        put(45006L, new GroupRecipe(45006, "osmium_ingot", new String[]{
                "mekanism:processing/osmium/ingot/from_nuggets",
                "mekanism:processing/osmium/ingot/from_block",
                "exdeorum:ores/osmium_tag",
                "immersiveengineering:crafting/hammercrushing_osmium",
                "immersiveengineering:crafting/raw_hammercrushing_osmium",
                "mekanism:processing/osmium/ingot/from_ore_blasting",
                "mekanism:processing/osmium/ingot/from_raw_blasting",
                "mekanism:processing/osmium/ingot/from_dust_blasting",
                "mekanism:processing/osmium/ingot/from_raw_smelting",
                "mekanism:processing/osmium/ingot/from_dust_smelting",
                "mekanism:processing/osmium/ingot/from_ore_smelting",
                "enderio:smelting/mekanism/processing/osmium/ingot/from_ore_smelting",
                "enderio:smelting/mekanism/processing/osmium/ingot/from_raw_smelting",
                "enderio:smelting/mekanism/processing/osmium/ingot/from_dust_smelting",
                "mekanism:processing/osmium/ingot/from_raw_smelting",
                "mekanism:processing/osmium/ingot/from_dust_smelting",
                "mekanism:processing/osmium/ingot/from_ore_smelting",
                "immersiveengineering:arcfurnace/raw_ore_osmium",
                "immersiveengineering:arcfurnace/dust_osmium",
                "immersiveengineering:arcfurnace/ore_osmium",
                "immersiveengineering:arcfurnace/raw_block_osmium",
                "thermal:furnace_231002903",
                "thermal:furnace_297955669",
                "thermal:furnace_730376383",
                "thermal:compat/mekanism/smelter_mek_osmium_ore"
        }));
        put(45007L, new GroupRecipe(45007, "tin_ingot", new String[]{
                "thermal:storage/tin_ingot_from_block",
                "mekanism:processing/tin/ingot/from_nuggets",
                "immersiveengineering:crafting/hammercrushing_tin",
                "immersiveengineering:crafting/raw_hammercrushing_tin",
                "crafttweaker:tin_ore",
                "thermal:smelting/tin_ingot_from_deepslate_ore_blasting",
                "thermal:smelting/tin_ingot_from_raw_blasting",
                "mekanism:processing/tin/ingot/from_dust_blasting",
                "thermal:smelting/tin_ingot_from_raw_smelting",
                "mekanism:processing/tin/ingot/from_dust_smelting",
                "thermal:smelting/tin_ingot_from_deepslate_ore_smelting",
                "enderio:smelting/mekanism/processing/tin/ingot/from_dust_smelting",
                "enderio:smelting/thermal/smelting/tin_ingot_from_deepslate_ore_smelting",
                "enderio:smelting/thermal/smelting/tin_ingot_from_raw_smelting",
                "mekanism:processing/tin/ingot/from_dust_smelting",
                "thermal:smelting/tin_ingot_from_deepslate_ore_smelting",
                "immersiveengineering:arcfurnace/raw_block_tin",
                "immersiveengineering:arcfurnace/dust_tin",
                "immersiveengineering:arcfurnace/ore_tin",
                "immersiveengineering:arcfurnace/raw_ore_tin",
                "thermal:furnace_2091874820",
                "thermal:furnace_1866176983",
                "thermal:furnace_1451001074",
                "thermal:machines/smelter/smelter_tin_ore",
                "thermal:machines/smelter/smelter_tin_dust",
                "thermal:machines/smelter/smelter_raw_tin"
        }));
        put(45008L, new GroupRecipe(45008, "copper_ingot", new String[]{
                "cyclic:copper_ingot",
                "minecraft:copper_ingot_from_waxed_copper_block",
                "immersiveengineering:crafting/nugget_copper_to_copper_ingot",
                "minecraft:copper_ingot",
                "minecraft:copper_ingot_from_waxed_copper_block",
                "exdeorum:copper_ore",
                "immersiveengineering:crafting/raw_hammercrushing_copper",
                "immersiveengineering:crafting/hammercrushing_copper",
                "mekanism:processing/copper/ingot/from_dust_blasting",
                "minecraft:copper_ingot_from_blasting_copper_ore",
                "crafttweaker:ore_copper_blasting",
                "minecraft:copper_ingot_from_blasting_raw_copper",
                "mekanism:processing/copper/ingot/from_dust_smelting",
                "crafttweaker:copper_ingot_from_smelting_copper_end",
                "crafttweaker:copper_ingot_from_smelting_copper_nether",
                "minecraft:copper_ingot_from_smelting_raw_copper",
                "minecraft:copper_ingot_from_smelting_copper_ore",
                "enderio:smelting/minecraft/copper_ingot_from_smelting_raw_copper",
                "enderio:smelting/minecraft/copper_ingot_from_smelting_copper_ore",
                "enderio:smelting/mekanism/processing/copper/ingot/from_dust_smelting",
                "mekanism:processing/copper/ingot/from_dust_smelting",
                "crafttweaker:copper_ingot_from_smelting_copper_end",
                "crafttweaker:copper_ingot_from_smelting_copper_nether",
                "minecraft:copper_ingot_from_smelting_raw_copper",
                "minecraft:copper_ingot_from_smelting_copper_ore",
                "immersiveengineering:arcfurnace/dust_copper",
                "immersiveengineering:arcfurnace/raw_ore_copper",
                "immersiveengineering:arcfurnace/ore_copper",
                "immersiveengineering:arcfurnace/raw_block_copper",
                "thermal:furnace_305421267",
                "thermal:furnace_1432225517",
                "thermal:furnace_370548971",
                "thermal:furnace_1962394495",
                "thermal:furnace_1347239524",
                "thermal:machines/smelter/smelter_copper_ore",
                "thermal:machines/smelter/smelter_copper_dust",
                "thermal:machines/smelter/smelter_raw_copper"
        }));
        put(45009L, new GroupRecipe(45009, "redstone_alloy_ingot", new String[]{
                "enderio:alloy_smelting/redstone_alloy_ingot"
        }));
        put(45010L, new GroupRecipe(45010, "netherite_ingot", new String[]{
                "minecraft:netherite_ingot",
                "enderio:smelting/mekanism/processing/netherite/ingot_from_dust_smelting"
        }));
        put(45011L, new GroupRecipe(45011, "conductive_alloy_ingot", new String[]{
                "enderio:alloy_smelting/conductive_alloy_ingot"
        }));
        put(45012L, new GroupRecipe(45012, "item_conduit", new String[]{
                "enderio:item_conduit",
                "crafttweaker:item_conduit"
        }));
        put(45013L, new GroupRecipe(45013, "fluid_conduit", new String[]{
                "enderio:fluid_conduit",
                "crafttweaker:fluid_conduit"
        }));
        put(45014L, new GroupRecipe(45014, "redstone_ingot", new String[]{
                "extendedcrafting:redstone_ingot"
        }));
        put(45015L, new GroupRecipe(45015, "dark_steel_ingot", new String[]{
                "enderio:alloy_smelting/dark_steel_ingot"
        }));
        put(45016L, new GroupRecipe(45016, "soulium_ingot", new String[]{
                "mysticalagriculture:soulium_ingot"
        }));
        put(45017L, new GroupRecipe(45017, "ender_eye", new String[]{
                "minecraft:ender_eye"
        }));
        put(45018L, new GroupRecipe(45019, "soularium_ingo", new String[]{
                "enderio:alloy_smelting/soularium_ingot"
        }));
        put(45019L, new GroupRecipe(45020, "thermal_machine_frame", new String[]{
                "crafttweaker:thermal_machine_frame"
        }));
        put(45020L, new GroupRecipe(45021, "gear_mold", new String[]{
                "immersiveengineering:blueprint/mold_gear",
                "immersiveengineering:bottling/grindingdisk",
                "thermal:press_gear_die",
                "thermal:parts/constantan_gear",
                "thermal:parts/iron_gear",
                "thermal:parts/gold_gear",
                "thermal:parts/copper_gear",
                "thermal:parts/netherite_gear",
                "thermal:parts/tin_gear",
                "thermal:parts/lead_gear",
                "thermal:parts/silver_gear",
                "thermal:parts/nickel_gear",
                "thermal:parts/steel_gear",
                "thermal:parts/rose_gold_gear",/// to do
                "thermal:parts/bronze_gear", ///  to do
                "thermal:parts/electrum_gear",
                "thermal:parts/invar_gear",
                "thermal:parts/signalum_gear",
                "thermal:parts/lumium_gear",
                "thermal:parts/enderium_gear",
                "thermal:parts/lapis_gear",
                "thermal:parts/diamond_gear",
                "thermal:parts/emerald_gear",
                "thermal:parts/quartz_gear",
                "crafttweaker:ruby_gear",
                "crafttweaker:sapphire_gear",
                "redstone_arsenal:materials/flux_gear",
                "avaritia:neutron_gear"
        }));
        put(45021L, new GroupRecipe(45022, "infusion_crystal", new String[]{
                "mysticalagriculture:infusion_crystal"
        }));
        put(45022L, new GroupRecipe(45023, "master_infusion_crystal", new String[]{
                "mysticalagriculture:master_infusion_crystal"
        }));
        put(45023L, new GroupRecipe(45024, "black_iron_ingot", new String[]{
                "extendedcrafting:black_iron_ingot"
        }));
        put(45024L, new GroupRecipe(45025, "basic_crafting_table", new String[]{
                "crafttweaker:basic_table"
        }));
        put(45025L, new GroupRecipe(45026, "inferium_growth_accelerator", new String[]{
                "mysticalagriculture:inferium_growth_accelerator"
        }));
        put(45026L, new GroupRecipe(45027, "prudentium_growth_accelerator", new String[]{
                "mysticalagriculture:prudentium_growth_accelerator"
        }));
        put(45027L, new GroupRecipe(45028, "tertium_growth_accelerator", new String[]{
                "mysticalagriculture:tertium_growth_accelerator"
        }));
        put(45028L, new GroupRecipe(45029, "imperium_growth_accelerator", new String[]{
                "mysticalagriculture:imperium_growth_accelerator"
        }));
        put(45029L, new GroupRecipe(45030, "supremium_growth_accelerator", new String[]{
                "mysticalagriculture:supremium_growth_accelerator"
        }));
        put(45030L, new GroupRecipe(45031, "electrum_ingot", new String[]{
                "immersiveengineering:alloysmelter/electrum",
                "immersiveengineering:arcfurnace/alloy_electrum",
                "thermal:machines/smelter/smelter_alloy_electrum",
                "thermal:electrum_dust_2"
        }));
        put(45031L, new GroupRecipe(45032, "end_steel_ingot", new String[]{
                "enderio:alloy_smelting/end_steel_ingot"
        }));
        put(45032L, new GroupRecipe(45033, "vibrant_alloy_ingot", new String[]{
                "enderio:alloy_smelting/vibrant_alloy_ingot"
        }));
        put(45033L, new GroupRecipe(45034, "enderium_ingot", new String[]{
                "thermal:enderium_dust_2",
                "thermal:machines/smelter/smelter_alloy_enderium"
        }));
        put(45034L, new GroupRecipe(45035, "awakned_draconium", new String[]{
                "draconicevolution:awakened_draconium_block"
        }));
        put(45035L, new GroupRecipe(45036, "neutronium_ingot", new String[]{
                "minecraft:neutron_ingot_from_nuggets"
        }));
        put(45036L, new GroupRecipe(45037, "energetic_alloy_ingot", new String[]{
                "enderio:alloy_smelting/energetic_alloy_ingot"
        }));
        put(45037L, new GroupRecipe(45038, "steel_ingot", new String[]{
                "immersiveengineering:arcfurnace/steel",
                "immersiveengineering:blastfurnace/steel",
                "thermal:machines/smelter/smelter_alloy_steel",
                "ad_astra:alloying/steel_ingot_from_alloying_iron_ingot_and_coals",
                "mekanism:processing/steel/enriched_iron_to_dust",
                "crafttweaker:steel_dust"
        }));
        put(45038L, new GroupRecipe(45039, "pulsating_alloy_ingot", new String[]{
                "enderio:alloy_smelting/pulsating_alloy_ingot"
        }));
        put(45039L, new GroupRecipe(45040, "copper_alloy_ingot", new String[]{
                "enderio:alloy_smelting/copper_alloy_ingot"
        }));
        put(45040L, new GroupRecipe(45041, "tesseract", new String[]{
                "crafttweaker:tesseract"
        }));
        put(45041L, new GroupRecipe(45042, "ender_ingot", new String[]{
                "extendedcrafting:ender_ingot"
        }));
        put(45042L, new GroupRecipe(45043, "invar_ingot", new String[]{
                "crafttweaker:invar_ingot",
                "thermal:invar_dust",
                "immersiveengineering:alloysmelter/invar",
                "immersiveengineering:arcfurnace/alloy_invar",
                "thermal:machines/smelter/smelter_alloy_invar"
        }));
        put(45043L, new GroupRecipe(45044, "signalum_ingot", new String[]{
                "thermal:machines/smelter/smelter_alloy_signalum",
                "thermal:signalum_dust_4"
        }));
        put(45044L, new GroupRecipe(45045, "lumium_ingot", new String[]{
                "thermal:machines/smelter/smelter_alloy_lumium",
                "thermal:lumium_dust_4"
        }));
        put(45045L, new GroupRecipe(45046, "constantan_ingot", new String[]{
                "thermal:constantan_dust_2",
                "crafttweaker:constantan_ingot",
                "immersiveengineering:alloysmelter/constantan",
                "immersiveengineering:arcfurnace/alloy_constantan",
                "thermal:machines/smelter/smelter_alloy_constantan"
        }));
        put(45046L, new GroupRecipe(45047, "bronze_ingot", new String[]{
                "thermal:bronze_dust_4",
                "mekanism:processing/bronze/ingot/from_infusing",
                "immersiveengineering:alloysmelter/bronze",
                "immersiveengineering:arcfurnace/alloy_bronze",
                "thermal:machines/smelter/smelter_alloy_bronze"
        }));
        put(45047L, new GroupRecipe(45048, "refined_obsidian_ingot", new String[]{
                "mekanism:processing/refined_obsidian/ingot/from_dust"
        }));
        put(45048L, new GroupRecipe(45049, "manasteel_ingot", new String[]{
                "botania:mana_infusion/manasteel"
        }));
        put(45049L, new GroupRecipe(45050, "graphite_bar", new String[]{
                "bigreactors:smelting/graphite_from_charcoal",
                "bigreactors:smelting/graphite_from_coal",
                "bigreactors:blasting/graphite_from_charcoal",
                "bigreactors:blasting/graphite_from_coal",
                "enderio:smelting/bigreactors/smelting/graphite_from_charcoal",
                "enderio:smelting/bigreactors/smelting/graphite_from_coal",
                "bigreactors:smelting/graphite_from_charcoal",
                "bigreactors:smelting/graphite_from_coal",
                "thermal:furnace_730376383",
                "thermal:furnace_1562079447"
        }));
        put(45050L, new GroupRecipe(45051, "fluix_crystal", new String[]{
                "ae2:transform/fluix_crystals"
        }));
        //will be bundled with the mysterious cube
        put(45051L, new GroupRecipe(45052, "basic_processor", new String[]{
                "refinedstorage:raw_basic_processor",
                "refinedstorage:raw_improved_processor",
                "refinedstorage:raw_advanced_processor"
        }));
    }};
    protected boolean injectIRecipe(Recipe<?> iRecipe) {
        for (var entry : recipes.entrySet()) {
            for (String namespaceID : entry.getValue().namespaceIDs) {
                LOGGER.trace("checking {} vs {},", iRecipe.getId().toString(), namespaceID);
                if (iRecipe.getId().toString().equals(namespaceID)) {
                    entry.getValue().addIRecipe(iRecipe);
                    Utils.sendMessageToAll("recipe "+ iRecipe.getId().toString() + " has been lock");
                    return true;
                }
            }
        }
        for (var entry : progressiveRecipes.entrySet()) {
            for (int i = 0; entry.getValue().namespaceIDs.size() > i; ++i) {
                String[] namespaceIDs = entry.getValue().namespaceIDs.get(i);
                for (String s : namespaceIDs) {
                    LOGGER.trace("checking {} vs {},", iRecipe.getId().toString(), s);
                    if (iRecipe.getId().toString().equals(s)) {
                        entry.getValue().addIRecipe(iRecipe, i);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean hasID(long id) {
        return recipes.containsKey(id) || progressiveRecipes.containsKey(id);
    }

    public APRecipe getID(long id) {
        if (recipes.containsKey(id)) {
            return recipes.get(id);
        } else if (progressiveRecipes.containsKey(id)) {
            return progressiveRecipes.get(id);
        }
        return null;
    }

    //our reset here is simple just reset what tier it thinks our progressive recipes are at.
    public void reset() {
        progressiveRecipes.forEach((id, recipe) -> recipe.setCurrentTier(0));
    }
}
