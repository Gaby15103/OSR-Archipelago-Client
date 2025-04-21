package gg.archipelago.aprandomizer.mixin;

import gg.archipelago.aprandomizer.APRandomizer;
import mekanism.api.recipes.cache.CachedRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CachedRecipe.class)
public abstract class MixinCachedRecipe {

    @Inject(method = "process", at = @At("HEAD"), cancellable = true, remap = false)
    private void lockRecipeProcessing(CallbackInfo ci) {
        CachedRecipe<?> recipe = (CachedRecipe<?>) (Object) this;
        if (APRandomizer.getRecipeManager().isRecipeLocked(recipe.getRecipe().getId())) {
            ci.cancel();
        }
    }
}
