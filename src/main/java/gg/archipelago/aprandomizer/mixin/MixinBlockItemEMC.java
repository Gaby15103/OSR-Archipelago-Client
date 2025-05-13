package gg.archipelago.aprandomizer.mixin;

import gg.archipelago.aprandomizer.APRandomizer;
import moze_intel.projecte.gameObjs.container.inventory.TransmutationInventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
    not functional but will be maybe added later, I think emc can be a good way to advance fast when unlocked
 */
@Mixin(TransmutationInventory.class)
public class MixinBlockItemEMC {

    @Unique
    private static final Logger oSR_Archipelago_Client$LOGGER = LogManager.getLogger();

    @Inject(method = "handleKnowledge*", at = @At("HEAD"), cancellable = true, remap = false)
    private void blockCertainItems(ItemStack stack, boolean consumeEMC, CallbackInfo ci) {
        // Log the item to see what we're working with
        if (stack == null || stack.isEmpty()) {
            oSR_Archipelago_Client$LOGGER.warn("Received empty or null stack, skipping.");
            return;  // Skip processing if the stack is empty or null
        }

        oSR_Archipelago_Client$LOGGER.info("Checking if item is locked: {}", stack.getItem().toString());

        if (APRandomizer.getRecipeManager().isRecipeLocked(stack.getItem())) {
            oSR_Archipelago_Client$LOGGER.info("Item is locked, canceling method call.");
            ci.cancel(); // Cancel the method if the recipe is locked
        }
    }
}
