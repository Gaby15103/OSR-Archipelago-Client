package gg.archipelago.aprandomizer.mixin;

import appeng.crafting.pattern.EncodedPatternItem;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EncodedPatternItem.class)
public abstract class MixinEncodedPatternItem {

    @Inject(method = "getOutput", at = @At("RETURN"), cancellable = true, remap = false)
    public void injectGetOutput(ItemStack item, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack originalOutput = cir.getReturnValue();

        if (APRandomizer.getRecipeManager().isRecipeLocked(originalOutput.getItem())) {
            cir.setReturnValue(ItemStack.EMPTY);
        } else {
            cir.setReturnValue(originalOutput);
        }
    }
}

