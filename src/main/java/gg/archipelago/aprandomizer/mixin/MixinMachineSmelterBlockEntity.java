package gg.archipelago.aprandomizer.mixin;

import cofh.thermal.expansion.common.block.entity.machine.MachineSmelterBlockEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MachineSmelterBlockEntity.class)
public abstract class MixinMachineSmelterBlockEntity {
    @Unique
    private static final Logger oSR_Archipelago_Client$LOGGER = LogManager.getLogger();

    @Unique
    private static boolean oSR_Archipelago_Client$hasWarned = false;

    @Inject(method = "validateInputs", at = @At("HEAD"), cancellable = true, remap = false)
    private void validateInputs(CallbackInfoReturnable<Boolean> cir) {
        if (!oSR_Archipelago_Client$hasWarned) {
            oSR_Archipelago_Client$LOGGER.warn("Archipelago: The Smelter is disabled.");
            oSR_Archipelago_Client$hasWarned = true;
        }

       cir.setReturnValue(false);
    }
}
