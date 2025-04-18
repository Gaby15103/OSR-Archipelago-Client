package gg.archipelago.aprandomizer.managers.questManager;

import dev.ftb.mods.ftbquests.api.FTBQuestsAPI;
import dev.ftb.mods.ftbquests.quest.*;
import dev.ftb.mods.ftbquests.quest.reward.Reward;
import dev.ftb.mods.ftbquests.quest.reward.RewardType;
import gg.archipelago.aprandomizer.APRandomizer;
import gg.archipelago.aprandomizer.common.Utils.Utils;
import gg.archipelago.aprandomizer.common.events.onQuest;
import gg.archipelago.aprandomizer.data.WorldData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class QuestManager {
    // directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    private final Set<Long> completedQuests = new HashSet<>();
    private static BaseQuestFile baseQuestFile;
    private static HashMap<Long, Quest> quests;
    private static HashMap<String,Long> questData = new HashMap<>(){{
        put("0366E4C57024B5E1:exdeorum:wooden_hammer:245134766379415009", 42000L);
        put("3267B5D26EB6EDAC:Space Suit:3632071539902836140", 42001L);
        put("130498751078C956:minecraft:elytra:1370387815182420310", 42002L);
        put("52558E9A9B255F2C:Alloy Kiln:5932804878809521964", 42003L);
        put("7C7509848963F782:projecte:aeternalis_fuel:8968084697823573890", 42004L);
        put("2CEBBA5BA48CB6EE:rftoolsstorage:storage_module1:3236885659961308910", 42005L);
        put("0D002DB1FBB8EFA3:farmingforblockheads:market:936798964948725667", 42006L);
        put("1463D0E842943F8B:redstone_arsenal:flux_sickle:1469247599420915595", 42007L);
        put("40D505F3A38214FC:botania:red_petal:4671646732445160700", 42008L);
        put("4C116BC549C3BF61:thermal:dynamo_disenchantment:5481280716576898913", 42009L);
        put("60665C65C798C959:mysticalagradditions:insanium_essence:6946341067475700057", 42010L);
        put("7C7DD6CC1A5E66F4:storagedrawers:oak_half_drawers_4:8970562204895962868", 42011L);
        put("582381B5E9F65894:hostilenetworks:overworld_prediction:6351062517836830868", 42012L);
        put("3E2E52EF1565C6FE:botania:black_petal:4480609866089875198", 42013L);
        put("78631C39A6804ECA:mysticalagradditions:inferium_paxel:8674808341177847498", 42014L);
        put("2315AD5F89763FA7:Painting Machines:2528117391678128039", 42015L);
        put("62DE39B1BEF752BA:enderio:octadic_capacitor:7124195096122577594", 42016L);
        put("431D954D3418BF6F:powah:aerial_pearl:4836185733684838255", 42017L);
        put("5E98432CCEF5AF15:64M Storage:6816271895755075349", 42018L);
        put("22B3756F75D9CE30:pneumaticcraft:amadron_tablet:2500471339699785264", 42019L);
        put("65516A8FE8B185F1:mob_grinding_utils:fan_upgrade_speed:7300733637261100529", 42020L);
        put("1FD4C674E8ACF4A8:redstone_arsenal:flux_sword:2293676315660580008", 42021L);
        put("088A633F7AAA303E:thermal:machine_chiller:615413423381557310", 42022L);
        put("2386929E75F58990:minecraft:iron_pickaxe:2559894647488219536", 42023L);
        put("4632C8C4F1622D5D:Into the End:5058326079679376733", 42024L);
        put("7D8D05F2066559D6:mekanismtools:wood_paxel:9046893763504724438", 42025L);
        put("0DA6D312F162E68A:tesseract:tesseract:983705646939694730", 42026L);
        put("73E0BFE574AB4221:mysticalagriculture:tertium_furnace:8349884701370696225", 42027L);
        put("67CC9D4B57B81F68:mekanism:crusher:7479526028075343720", 42028L);
        put("08E49BD35B88AB59:redstone_arsenal:flux_crossbow:640808379069541209", 42029L);
        put("4B09FF0A1DB56319:powah:energy_hopper_starter:5407133246548173593", 42030L);
        put("6B30912D3790F068:mysticalagriculture:nitro_crystal_seeds:7723832984332202088", 42031L);
        put("4487BB32A8321E8B:Basic:4938121342679654027", 42032L);
        put("0B023FB0978509B7:minecraft:nether_wart:793266512059500983", 42033L);
        put("1F16F7B5039613C3:immersiveengineering:fluid_pipe:2240250221484708803", 42034L);
        put("587E8FA1B3ACD6E3:cyclic:apple_honey:6376692047070156515", 42035L);
        put("7E48F8220B813044:cyclic:apple_diamond:9099795872207679556", 42036L);
        put("755ABBE38C75AE31:ironchests:copper_chest:8456277836330020401", 42037L);
        put("721476FC4B99E2C0:Netherite Space Suit:8220326045830210240", 42038L);
        put("326BB3D7D7F1C495:chipped:alchemy_bench:3633195264026723477", 42039L);
        put("4DDF690FD8CA4D56:draconicevolution:wyvern_crafting_injector:5611319177509490006", 42040L);
        put("4AE8A5DD70731DC5:mysticalagriculture:vibrant_alloy_seeds:5397746523896487365", 42041L);
        put("272874C4E29A97D3:powah:battery_blazing:2821633555511810003", 42042L);
        put("4B2825C257986EF8:mob_grinding_utils:absorption_hopper:5415620068536512248", 42043L);
        put("62F15DDF575F4605:cobblefordays:tier_4:7129582898929157637", 42044L);
        put("3BC52195653A9011:enderio:stirling_generator:4306885544181927953", 42045L);
        put("4F0C9F7740D46013:solarflux:traversal_upgrade:5696102963254419475", 42046L);
        put("6D3E1A586AB3F99A:mysticalagriculture:prudentium_growth_accelerator:7871758165739829658", 42047L);
        put("2569A126CB0A8950:256k Storage:2695863039945247056", 42048L);
        put("4CE5EA6C8E105623:exdeorum:netherite_hammer:5541092668510066211", 42049L);
        put("271B755051628B5B:solarflux:sp_4:2817974979673623387", 42050L);
        put("0824649D9D165302:solarflux:sp_de.wyvern:586704479573136130", 42051L);
        put("1755A85B0F92464A:Hardened:1681435144949483082", 42052L);
        put("6AA00CEA49650CA4:solarflux:twilightforest/twilight_upgrade:7683155164687305892", 42053L);
        put("00C14C1B77ACFCD2:mysticalagriculture:infusion_crystal:54408351360810194", 42054L);
        put("45C50B63083377EB:botania:mana_pool:5027437078996285419", 42055L);
        put("092E17F60451DB2F:minecraft:enchanting_table:661492540671908655", 42056L);
        put("41ACAC42D3ECF8A9:chunkloaders:ultimate_chunk_loader:4732346711482890409", 42057L);
        put("418CF7FDF846BE84:storagedrawers:oak_full_drawers_1:4723422779368980100", 42058L);
        put("4E724832D6644045:rftoolsutility:crafter3:5652659865485852741", 42059L);
        put("4AD415D51F455691:mekanismtools:refined_glowstone_paxel:5391958658966181521", 42060L);
        put("50D771A1B08295B6:cyclic:anvil:5825249582292047286", 42061L);
        put("79E30F3A54F84EA4:botania:gaia_ingot:8782880441510678180", 42062L);
        put("1AA5CB18794F41EC:chipped:glassblower:1920164122118275564", 42063L);
        put("008A101B82C5025A:thermal:dynamo_numismatic:38861257130181210", 42064L);
        put("238651C98852C456:botania:apothecary_default:2559823364224107606", 42065L);
        put("2EC618F53E619F73:exdeorum:flint_mesh:3370408812726034291", 42066L);
        put("6EB5A813D4088C7E:botania:lexicon:7977467118071876734", 42067L);
        put("1B2809B6F2B4ECA2:redstone_arsenal:flux_fishing_rod:1956824719453121698", 42068L);
        put("12EB38289B348239:mekanism:basic_energy_cube:1363245059263463993", 42069L);
        put("77A87F38662FDAE3:patchouli:guide_book:8622281366810122979", 42070L);
        put("1125E171E567293C:powah:energizing_orb:1235641552079366460", 42071L);
        put("361BB94AB55C1A9E:extendedcrafting:advanced_catalyst:3898913632940726942", 42072L);
        put("41275C2B37848720:botania:terrasteel_ingot:4694822477241812768", 42073L);
        put("345C7F6955DCBF95:ironchests:netherite_chest:3773030678218456981", 42074L);
        put("51A0F9A5FD72B84C:Dungeon Master:5881975604662941772", 42075L);
        put("5CEFCBB2DA94997B:mekanismtools:lapis_lazuli_paxel:6696795139955005819", 42076L);
        put("658CF4963978B628:botania:orange_petal:7317492420616697384", 42077L);
        put("6042D5F44B5F0A5F:storagedrawers:oak_full_drawers_4:6936341621317241439", 42078L);
        put("283D4AE04F36E7DF:redstone_arsenal:flux_elytra:2899556062358595551", 42079L);
        put("0FE358C58400C6A0:mysticalagriculture:imperium_furnace:1144856335628682912", 42080L);
        put("249E36927538C411:projecte:philosophers_stone:2638606434345468945", 42081L);
        put("007A44E227967158:thermal:machine_crucible:34415685276168536", 42082L);
        put("715D790B9529BA88:industrialforegoing:machine_frame_simple:8168818389774088840", 42083L);
        put("0497D308100CEA7E:ironjetpacks:capacitor:330965129217501822", 42084L);
        put("62DE5FA1C1F76537:immersiveengineering:workbench:7124236808895292727", 42085L);
        put("24226A5DB7C6CC00:storagedrawers:shroud_key:2603760485321329664", 42086L);
        put("4F9E0FF0FDC4AFF9:powah:battery_nitro:5737040503040684025", 42087L);
        put("129ACE50C21E79EF:industrialforegoing:black_hole_controller:1340610685345626607", 42088L);
        put("089F8808A64381C3:powah:book:621364844330975683", 42089L);
        put("51ADDE07480D68C7:storagedrawers:emerald_storage_upgrade:5885604410898081991", 42090L);
        put("69A84F014E7F034C:thermal:machine_furnace:7613422037100331852", 42091L);
        put("53E729F7A36FB0FF:avaritia:neutron_collector:6045847168343847167", 42092L);
        put("18EB92AEBC23CCC8:projecte:mobius_fuel:1795690155615702216", 42093L);
        put("34570BDB16B3E57B:powah:uraninite:3771496248552711547", 42094L);
        put("1467C86B0DB6BF13:ironjetpacks:capacitor:1470364165476892435", 42095L);
        put("010C229774F22C63:redstone_arsenal:flux_shovel:75473327655890019", 42096L);
        put("4398B8242093BE3F:mekanismtools:iron_paxel:4870845462306078271", 42097L);
        put("50FA8A733EE88840:exdeorum:diamond_mesh:5835128494793197632", 42098L);
        put("09DA42D1300AEF21:industrialforegoing:common_black_hole_unit:709953357485895457", 42099L);
        put("3BFF866B32E6FD5A:mysticalagriculture:spirited_crystal_seeds:4323321962272587098", 42100L);
        put("2D3DEBC427EEF742:Nitro:3260020933002196802", 42101L);
        put("39BB2C47B2611FB1:powah:battery_basic:4159967367253794737", 42102L);
        put("544305340A781906:Upgrades:6071702443697641734", 42103L);
        put("357543CD8A5D9DB6:4k Storage:3852059606354075062", 42104L);
        put("4AB2114871FFCEDC:rftoolsstorage:storage_module3:5382383507509071580", 42105L);
        put("6D75D785EB89AFAF:enderio:advanced_capacitor_bank:7887447292591583151", 42106L);
        put("67E2304FB8A29A5F:mekanismtools:stone_paxel:7485598649601399391", 42107L);
        put("3EE16F0264052C50:Getting Started:4531024756170107984", 42108L);
        put("23E8945836D53D6E:betterfurnacesreforged:iron_forge:2587481092522327406", 42109L);
        put("68C295E0107861DA:mekanismtools:steel_paxel:7548760715007910362", 42110L);
        put("15189D9DBA11779E:mekanism:energized_smelter:1520138174994675614", 42111L);
        put("1C49E92D224E3EB3:minecraft:dragon_head:2038416686420213427", 42112L);
        put("09C3686C05D82DB4:solarflux:sp_tf.fiery:703520779963739572", 42113L);
        put("4BC0CBB7502B3367:mekanism:creative_fluid_tank:5458586736557503335", 42114L);
        put("236AB7E3C6AE1F38:enderio:slice_and_splice:2552054327777566520", 42115L);
        put("4B61E75F57941931:immersiveengineering:steel_scaffolding_standard:5431877022262761777", 42116L);
        put("3C93642728A0CAAA:Into the Undergarden:4364942583200271018", 42117L);
        put("29E40CD5EF7495FB:mysticalagriculture:nether_star_seeds:3018551763230037499", 42118L);
        put("60331AC07AD1EBF5:projecte:transmutation_tablet:6931913665449946101", 42119L);
        put("6417762CBF914C64:mekanism:enrichment_chamber:7212363262821747812", 42120L);
        put("2BC7CCC03A2AB01A:exdeorum:netherite_mesh:3154715189977985050", 42121L);
        put("35FB3DDDD991DB3E:mob_grinding_utils:gm_chicken_feed_cursed:3889770726211836734", 42122L);
        put("1F2933C86F227F89:mysticalagriculture:awakened_draconium_seeds:2245382825171910537", 42123L);
        put("7A579C382828D3FF:patchouli:guide_book:8815686560606901247", 42124L);
        put("25E8C61EBDB5DF56:powah:ender_core:2731651009334337366", 42125L);
        put("3082BB769BB97BEC:hostilenetworks:deep_learner:3495562378886085612", 42126L);
        put("17E874348EBC65E9:botania:light_gray_petal:1722754626551047657", 42127L);
        put("3F309FB5E1672716:betterfurnacesreforged:extreme_forge:4553314826791102230", 42128L);
        put("77F0DF050A474878:Into the Twilight Forest:8642652897664256120", 42129L);
        put("1C49D56AD66A9A18:chunkloaders:advanced_chunk_loader:2038394961202420248", 42130L);
        put("1F26D7D6336C6B0D:botania:natura_pylon:2244718779276487437", 42131L);
        put("17E5A5A650EC804A:constructionwand:core_destruction:1721964566279913546", 42132L);
        put("0B98823BEBCD00D1:Chaos Shard Duplication:835560924747989201", 42133L);
        put("5139878FF928711F:Starting Power:5852858243174920479", 42134L);
        put("7A4707F5D668F55F:botania:cyan_petal:8811019948419249503", 42135L);
        put("6668D3FEB6B195C4:Dimension Card:7379381080386803140", 42136L);
        put("721A33A024056D36:projecte:rm_katar:8221940832610381110", 42137L);
        put("1A520CD6F25FE860:ad_astra:oxygen_loader:1896592510405306464", 42138L);
        put("77120C4E9ECB9A5B:angelring:diamond_ring:8579933771905342043", 42139L);
        put("3EDFCF2825FBDCEA:powah:energy_cell_creative:4530567521500912874", 42140L);
        put("3F271262B3C6BE36:extendedcrafting:crystaltine_catalyst:4550626163613417014", 42141L);
        put("0D56F9F5F92A22D1:redstone_arsenal:flux_pickaxe:961230405332771537", 42142L);
        put("20D31F5DFA10C602:rftoolsutility:crafter1:2365268717787465218", 42143L);
        put("4A3ED57B133E0F5C:ad_astra:tier_4_rocket:5349948131943255900", 42144L);
        put("569F057DEE9840AB:industrialforegoing:mycelial_culinary:6241713646990803115", 42145L);
        put("761298D0F39BC4EE:cyclic:apple_lapis:8508030669263979758", 42146L);
        put("2B2FB3D50B29B04F:enderio:vibrant_capacitor_bank:3111903595132989519", 42147L);
        put("29631AAEB3EE862C:mysticalagriculture:prudentium_furnace:2982256715894785580", 42148L);
        put("2FEE448F16BA7321:storagedrawers:creative_storage_upgrade:3453773345592079137", 42149L);
        put("516F027BF3428F94:trashcans:ultimate_trash_can:5867911570872504212", 42150L);
        put("089EB2576E939DE2:thermal:dynamo_lapidary:621129887210773986", 42151L);
        put("59DB37E04DE34EBD:exdeorum:golden_mesh:6474830325794164413", 42152L);
        put("2E11E2DE34806504:woodenbucket:wooden_bucket:3319683844340212996", 42153L);
        put("09999790B789A604:mysticalagriculture:water_seeds:691750665588418052", 42154L);
        put("5BF01179CCD780F0:extendedcrafting:the_ultimate_catalyst:6624814266687389936", 42155L);
        put("502368BBB9DF42A3:storagedrawers:gold_storage_upgrade:5774574302705697443", 42156L);
        put("700578C9ED61AA16:mekanism:purification_chamber:8071990715797842454", 42157L);
        put("6844FF31F235F550:thermal:dynamo_gourmand:7513410668342932816", 42158L);
        put("00F000B1AE6E31E9:botania:magenta_petal:67554757546226153", 42159L);
        put("702B0D773193BB64:cobblefordays:tier_5:8082568761830521700", 42160L);
        put("499DEBE64BE4FAA6:powah:reactor_starter:5304655310460615334", 42161L);
        put("1892F7D6B155AADD:aether:enchanted_gravitite:1770750104980269789", 42162L);
        put("164900FAE9CE19DC:powah:crystal_blazing:1605815819798714844", 42163L);
        put("2C512D6C9BB46EE2:64k Storage:3193383555274403554", 42164L);
        put("7B95D92438371980:mekanismtools:diamond_paxel:8905262587780209024", 42165L);
        put("1BBAE3C2176C6BA6:Engineer's Tools:1998159807448378278", 42166L);
        put("2F5D8150C3A8C415:botania:gaia_pylon:3413026276496688149", 42167L);
        put("2E4FB49A2DD9A513:chipped:botanist_workbench:3337084423192028435", 42168L);
        put("74D262D220F6AD75:ad_astra:calorite_plate:8417899308144569717", 42169L);
        put("49C5E4A324921A73:pneumaticcraft:reinforced_pressure_tube:5315906324525095539", 42170L);
        put("28477BE219D0D520:pneumaticcraft:advanced_liquid_compressor:2902424695889515808", 42171L);
        put("6A40A9ED42048DC0:industrialforegoing:pitiful_generator:7656306203009781184", 42172L);
        put("47C0A4FE9AA357A8:industrialforegoing:machine_frame_pity:5170313785644373928", 42173L);
        put("26B34910CE753FB9:Clouds:2788652930804563897", 42174L);
        put("57806AD385106226:rftoolsutility:matter_transmitter:6305156935021781542", 42175L);
        put("641F869574914AB0:redstone_arsenal:flux_bow:7214633104534751920", 42176L);
        put("057F887839D3B028:powah:battery_starter:396185342179520552", 42177L);
        put("423FCA2A7EF09109:Mechanical Sieve:4773756413903147273", 42178L);
        put("3BCA4E8A93D6B49E:Draconic Fusion Crafting:4308342350626075806", 42179L);
        put("1DD1DCC7A9D37C31:powah:crystal_nitro:2148741247338249265", 42180L);
        put("37774FFF19A446DF:cookingforblockheads:no_filter_edition:3996751151380055775", 42181L);
        put("673A9F05CA581D87:angelblockrenewed:angel_block:7438432581774220679", 42182L);
        put("14EB2DF6925C875B:Pneumatic Assembly:1507349037326305115", 42183L);
        put("6BCA1F6E5A1F52C0:thermal:energy_cell:7767055066172707520", 42184L);
        put("2218FD98BD04A52E:draconicevolution:chaos_shard:2456992429178660142", 42185L);
        put("7995042AD8710998:undergarden:froststeel_sword:8760913232185592216", 42186L);
        put("5C995D768B6D83A2:thermal:machine_centrifuge:6672467086652834722", 42187L);
        put("3E9ACFA8AF8D7DA5:betterfurnacesreforged:copper_furnace:4511146300171713957", 42188L);
        put("2E3395E305C41745:solarflux:sp_de.draconic:3329169351843780421", 42189L);
        put("645144B65DD8C33D:mysticalagriculture:diamond_seeds:7228634426955580221", 42190L);
        put("5181670828BD8186:draconicevolution:awakened_crafting_injector:5873088673808744838", 42191L);
        put("123F1D4B1509DA03:enderio:energetic_photovoltaic_module:1314801824528194051", 42192L);
        put("49C3E3CAC20DF7BC:extendedcrafting:elite_table:5315342445205911484", 42193L);
        put("3D039057F963BF8A:enderio:travel_anchor:4396516368764354442", 42194L);
        put("5104ACF37E471F1A:extendedcrafting:basic_catalyst:5837981178774626074", 42195L);
        put("61764BC9112A7918:patchouli:guide_book:7022883995879373080", 42196L);
        put("4809A25A2F4709C3:avaritia:infinity_catalyst:5190858553745148355", 42197L);
        put("1A3FA59451D43379:Controller:1891412424965501817", 42198L);
        put("1F0F791303414C9F:redstone_arsenal:flux_wrench:2238140662392376479", 42199L);
        put("7854EB31D875B927:mob_grinding_utils:saw_upgrade_sharpness:8670813781912566055", 42200L);
        put("5C3C77AAFDB39065:Coke Oven:6646318726376558693", 42201L);
        put("221B5FD698E5AF4A:mob_grinding_utils:saw_upgrade_looting:2457663396953567050", 42202L);
        put("6DAAAA21A430F540:cyclic:lunchbox:7902315557640271168", 42203L);
        put("1EE80B09837AF1D9:immersiveengineering:plate_steel:2227042151223194073", 42204L);
        put("23093BFA316B6B83:Even More Storage:2524615011874859907", 42205L);
        put("6E0DE5001D7AF6D3:draconicevolution:draconic_chestpiece:7930246307526670035", 42206L);
        put("6B1A8D1CE3D65BA0:mysticalagriculture:tertium_essence:7717636066673843104", 42207L);
        put("6F1CAF83608EAB3E:cyclic:disenchanter:8006467216353438526", 42208L);
        put("5EEDD363F71B2FEC:Improved Blast Furnace:6840355835346628588", 42209L);
        put("7617CB1F7F502680:hostilenetworks:end_prediction:8509493357080225408", 42210L);
        put("5E95CB10F2939C12:solarflux:capacity_upgrade:6815576884744789010", 42211L);
        put("3FEBBF71D957867B:mysticalagriculture:supremium_furnace:4605985539615065723", 42212L);
        put("268AB8819A2C91A0:16M Storage:2777234987004236192", 42213L);
        put("56CC6260E5B09B5A:mysticalagriculture:master_infusion_crystal:6254482150820715354", 42214L);
        put("3BAAF007B830AD10:enderio:staff_of_levity:4299512710224194832", 42215L);
        put("2CFFA094B47F28E9:rftoolsutility:dialing_device:3242486817273882857", 42216L);
        put("10C49AFD1DB6A8E9:mysticalagradditions:creative_essence:1208261011958049001", 42217L);
        put("7CE3C90007447787:Thermal Series:8999257482375493511", 42218L);
        put("4D8D51291629C01B:redstone_arsenal:flux_metal_block:5588211949543997467", 42219L);
        put("09EAF3F7ABE5840E:cookingforblockheads:recipe_book:714651735958062094", 42220L);
        put("27E92F9CAD2E8201:mysticalagriculture:inferium_furnace:2875882187019682305", 42221L);
        put("37C69DFAEE126C2A:rftoolsstorage:storage_module2:4019073418536315946", 42222L);
        put("6634C250A453D27A:minecraft:shulker_shell:7364724942267732602", 42223L);
        put("4D1D60A606D7525A:ironjetpacks:capacitor:5556703781440672346", 42224L);
        put("3D181D1398577D0E:storagedrawers:controller_slave:4402300605752114446", 42225L);
        put("77F16B65C041726F:ad_astra:tier_1_rocket:8642807244662076015", 42226L);
        put("5051F80ECA9FBFB9:Varied Block Types:5787679738560495545", 42227L);
        put("0984717AC3E4C46A:botania:white_petal:685797815353656426", 42228L);
        put("10AB6EF714EC6571:mysticalagriculture:emerald_seeds:1201175733111383409", 42229L);
        put("2614ECA52DB256A6:Wireless Crafting:2744078267155895974", 42230L);
        put("41D4504AFE0D80F8:enderio:alloy_smelter:4743504590548074744", 42231L);
        put("55A1A678A5144AD1:mekanismtools:bronze_paxel:6170396001570147025", 42232L);
        put("4176CCCBE76268DF:cyclic:teleport_wand:4717182835849586911", 42233L);
        put("65029D69C4D7C329:botania:livingrock:7278553025383744297", 42234L);
        put("3357F34BE165ACBC:extendedcrafting:enhanced_ender_catalyst:3699693126137785532", 42235L);
        put("1BB90181D2B61AD6:solarflux:sp_3:1997629566813084374", 42236L);
        put("72DFDF6EEEE0F329:cyclic:apple_prismarine:8277580307677377321", 42237L);
        put("4B0781982EFCF468:mekanism:advanced_energy_cube:5406432368304911464", 42238L);
        put("16AD6B208C1D9CF2:mob_grinding_utils:ender_inhibitor_on:1634080027339234546", 42239L);
        put("20E4E8939E7021CD:hostilenetworks:sim_chamber:2370275024619708877", 42240L);
        put("64593A765B84CBFE:Build a Kitchen:7230874956736023550", 42241L);
        put("631886694B190186:mysticalagriculture:gaia_spirit_seeds:7140604995985539462", 42242L);
        put("06084536DEFB9B02:pneumaticcraft:advanced_air_compressor:434673466012834562", 42243L);
        put("70A23413D989C094:hangglider:hang_glider:8116106738333761684", 42244L);
        put("694ED42FA3146EEA:projecte:alchemical_coal:7588235723230441194", 42245L);
        put("4FFD39276D8238DF:thermal:machine_smelter:5763825939607861471", 42246L);
        put("0A6BF9A655AD18F4:rftoolsutility:matter_receiver:750968255684614388", 42247L);
        put("21EBF76CE1C8DD44:mysticalagriculture:end_steel_seeds:2444319269795192132", 42248L);
        put("408BA52DFB3A0066:rftoolsstorage:storage_module0:4650992657097162854", 42249L);
        put("2DF4726B8D54971D:mysticalagriculture:yellorium_seeds:3311397432282355485", 42250L);
        put("07190E7B4881D272:mekanism:teleporter:511455955343495794", 42251L);
        put("0C0743BB7BBBB11B:powah:solar_panel_starter:866735925805953307", 42252L);
        put("2133D9091DA6AB65:rftoolsstorage:modular_storage:2392494460239260517", 42253L);
        put("6C636869DB9AD89F:extendedcrafting:advanced_table:7810200982655785119", 42254L);
        put("41D57481AD08B1C4:ironchests:gold_chest:4743825882807316932", 42255L);
        put("6E7CA77A0A77CF2B:betterfurnacesreforged:diamond_forge:7961422383887666987", 42256L);
        put("6D28BFB719B857C5:redstone_arsenal:flux_quiver:7865747542334003141", 42257L);
        put("4C20760BE5CE7796:industrialforegoing:plastic:5485514139609495446", 42258L);
        put("6F25BC39A243FB15:enderio:basic_capacitor_bank:8009014468069817109", 42259L);
        put("026968D0C9F3EEF3:Chaos Guardian:173785306581167859", 42260L);
        put("4109CFAABCC43103:mob_grinding_utils:tank:4686505219474075907", 42261L);
        put("37D1B059E4217163:exdeorum:iron_hammer:4022189842344538467", 42262L);
        put("0632775165BACE5A:bigreactors:graphite_ingot:446550504545898074", 42263L);
        put("39C5318243BD3B15:cyclic:apple_chorus:4162787866125875989", 42264L);
        put("153F4EBD5928273F:cyclic:soundproofing:1531028973480847167", 42265L);
        put("0076047DA7FD61DF:angelring:leadstone_angel_ring:33218984987681247", 42266L);
        put("20B034D16EE93E07:Pam's Kitchen Tools:2355440679228358151", 42267L);
        put("47686031CE8F3478:pneumaticcraft:ingot_iron_compressed:5145468341305947256", 42268L);
        put("6FFF383B65563E08:Basic Turbine:8070230885025594888", 42269L);
        put("621446C746165507:extendedcrafting:flux_crafter:7067351536939455751", 42270L);
        put("39822004CF85610A:ironjetpacks:capacitor:4143909812167860490", 42271L);
        put("57D82F63CCCAFC6A:trashcans:energy_trash_can:6329861381953354858", 42272L);
        put("66EB0CF3E7120F96:constructionwand:diamond_wand:7416035453088960406", 42273L);
        put("600233D31B6CE0DF:thermal:dynamo_magmatic:6918148959385739487", 42274L);
        put("5241E6CA7D03EC2C:draconicevolution:awakened_core:5927272341951474732", 42275L);
        put("721DE48EB10F97EE:mysticalagriculture:netherite_seeds:8222979796155471854", 42276L);
        put("556A26FD1821B382:solarflux:sp_1:6154774709228647298", 42277L);
        put("07BB874036D0C27A:thermal:machine_pulverizer:557187688777695866", 42278L);
        put("3827000B3451BAD7:mob_grinding_utils:saw_upgrade_arthropod:4046202838338091735", 42279L);
        put("3C392D84AF0B5711:redstone_arsenal:flux_trident:4339549763843872529", 42280L);
        put("71F4CA2830D6EB69:mekanismgenerators:gas_burning_generator:8211410294570150761", 42281L);
        put("3E2361BEEA7F85CD:angelring:hardened_angel_ring:4477529927142311373", 42282L);
        put("570BF316442B3B31:mob_grinding_utils:saw_upgrade_smite:6272374183002061617", 42283L);
        put("160D692027A53B64:hostilenetworks:prediction_matrix:1589041830356663140", 42284L);
        put("0F7268F1692BA6DD:mysticalagriculture:awakened_supremium_furnace:1113067443974809309", 42285L);
        put("488BE929D223408C:mysticalagriculture:enderium_seeds:5227528158322049164", 42286L);
        put("4F2E868BE8E4EDBD:angelring:angel_ring:5705645713390890429", 42287L);
        put("008708864D9B7CAD:draconicevolution:chaotic_core:38008494776614061", 42288L);
        put("56FBAF04FEC1E0F8:thermal:machine_bottler:6267795742405026040", 42289L);
        put("4C0FC73E751AC347:extendedcrafting:ender_catalyst:5480818342599770951", 42290L);
        put("2E548A9237C72042:avaritia:neutron_ingot:3338445584394035266", 42291L);
        put("4A05549794AC770B:mob_grinding_utils:golden_egg:5333762343701346059", 42292L);
        put("59559F76CB02926B:extendedcrafting:enhanced_redstone_catalyst:6437226574956892779", 42293L);
        put("5948A62DBB7CE1A4:immersiveengineering:heavy_engineering:6433574783048016292", 42294L);
        put("763C41E67AABF25F:betterfurnacesreforged:netherhot_forge:8519757053234508383", 42295L);
        put("6C5ABCB21F4AD687:Covalence Dust:7807760377215374983", 42296L);
        put("38AA05C643E2D4FF:powah:battery_hardened:4083082361265378559", 42297L);
        put("0332B6BA96B14AD1:undergarden:cloggrum_sword:230447443457690321", 42298L);
        put("38B38839D9365730:botania:livingwood_log:4085759068993902384", 42299L);
        put("58824BBCD74179F7:hangglider:reinforced_hang_glider:6377743296747370999", 42300L);
        put("41C74A18C8B756B1:solarflux:sp_2:4739838603137865393", 42301L);
        put("599D753764477C22:Conduit Upgrades:6457446321485216802", 42302L);
        put("36DE9BE5B9E817D5:minecraft:wither_skeleton_skull:3953768933846685653", 42303L);
        put("6A8D95859D0D41A3:mekanism:oredictionificator:7677957340834644387", 42304L);
        put("1759B24EE5751EF7:Into the Mining Dimensions:1682571987726442231", 42305L);
        put("0D3C589B358447AB:extendedcrafting:ultimate_singularity:953734644736739243", 42306L);
        put("2CB97A9EECD58E88:mysticalagriculture:seed_reprocessor:3222741831357140616", 42307L);
        put("3C5C46275635B639:enderio:xp_obelisk:4349428474897086009", 42308L);
        put("1B56B0A5C3158499:minecraft:bucket:1969956113010230425", 42309L);
        put("189D3AEA2DEE6CD1:pneumaticcraft:printed_circuit_board:1773638605721201873", 42310L);
        put("0FE4104B78C71CC0:mekanismtools:gold_paxel:1145058121593855168", 42311L);
        put("0A0CAFC53A9F49CA:solarflux:efficiency_upgrade:724146901726742986", 42312L);
        put("1D6D696FBEE501E7:mysticalagradditions:imperium_paxel:2120466928226337255", 42313L);
        put("6BE7EA49DC2FA6D2:projecte:dark_matter:7775440884626073298", 42314L);
        put("0E72F3DC49ECCFAC:solarflux:sp_tf.carminite:1041162591334617004", 42315L);
        put("187CE1DC0DC4AF2D:bigreactors:insanite_block:1764533489262440237", 42316L);
        put("252FF7E709D75DBB:mob_grinding_utils:spikes:2679632874983349691", 42317L);
        put("755E8742EEC1D006:mob_grinding_utils:xp_tap:8457345871791640582", 42318L);
        put("09CD6F61EF329771:trashcans:liquid_trash_can:706343182982616945", 42319L);
        put("1C4D8135917B74EB:industrialforegoing:common_black_hole_tank:2039428273342739691", 42320L);
        put("1B98E6A24B2FD26A:immersiveengineering:sheetmetal_iron:1988592820204589674", 42321L);
        put("5A0676C55549A2EA:pneumaticcraft:air_compressor:6487002903185302250", 42322L);
        put("552EF8646F9D5E55:mekanism:elite_energy_cube:6138116452405567061", 42323L);
        put("46F450DDEAECE702:minecraft:dragon_egg:5112800391031744258", 42324L);
        put("3C61B00A5662F884:immersiveengineering:treated_wood_horizontal:4350952273462098052", 42325L);
        put("0754204BD1B84C6E:chunkloaders:basic_chunk_loader:528082566322343022", 42326L);
        put("0E591671C9C77C30:angelring:resonant_angel_ring:1033882267430648880", 42327L);
        put("5C9ECD8109413F70:mysticalagriculture:flight_augment:6673997651899400048", 42328L);
        put("0BFDD76B51FA0EA7:Reinforced Turbine:864083559461818023", 42329L);
        put("1BA88B607F47E8FC:Crude Blast Furnace:1992996081679984892", 42330L);
        put("2F349D814A5AAAAD:powah:steel_energized:3401516797195365037", 42331L);
        put("7E31F38928E965F2:mysticalagriculture:earth_seeds:9093316893060195826", 42332L);
        put("034786603D7FDD18:mysticalagriculture:tertium_growth_accelerator:236305253367012632", 42333L);
        put("1DBA3366F50A27DD:exdeorum:stone_hammer:2142081090058856413", 42334L);
        put("30456C23D0367E64:industrialforegoing:mycelial_furnace:3478305188286463588", 42335L);
        put("6543E32C66892345:mysticalagriculture:tinkering_table:7296925601108665157", 42336L);
        put("0DCAF4BD7DA81E0C:Dim/Ender Storage:993875762482781708", 42337L);
        put("47CDA15E8566561B:solarflux:block_charging_upgrade:5173968974255642139", 42338L);
        put("4140645DC360472A:Enchanter:4701868364847400746", 42339L);
        put("430434760D2EC53D:enderio:dark_steel_sword:4829042382079968573", 42340L);
        put("4DCF187529D58214:mysticalagriculture:supremium_essence:5606726952591655444", 42341L);
        put("67E3E6364520C852:angelring:energetic_angel_ring:7486080126382295122", 42342L);
        put("01875203CFD676FD:thermal:machine_refinery:110146892219184893", 42343L);
        put("710E293C5F362BDA:industrialforegoing:mycelial_halitosis:8146494115231968218", 42344L);
        put("4DD6781AF23A36CE:bigreactors:inanite_block:5608802443064915662", 42345L);
        put("70FC8BA6D4E1C08E:chipped:tinkering_table:8141535775031410830", 42346L);
        put("783FDD210C8E8C93:enderio:vibrant_photovoltaic_module:8664887342098451603", 42347L);
        put("4026905FACEEB1F2:mysticalagradditions:prudentium_paxel:4622540808140009970", 42348L);
        put("28AEB6B18BC853EF:thermal:machine_brewer:2931481281135399919", 42349L);
        put("6A2AE7213737A80A:immersiveengineering:plate_iron:7650181046888540170", 42350L);
        put("304C3DA255E8BEA1:enderio:basic_capacitor:3480224379485863585", 42351L);
        put("5FC1F4C053B4DFB2:ad_astra:ice_shard:6900065210983571378", 42352L);
        put("0AFEE76D3204FB5B:angelring:reinforced_angel_ring:792325040640424795", 42353L);
        put("3B05D233BD036F90:mekanism:basic_control_circuit:4253036542777585552", 42354L);
        put("131E32F38FD34133:pneumaticcraft:thermopneumatic_processing_plant:1377594557693378867", 42355L);
        put("668D33D154B79414:solarflux:sp_8:7389619537747350548", 42356L);
        put("721B545DEB60BCBD:projecte:rm_morning_star:8222258307052649661", 42357L);
        put("0E8AEDBF4367D34D:industrialforegoing:dissolution_chamber:1047911269042475853", 42358L);
        put("4BE855C08DE7BDC4:mob_grinding_utils:fan:5469716032944324036", 42359L);
        put("25A52B75D396C2C7:chancecubes:creative_pendant:2712622135621632711", 42360L);
        put("40812D2012C3068B:mysticalagriculture:awakened_supremium_chestplate:4648045906200037003", 42361L);
        put("3480CD4B028D3084:exdeorum:diamond_hammer:3783249409040265348", 42362L);
        put("1C5DB079B5AA1BCD:mekanismgenerators:advanced_solar_generator:2043983842681428941", 42363L);
        put("2623B69479593C70:pneumaticcraft:unassembled_pcb:2748240946433440880", 42364L);
        put("4F263B60E4157BCA:constructionwand:stone_wand:5703311265440824266", 42365L);
        put("2761EA6245F64645:solarflux:transfer_rate_upgrade:2837806948021585477", 42366L);
        put("17DCD0325F76FF0C:mysticalagriculture:inferium_essence:1719478072517263116", 42367L);
        put("2E9E5B35927F5A4C:industrialforegoing:mycelial_ender:3359222657714182732", 42368L);
        put("2CD899D0FF981FE6:Coin Shop:3231501855558737894", 42369L);
        put("16FC2C94F09D2335:ironjetpacks:capacitor:1656247781169111861", 42370L);
        put("1BBED5317DB102D4:mysticalagriculture:niotic_crystal_seeds:1999269693137945300", 42371L);
        put("74076796EE6A84BE:bigreactors:yellorium_ingot:8360765131179328702", 42372L);
        put("0226202FC2B70325:constructionwand:iron_wand:154846626693186341", 42373L);
        put("7D447FD94FEA3FC8:mekanismtools:netherite_paxel:9026480124482699208", 42374L);
        put("0187148CB8910C44:New Resources:110079310518357060", 42375L);
        put("5B49ED8087C82E2C:draconicevolution:chaotic_crafting_injector:6578049867040960044", 42376L);
        put("626FBCC4BE843CEB:mysticalagriculture:wither_skeleton_seeds:7093095491327769835", 42377L);
        put("2F89C9A81F049F60:betterfurnacesreforged:ultimate_forge:3425490715504058208", 42378L);
        put("7FF2C44C6839D48C:mysticalagriculture:imperium_chestplate:9219647219626005644", 42379L);
        put("01AB1445EBDED5F2:industrialforegoing:latex_processing_unit:120212105597998578", 42380L);
        put("140A6BB74911AF3C:botania:brown_petal:1444085065474748220", 42381L);
        put("28ADE47BE9ED4B9F:mysticalagriculture:neutronium_seeds:2931250153344813983", 42382L);
        put("11C1BDB53489F2A9:ad_astra:cryo_freezer:1279512355118117545", 42383L);
        put("12EDAA9CA224D529:bigreactors:ludicrite_block:1363933851875071273", 42384L);
        put("3CE059588A2A9A97:patchouli:guide_book:4386604273868905111", 42385L);
        put("2DA0BD387098BB09:projecte:red_matter:3287835778085337865", 42386L);
        put("1E2ED0015185D23E:Wireless Power!:2174904374147797566", 42387L);
        put("3F9197AAD4C688A5:powah:battery_spirited:4580609055982520485", 42388L);
        put("0AC66AFEA56D0F81:mysticalagriculture:air_seeds:776425627697614721", 42389L);
        put("7B2BB48F9AB28BC6:1k Storage:8875386019532409798", 42390L);
        put("2613CCF6E2B3A19F:mysticalagriculture:inferium_growth_accelerator:2743761958736208287", 42391L);
        put("6C87B43D15407718:storagedrawers:iron_storage_upgrade:7820417452394706712", 42392L);
        put("6597118149478700:solarflux:sp_5:7320338966291908352", 42393L);
        put("225CCF5803299B49:thermal:machine_sawmill:2476081872064060233", 42394L);
        put("0698A40FAC316CF7:mekanism:advanced_control_circuit:475310147907972343", 42395L);
        put("77D847CEB6743643:thermal:machine_crafter:8635731238632830531", 42396L);
        put("080262166E7887C3:16k Storage:577131550739040195", 42397L);
        put("63C16B115A6500B6:chipped:carpenters_table:7188144202535207094", 42398L);
        put("735D4596340E6B02:redstone_arsenal:flux_excavator:8312876998616574722", 42399L);
        put("28F3AB28A0160B7F:betterfurnacesreforged:gold_furnace:2950890371830647679", 42400L);
        put("3002A490F28BEF65:botania:elementium_ingot:3459508406225465189", 42401L);
        put("3427FD33D5880D1B:ad_astra:tier_2_rocket:3758250813131590939", 42402L);
        put("71D96D724B65F94E:cyclic:ender_bag:8203708533890742606", 42403L);
        put("10ADA8A01306DAEC:pneumaticcraft:liquid_compressor:1201802081045240556", 42404L);
        put("27D0E09429A5BC58:storagedrawers:diamond_storage_upgrade:2869039889593515096", 42405L);
        put("7845C0F597E6BC20:botania:green_petal:8666545218992389152", 42406L);
        put("283C06398F6AAF86:Automation!:2899199104408792966", 42407L);
        put("31A6D9ACF02F1721:mob_grinding_utils:nutritious_chicken_feed:3577786290779658017", 42408L);
        put("717674BB18DF203E:redstone_arsenal:flux_axe:8175850520462696510", 42409L);
        put("3B0F48FAF8946326:powah:crystal_niotic:4255700415637906214", 42410L);
        put("54080968CFFFB899:thermal:dynamo_stirling:6055100044770523289", 42411L);
        put("7AC647D823CD6523:immersiveengineering:light_engineering:8846837511655089443", 42412L);
        put("69021BE4507E3022:ironchests:diamond_chest:7566640991352795170", 42413L);
        put("1BA725CC0AC2D0E2:ad_astra:nasa_workbench:1992602918418829538", 42414L);
        put("56269978214E1D5A:patchouli:guide_book:62078178770D3C589B358447AB610700122", 42415L);
        put("4FBABE3D59B8E9B5:Drive:5745113445372062133", 42416L);
        put("55F91BC03B407F6C:ad_astra:ostrum_plate:6195013274866581356", 42417L);
        put("46DCFABCE6CA89BC:projectexpansion:transmutation_interface:5106231766764128700", 42418L);
        put("1FB5B6707BBFB0CC:mysticalagriculture:prudentium_essence:2284932980189147340", 42419L);
        put("72461EE6B85195DF:Niotic:8234302944977196511", 42420L);
        put("01CCC0C8CD852680:betterfurnacesreforged:copper_forge:129690457960949376", 42421L);
        put("0DCB380AD60B3615:mekanismgenerators:heat_generator:993949761957213717", 42422L);
        put("009DC6680176855E:undergarden:utherium_sword:44409721347016030", 42423L);
        put("04E06C50ED42364D:mysticalagradditions:supremium_paxel:351399865768621645", 42424L);
        put("5705A80B7A875A15:mekanismtools:osmium_paxel:6270602823437081109", 42425L);
        put("15C0A3A1B2535ECA:extendedcrafting:ultimate_catalyst:1567432585201802954", 42426L);
        put("7CA502DB7EC311B0:patchouli:guide_book:8981588173608128944", 42427L);
        put("09801AD3826251FF:industrialforegoing:mycelial_magma:684576639088218623", 42428L);
        put("3FA2C4EFEA686EF2:botanypots:terracotta_botany_pot:4585443905325526770", 42429L);
        put("1293A7D5A06C73C7:mekanismtools:refined_obsidian_paxel:1338598050220504007", 42430L);
        put("6075AD857006EFC7:Frequently Asked Questions:6950652388537987015", 42431L);
        put("0B20B3F2EE78FFB5:immersiveengineering:rs_engineering:801838589636313013", 42432L);
        put("07ABF96BA0FFD5BE:solarflux:sp_6:552809619940955582", 42433L);
        put("61E14F41995942B4:projecte:white_alchemical_bag:7053005634603139764", 42434L);
        put("5D371B75E22515F7:draconicevolution:chaotic_chestpiece:6716867562365588983", 42435L);
        put("42CEB2E7C533F796:mekanismgenerators:bio_generator:4813981760221345686", 42436L);
        put("60E0858BEF4F9702:mekanism:combiner:6980726258486187778", 42437L);
        put("04CD02B4D3E60A08:mob_grinding_utils:rotten_egg:345935722049833480", 42438L);
        put("030AD281CFAFB320:Infinity Tools:219218986857902880", 42439L);
        put("3C94C3249C635B5B:enderio:staff_of_travelling:4365328500838849371", 42440L);
        put("4F9D02ABF81633F3:cyclic:apple_chocolate:5736744437964747763", 42441L);
        put("035D7EEE54AED6E1:projecte:transmutation_table:242489517035935457", 42442L);
        put("1BE1BDAF0936A984:cyclic:apple_ender:2009095468255455620", 42443L);
        put("3D514FFE4B4FABD0:mysticalagriculture:uraninite_seeds:4418400663030967248", 42444L);
        put("1111A4212E59B734:mysticalagriculture:imperium_essence:1229944635667363636", 42445L);
        put("234E84DB362CCB37:mysticalagradditions:awakened_supremium_paxel:2544116916552518455", 42446L);
        put("0E5632983BB97532:mob_grinding_utils:saw:1033068793946535218", 42447L);
        put("321D17BD61F59DE0:enderio:powered_spawner:3611068578380750304", 42448L);
        put("644D6BC9C97B7E0F:Basic Reactor:7227551491412426255", 42449L);
        put("4A224F9E3C15B5C8:storagedrawers:controller:5341919649046312392", 42450L);
        put("2297686A02AF6553:ironchests:obsidian_chest:2492575723293730131", 42451L);
        put("0EE4D5145E92D9F7:botania:creative_pool:1073216894683765239", 42452L);
        put("1D639216999D7353:minecraft:ghast_tear:2117696875558433619", 42453L);
        put("769DBFCAAF546990:storagedrawers:quantify_key:8547198545064913296", 42454L);
        put("50A2E2E8521749A7:Diamond:5810455956698909095", 42455L);
        put("59FF5F7CF04A2152:mysticalagradditions:tertium_paxel:6485006978648777042", 42456L);
        put("05451426126D87CA:pneumaticcraft:pressure_tube:379731897333155786", 42457L);
        put("601002BDDC947ABD:draconicevolution:wyvern_chestpiece:6922035641741245117", 42458L);
        put("632DBB9D66CB2457:hostilenetworks:nether_prediction:7146574468415693911", 42459L);
        put("4B5C56B584011078:ad_astra:desh_plate:5430310588305707128", 42460L);
        put("29AE56F399A8006A:storagedrawers:obsidian_storage_upgrade:3003433605757665386", 42461L);
        put("30C0E7E9D6D89F39:256M Storage:3513062700866903865", 42462L);
        put("2AFC300B86665BAB:enderio:double_layer_capacitor:3097403469781687211", 42463L);
        put("60B7F429BAE6E5EF:Gold:6969307408445662703", 42464L);
        put("146CF4A0D8FF14D3:hostilenetworks:loot_fabricator:1471820149915849939", 42465L);
        put("52AF7D2570BA5694:thermal:machine_press:5958118431792977556", 42466L);
        put("77D5B7D22F8D1B8E:minecraft:blaze_rod:8635009973921586062", 42467L);
        put("42E5E12B9230AD31:cookingforblockheads:crafting_book:4820506553422490929", 42468L);
        put("01AF9DC9312D2F79:pneumaticcraft:advanced_pressure_tube:121489202401324921", 42469L);
        put("3FC54D16CC751DE7:exdeorum:string_mesh:4595163755116305895", 42470L);
        put("744F2912E50C98B4:Creative Augments:8380962592688740532", 42471L);
        put("78F681277CF86ACC:chipped:loom_table:8716296135422536396", 42472L);
        put("182CA53075F91A01:ad_astra:tier_1_rover:1741948783441811969", 42473L);
        put("18A1A7997D19A8F2:mob_grinding_utils:fan_upgrade_height:1774884005831354610", 42474L);
        put("574E54FFCA5C2F39:extendedcrafting:basic_table:6291059187071594297", 42475L);
        put("22AEDCCADAFFAC5A:botania:manasteel_ingot:2499177607052897370", 42476L);
        put("781637324948FC00:pneumaticcraft:empty_pcb:8653164423156399104", 42477L);
        put("728C5C3D9B9A4343:cyclic:apple_emerald:8254073636736615235", 42478L);
        put("20B890FD3E291F76:Silicon:2357793822272462710", 42479L);
        put("724EDABFE1DBE350:solarflux:dispersive_upgrade:8236761286170108752", 42480L);
        put("274C14C7C62D48A8:Crafting:2831661113965103272", 42481L);
        put("097C1F33625F658D:betterfurnacesreforged:diamond_furnace:683455549007685005", 42482L);
        put("561A5FF8F7E1F164:ironchests:iron_chest:6204376959571587428", 42483L);
        put("30E74CBC52DA2003:Wireless Access:3523869605168291843", 42484L);
        put("7AC2F14B5B3E0EBF:betterfurnacesreforged:extreme_furnace:8845897924064710335", 42485L);
        put("6D70C11E6989DFBC:powah:player_aerial_pearl:7886015283889561532", 42486L);
        put("602AC0D72EC760E3:ironjetpacks:capacitor:6929563007098249443", 42487L);
        put("30578613220E2188:mekanism:chemical_injection_chamber:3483400253528220040", 42488L);
        put("0D6E9FD75AE47D03:storagedrawers:oak_half_drawers_1:967886717222944003", 42489L);
        put("2249AFBFF8152AC5:storagedrawers:drawer_key:2470699109625178821", 42490L);
        put("005B1AF7044969FA:pneumaticcraft:manual_compressor:25643871111834106", 42491L);
        put("1EDC66EC5016C09E:powah:energy_discharger_starter:2223765481156165790", 42492L);
        put("5D6289BEC50F8A91:redstone_arsenal:flux_hammer:6729092245687863953", 42493L);
        put("174722A56CE77CB2:extendedcrafting:compressor:1677347480110857394", 42494L);
        put("33477B5891C0B36B:betterfurnacesreforged:gold_forge:3695057639613444971", 42495L);
        put("1E2B4C7819C153C0:Spirited:2173915323848283072", 42496L);
        put("22221F35326DD805:ad_astra:oxygen_distributor:2459562659837499397", 42497L);
        put("49117C982012A5F6:compacter:compacter:5265126432187786742", 42498L);
        put("674BCF75C9639981:betterfurnacesreforged:iron_furnace:7443270913956747649", 42499L);
        put("2CD626E3DA24914E:projecte:condenser_mk2:3230812542744170830", 42500L);
        put("7041A7651D54436A:mysticalagriculture:supremium_growth_accelerator:8088930458459718506", 42501L);
        put("0C20C02B76C10512:mysticalagriculture:dragon_egg_seeds:873909620618364178", 42502L);
        put("24F3DD2D706EBFB6:3x3 Pressure Chamber:2662714991935668150", 42503L);
        put("4CACD99EBC5734B6:cyclic:fire_killer:5525030118664647862", 42504L);
        put("19297AF6271B1473:mysticalagriculture:fire_seeds:1813115522629964915", 42505L);
        put("0A0F1B092A568F5B:ad_astra:launch_pad:724827791208910683", 42506L);
        put("7FDCFD25D848F2C7:mob_grinding_utils:saw_upgrade_beheading:9213517276677468871", 42507L);
        put("3279F0A5EC784995:mekanism:elite_control_circuit:3637202769505962389", 42508L);
        put("522ABEF3993C693B:cyclic:apple_bone:5920754613589141819", 42509L);
        put("68E323E78BBDB860:pneumaticcraft:plastic:7557924077046708320", 42510L);
        put("403922E054130670:Into the Nether:4627768438978446960", 42511L);
        put("7671F5CD5D0428F2:thermal:machine_pyrolyzer:8534873031221455090", 42512L);
        put("12F74E49F266865A:mysticalagriculture:inferium_chestplate:1366647091436619354", 42513L);
        put("4A010F0546C95882:botania:mana_spreader:5332559949120231554", 42514L);
        put("6BC096C9F0BE3611:industrialforegoing:mycelial_netherstar:7764371551658325521", 42515L);
        put("140BD22ACE0A9E31:botania:gray_petal:1444479186789637681", 42516L);
        put("2790481898D18B77:solarflux:sp_7:2850857834605808503", 42517L);
        put("6E285CDF62E9EBB8:mysticalagriculture:supremium_chestplate:7937696457747459000", 42518L);
        put("551314254FBE6758:botania:pink_petal:6130265668265600856", 42519L);
        put("2EF5F87F9ED8BDF9:cyclic:apple_lofty_stature:3383883922048335353", 42520L);
        put("5C7DC5405B9EAA96:storagedrawers:void_upgrade:6664699903783905942", 42521L);
        put("110127114AE2FDA9:enderio:pulsating_photovoltaic_module:1225303528845802921", 42522L);
        put("0B2BCEBBE9C3FC40:betterfurnacesreforged:ultimate_furnace:804964264891907136", 42523L);
        put("0CF8AF74D606289A:extendedcrafting:redstone_catalyst:934689839021172890", 42524L);
        put("12DC5146FBF6131B:trashcans:item_trash_can:1359050552875815707", 42525L);
        put("3FB9FD00EF091C52:powah:magmator_starter:4591979475533110354", 42526L);
        put("3E8681CB5EAE6368:botania:mana_pylon:4505431187697591144", 42527L);
        put("45B40527613AF29C:exdeorum:porcelain_bucket:5022645151118062236", 42528L);
        put("2E1AF16970EDF2BB:cyclic:apple_iron:3322233110307664571", 42529L);
        put("18511D1BCCA33A8B:ad_astra:tier_3_rocket:1752213735258405515", 42530L);
        put("59F498EBB5FE2C2C:storagedrawers:compacting_drawers_3:6481973901831056428", 42531L);
        put("07E82AE7818BF3E8:1M Storage:569752526661612520", 42532L);
        put("3895713C1C5AE788:mekanismgenerators:wind_generator:4077289540641548168", 42533L);
        put("0B4FE52E8C83C177:constructionwand:infinity_wand:815122045666050423", 42534L);
        put("2EAEA784ACD625B6:cobblefordays:tier_2:3363810159969576374", 42535L);
        put("3105EE93AE0C62BF:cobblefordays:tier_3:3532491800789672639", 42536L);
        put("5B1AD78B32132907:projecte:condenser_mk1:6564796399686461703", 42537L);
        put("691B800BAABBCE37:ad_astra:compressor:7573787985951051319", 42538L);
        put("71AFD1B1BAA2D468:industrialforegoing:machine_frame_advanced:8191996808480871528", 42539L);
        put("7809066C248C37AD:Digital Storage:8649451620881151917", 42540L);
        put("51D260302DDC90F1:extendedcrafting:ultimate_table:5895880622225527025", 42541L);
        put("4ED75332CE21FB62:undergarden:forgotten_sword:5681100932622973794", 42542L);
        put("0AB165272BEAEE3A:rftoolsutility:crafter2:770508230172012090", 42543L);
        put("553EB2BD34769B0A:mekanism:ultimate_energy_cube:6142543467478686474", 42544L);
        put("72B30CE3A543089C:solarflux:furnace_upgrade:8264963913024735388", 42545L);
        put("4D2125287B8D730D:powah:furnator_starter:5557764270953689869", 42546L);
        put("3FC496674B2D2961:projecte:rm_sword:4594962890211797345", 42547L);
        put("4E39823E3BB2A755:projecte:alchemical_chest:5636679612432033621", 42548L);
        put("21B24ABB821326B6:industrialforegoing:fluid_extractor:2428085318307751606", 42549L);
        put("61DE6F3118A9FEC7:draconicevolution:wyvern_core:7052196323166650055", 42550L);
        put("254AD3E776A4A6C2:botania:fertilizer:2687193118761330370", 42551L);
        put("7CA5E6793BD892A5:botania:blue_petal:8981838440229802661", 42552L);
        put("25C913FA324A3278:bigreactors:cyanite_ingot:2722729415028650616", 42553L);
        put("3966F96406BF7DF4:mob_grinding_utils:absorption_upgrade:4136267515791638004", 42554L);
        put("7FEFE2387B9C77E4:exdeorum:iron_mesh:9218835694470592484", 42555L);
        put("01A19C42EDC32E35:cyclic:heart:117546876559109685", 42556L);
        put("47FCC3324190FA2A:mob_grinding_utils:saw_upgrade_fire:5187235491439770154", 42557L);
        put("29AFE089E6E4790B:extendedcrafting:ender_crafter:3003866359368284427", 42558L);
        put("75729BFC02172F7E:Reinforced Reactor:8462998156451721086", 42559L);
        put("55505B2AAB72D0ED:powah:crystal_spirited:6147513730183909613", 42560L);
        put("192279D2382F35DD:enderio:sag_mill:1811143943949071837", 42561L);
        put("521F17E3A2F13B48:draconicevolution:draconium_core:5917474701846854472", 42562L);
        put("429C156DA815B9AC:extendedcrafting:elite_catalyst:4799734863585720748", 42563L);
        put("1F0C090AFA116B94:Universal Wireless:2237173057646062484", 42564L);
        put("74A2874A636757AA:mysticalagriculture:tertium_chestplate:8404428608191813546", 42565L);
        put("5BF7D180B44F673D:botania:lime_petal:6626995727410095933", 42566L);
        put("5D7D2A4C3BA37750:Into the Aether:6736587124522579792", 42567L);
        put("413D5F6672C5B552:Sprinklers!:4701018479661528402", 42568L);
        put("05277533590463A1:chipped:mason_table:371394357678597025", 42569L);
        put("184E912DACA79A0A:4M Storage:1751496930449922570", 42570L);
        put("29FFD5CE69AB6A31:mekanismgenerators:solar_generator:3026372557129083441", 42571L);
        put("4204E62CC213F589:mekanism:precision_sawmill:4757180186319123849", 42572L);
        put("7833ADC8AD653FEA:Transfer Nodes:8661457585777754090", 42573L);
        put("5EAA0221EABDA255:mekanism:digital_miner:6821266930301510229", 42574L);
        put("4C4BFB01B08B9B41:mysticalagriculture:flux_infused_gem_seeds:5497763754811300673", 42575L);
        put("11B0D33506A1E4B7:Mechanical Hammer:1274750919243850935", 42576L);
        put("4BB8FBE275D2AE7F:Pipez:5456387898617278079", 42577L);
        put("3C917DD1175757FB:powah:thermo_generator_starter:4364407850891958267", 42578L);
        put("51F3D3ABAD3D00CC:Infinity Armor:5905296270712176844", 42579L);
        put("1722CE66E8CA2EFF:thermal:dynamo_compression:1667121753468055295", 42580L);
        put("2E3936DE3FD51B85:Infinity Booster:3330753727598762885", 42581L);
        put("31A628554F318833:botania:pure_daisy:3577591300858415155", 42582L);
        put("4C60C76148FDA511:bigreactors:ridiculite_block:5503617965297083665", 42583L);
        put("7DFCD91873E59B94:projecte:dm_sword:9078369647918947220", 42584L);
        put("72E4E8FA5A17242D:thermal:machine_insolator:8278998176964748333", 42585L);
        put("73FED8E380B82EDE:extendedcrafting:crafting_core:8358356430074949342", 42586L);
        put("735C356FC487F1B9:botania:light_blue_petal:8312577766373978553", 42587L);
        put("546C44F3B917BB85:Creative Items:6083313010243779461", 42588L);
        put("69912119C98D6247:Conquor the Twilight:7606897640244863559", 42589L);
        put("6A16098F8D637340:thermal:machine_crystallizer:7644307929665073984", 42590L);
        put("2D42B0ECBDAA096C:mob_grinding_utils:fan_upgrade_width:3261363611010468204", 42591L);
        put("419BE58F0847E557:Dimension Creation:4727624636337481047", 42592L);
        put("7F51221B616F8DD5:pneumaticcraft:creative_compressor:9174151416924573141", 42593L);
        put("1E19F85B406BFC9D:betterfurnacesreforged:netherhot_furnace:2169037766362135709", 42594L);
        put("30087CE07AD56C2E:mekanism:osmium_compressor:3461153617209551918", 42595L);
        put("4658F0B4AEC9C492:ad_astra:coal_generator:5069066039422731410", 42596L);
        put("5EFC686F406A2689:storagedrawers:oak_half_drawers_2:6844460360727668361", 42597L);
        put("36C9019C18E3D598:industrialforegoing:dryrubber:3947688318311060888", 42598L);
        put("00B0AA8BE2B0C31C:ad_astra:solar_panel:49727113681486620", 42599L);
        put("545FA0E3602EDE82:Wireless Player Power:6079754920405163650", 42600L);
        put("08A7FD61246B76D6:bigreactors:blutonium_ingot:623745667078780630", 42601L);
        put("5B196B44909E59C9:mysticalagriculture:imperium_growth_accelerator:6564395874097453513", 42602L);
        put("54FF1E782343EAAF:mekanism:ultimate_control_circuit:6124647519583726255", 42603L);
        put("18884245807D016C:powah:battery_niotic:1767735720018772332", 42604L);
        put("09A076E82128DC19:Blazing:693685081975872537", 42605L);
        put("12612B561581014B:mysticalagriculture:prosperity_shard:1324387414151594315", 42606L);
        put("558C2A6AAA467ED1:projecte:dm_pick:6164348627575013073", 42607L);
        put("24DBF5D8CCA1CE79:Basic Watering Can:2655986716759936633", 42608L);
        put("6450516E0A3A74DF:mekanism:ingot_osmium:7228366934989501663", 42609L);
        put("74AC6573C1A806BE:hostilenetworks:blank_data_model:8407206152238532286", 42610L);
        put("2213ACF90E286069:projecte:rm_pick:2455496407531413609", 42611L);
        put("29A2B58D200D65F9:mysticalagriculture:awakened_supremium_essence:3000159919514936825", 42612L);
        put("30DA803A12E35624:Cobblestone and Lava Generation:3520267045656811044", 42613L);
        put("1F8E2FAFA333804D:mysticalagriculture:prudentium_chestplate:2273807293272522829", 42614L);
        put("1F4B7FC6646657F3:industrialforegoing:machine_frame_supreme:2255036528493746163", 42615L);
        put("6BE482A7F8998527:enderio:soul_binder:7774482514690278695", 42616L);
        put("62E43B019BE82738:botania:purple_petal:7125885388503656248", 42617L);
        put("3ECBDF981EE1ADEC:botania:yellow_petal:4524956095069924844", 42618L);
        put("16B57C4BD4C33843:storagedrawers:oak_full_drawers_2:1636350704752998467", 42619L);
        put("08C08FE6676AF787:constructionwand:core_angel:630662167572182919", 42620L);
        put("6C675D88FE73ADE6:mekanism:metallurgic_infuser:7811314921663344102", 42621L);
        put("725C86A23081A0F6:avaritia:infinity_ingot:8240609449337790710", 42622L);
        put("0299EC5E270E0F04:exdeorum:golden_hammer:187440748638899972", 42623L);
        put("04846D1F54DF981C:mysticalagriculture:infusion_altar:325505054412871708", 42624L);
    }};

    private static final List<String> QUEST_TO_KEEP_REWARDS = List.of(
            "02A82C7E4F943C19"
    );


    public QuestManager() {
        onQuest.onInitialize();
        quests = new HashMap<>();
    }

    public void initializeQuests() {
        try {
            baseQuestFile = FTBQuestsAPI.api().getQuestFile(false);
        } catch (Exception e) {
            Utils.sendMessageToAll(e.getMessage());
        }

        if (baseQuestFile != null) {
            for (QuestObjectBase object : baseQuestFile.getAllObjects()) {
                if (object instanceof Quest) {
                    //Utils.sendMessageToAll(String.valueOf(object.id));
                    if (!((Quest) object).canBeRepeated())
                        quests.put(object.id, object.getQuestFile().getQuest(object.id));
                }
            }
            removeUnwantedRewards(baseQuestFile);
        } else {
            Utils.sendMessageToAll("QuestFile is null");
        }

    }
    public void removeUnwantedRewards(BaseQuestFile questFile) {
        for (Chapter chapter : questFile.getAllChapters()) {
            for (Quest quest : chapter.getQuests()) {
                if (QUEST_TO_KEEP_REWARDS.contains(quest.toString())) {
                    continue;
                }
                List<Reward> rewardToRemove = new ArrayList<>(quest.getRewards());
                for (Reward reward : rewardToRemove){
                    quest.removeReward(reward);
                    Utils.sendMessageToAll("reward removed " + reward.getRawTitle());
                }
            }
        }
    }


    public Long getQuestId(Long questId) {
        if (questData.keySet().stream().anyMatch(key -> {
            String[] parts = key.split(":");
            if (parts.length >= 4){
                try {
                    long KeyId = Long.parseLong(parts[3]);
                    return KeyId == questId;
                }catch (NumberFormatException e){
                    return false;
                }
            }
            return false;
        })){
            return questData.entrySet().stream()
                    .filter(entry -> {
                        // Split the key by ':' and check if the last part (ID) matches
                        String[] parts = entry.getKey().split(":");
                        if (parts.length >= 4) {
                            try {
                                long keyId = Long.parseLong(parts[3]);
                                return keyId == questId;
                            } catch (NumberFormatException e) {
                                // If parsing fails, return false
                                return false;
                            }
                        }
                        return false;
                    })
                    .map(Map.Entry::getValue)  // Get the value corresponding to the matching key
                    .findFirst()  // We only need the first match
                    .orElse(null);
        }
        return 0L;
    }

    public boolean hasQuest(Long questId) {
        return completedQuests.contains(getQuestId(questId));
    }

    public void addQuest(Long id) {
        completedQuests.add(id);
        boolean response = APRandomizer.getAP().checkLocation(id);
        if (response){
            Utils.sendMessageToAll("Able to send Quest");
        }else{
            Utils.sendMessageToAll("Wasn't able to send quest");
        }
        APRandomizer.getGoalManager().updateGoal(true);
        APRandomizer.getWorldData().addLocation(id);
        syncAllQuests();
    }

    public void resendQuests() {
        for (Long completedQuest : completedQuests) {
            APRandomizer.getAP().checkLocation(completedQuest);
        }
    }
    public void syncQuest(Quest q) {
    }

    public void syncAllQuests() {
        for (Quest q : quests.values()) {
            syncQuest(q);
        }
    }

    public int getFinishedAmount() {
        return completedQuests.size();
    }

    public void setCheckedQuests(Set<Long> checkedLocations) {
        completedQuests.addAll(checkedLocations);
        WorldData data = APRandomizer.getWorldData();
        for (var checkedLocation : checkedLocations){
            data.addLocation(checkedLocation);
        }
        syncAllQuests();
    }
}
