package gg.archipelago.aprandomizer.mixin;

import blusunrize.immersiveengineering.api.crafting.BlastFurnaceRecipe;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(BlastFurnaceRecipe.class)
public class BlastFurnaceRecipeMixin {

    @Inject(
            method = "findRecipe",
            at = @At("RETURN"),
            cancellable = true,
            remap = false
    )
    private static void apr$onFindRecipe(Level level, ItemStack input, @Nullable BlastFurnaceRecipe hint, CallbackInfoReturnable<BlastFurnaceRecipe> cir) {
        BlastFurnaceRecipe result = cir.getReturnValue();
        if (result != null && APRandomizer.getRecipeManager().isRecipeLocked(result.getId())) {
            cir.setReturnValue(null);
        }
    }
}
