package gg.archipelago.aprandomizer.mixin;

import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import wily.betterfurnaces.blockentity.SmeltingBlockEntity;

@Mixin(SmeltingBlockEntity.class)
public class MixinBetterFurnaceBlockEntity {

    @Inject(method = "canSmelt", at = @At("RETURN"), cancellable = true, remap = false)
    private void aprandomizer$blockLockedSmelting(@Nullable Recipe<?> recipe, int INPUT, int OUTPUT, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue() || recipe == null) return;

        if (APRandomizer.getRecipeManager().isRecipeLocked(recipe.getId())) {
            cir.setReturnValue(false);
        }
    }
}
