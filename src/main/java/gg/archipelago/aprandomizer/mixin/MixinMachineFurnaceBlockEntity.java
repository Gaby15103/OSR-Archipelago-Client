package gg.archipelago.aprandomizer.mixin;

import cofh.lib.common.inventory.ItemStorageCoFH;
import cofh.thermal.expansion.common.block.entity.machine.MachineCrafterBlockEntity;
import cofh.thermal.expansion.common.block.entity.machine.MachineFurnaceBlockEntity;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Mixin(MachineFurnaceBlockEntity.class)
public class MixinMachineFurnaceBlockEntity {
    @Unique
    private static final Logger oSR_Archipelago_Client$LOGGER = LogManager.getLogger();

    @Inject(method = "cacheRecipe", at = @At("HEAD"), cancellable = true, remap = false)
    private void afterSetRecipe(CallbackInfoReturnable<Boolean> cir) {
        MachineFurnaceBlockEntity self = (MachineFurnaceBlockEntity) (Object) this;

        try {
            Field inputSlotField = MachineFurnaceBlockEntity.class.getDeclaredField("inputSlot");
            inputSlotField.setAccessible(true);
            ItemStorageCoFH inputSlot = (ItemStorageCoFH) inputSlotField.get(self);

            ItemStack inputStack = inputSlot.getItemStack();


            List<SmeltingRecipe> smeltingRecipes = APRandomizer.getServer()
                    .getRecipeManager()
                    .getAllRecipesFor(RecipeType.SMELTING);

            for (SmeltingRecipe recipe : smeltingRecipes) {
                if (recipe.getType() == RecipeType.SMELTING){
                    if (recipe.getIngredients().get(0).test(inputStack)) {
                        if (APRandomizer.getRecipeManager().isRecipeLocked(recipe.getId())) {
                            oSR_Archipelago_Client$LOGGER.debug("Blocked smelting recipe for locked output: {}", recipe.getId());
                            cir.setReturnValue(false);
                            return;
                        }
                    }
                }
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            oSR_Archipelago_Client$LOGGER.error("Failed to access outputSlot in MachineFurnaceBlockEntity", e);
        }
    }
}
