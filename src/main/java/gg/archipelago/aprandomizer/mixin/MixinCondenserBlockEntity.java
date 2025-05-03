package gg.archipelago.aprandomizer.mixin;

import gg.archipelago.aprandomizer.APRandomizer;
import moze_intel.projecte.api.ItemInfo;
import moze_intel.projecte.gameObjs.block_entities.CondenserBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CondenserBlockEntity.class)
public class MixinCondenserBlockEntity {
    @Inject(method = "condense", at = @At("HEAD"), cancellable = true, remap = false)
    private void aprandomizer$blockCondenseForLockedRecipes(CallbackInfo ci) {
        CondenserBlockEntity self = (CondenserBlockEntity)(Object)this;
        ItemInfo lockInfo = self.getLockInfo();
        if (lockInfo != null && APRandomizer.getRecipeManager().isRecipeLocked(lockInfo.createStack().getItem())) {
            ci.cancel();
        }
    }

    @Inject(method = "pushStack", at = @At("HEAD"), cancellable = true, remap = false)
    private void aprandomizer$blockPushLockedItem(CallbackInfo ci) {
        CondenserBlockEntity self = (CondenserBlockEntity)(Object)this;
        ItemInfo lockInfo = self.getLockInfo();
        if (lockInfo != null && APRandomizer.getRecipeManager().isRecipeLocked(lockInfo.createStack().getItem())) {
            ci.cancel();
        }
    }
}
