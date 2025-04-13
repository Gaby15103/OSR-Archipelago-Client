package gg.archipelago.aprandomizer.mixin;

import com.enderio.machines.common.blockentity.task.host.CraftingMachineTaskHost;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(CraftingMachineTaskHost.class)
public class MixinCraftingMachineTaskHost {
    // directly reference a log4j logger.
    @Unique
    private static final Logger oSR_Archipelago_Client$LOGGER = LogManager.getLogger();

    @Inject(
            method = "findRecipe",
            at = @At("RETURN"),
            cancellable = true,
            remap = false
    )
    private void onFindRecipe(CallbackInfoReturnable<Optional<?>> cir) {
        Optional<?> recipe = cir.getReturnValue();
        if (recipe.isPresent()){
            if (recipe.get() instanceof Recipe<?> r) {
                if (APRandomizer.getRecipeManager().isRecipeLocked(r.getId())) {
                    cir.setReturnValue(Optional.empty());
                }
            }
        }

    }
}