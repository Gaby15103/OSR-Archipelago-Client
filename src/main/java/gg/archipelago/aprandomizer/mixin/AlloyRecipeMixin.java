package gg.archipelago.aprandomizer.mixin;

import blusunrize.immersiveengineering.api.crafting.AlloyRecipe;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(AlloyRecipe.class)
public class AlloyRecipeMixin {

    @Inject(
            method = "findRecipe",
            at = @At("RETURN"),
            cancellable = true,
            remap = false
    )
    private static void apr$onFindRecipe(Level level, ItemStack input0, ItemStack input1, @Nullable AlloyRecipe hint, CallbackInfoReturnable<AlloyRecipe> cir) {
        AlloyRecipe result = cir.getReturnValue();
        if (result != null && APRandomizer.getRecipeManager().isRecipeLocked(result.getId())) {
            cir.setReturnValue(null);
        }
    }
}
