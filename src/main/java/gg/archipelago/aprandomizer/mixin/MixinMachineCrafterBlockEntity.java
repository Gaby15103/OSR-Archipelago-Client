package gg.archipelago.aprandomizer.mixin;

import cofh.thermal.expansion.common.block.entity.machine.MachineCrafterBlockEntity;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.reflect.Field;

@Mixin(MachineCrafterBlockEntity.class)
public abstract class MixinMachineCrafterBlockEntity {

    @Unique
    private static final Logger oSR_Archipelago_Client$LOGGER = LogManager.getLogger();

    @Inject(method = "cacheRecipe", at = @At("TAIL"), cancellable = true, remap = false)
    private void afterSetRecipe(CallbackInfoReturnable<Boolean> cir) {
        MachineCrafterBlockEntity self = (MachineCrafterBlockEntity) (Object) this;

        try {
            Field craftResultField = MachineCrafterBlockEntity.class.getDeclaredField("craftResult");
            craftResultField.setAccessible(true);
            ResultContainer craftResult = (ResultContainer) craftResultField.get(self);
            ItemStack result = craftResult.getItem(0);
            if (APRandomizer.getRecipeManager().isRecipeLocked(result.getItem())) {
                cir.setReturnValue(false);
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            oSR_Archipelago_Client$LOGGER.error("Failed to access craftResult in MachineCrafterBlockEntity", e);
        }
    }
}
