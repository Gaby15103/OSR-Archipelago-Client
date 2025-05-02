package gg.archipelago.aprandomizer.mixin;

import gg.archipelago.aprandomizer.APRandomizer;
import moze_intel.projecte.gameObjs.block_entities.DMFurnaceBlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DMFurnaceBlockEntity.class)
public class MixinDMFurnaceBlockEntity {
    @Inject(method = "getSmeltingResult", at = @At("RETURN"), cancellable = true, remap = false)
    private void blockUnauthorizedRecipes(ItemStack in, CallbackInfoReturnable<ItemStack> cir) {
        ItemStack result = cir.getReturnValue();

        // Replace this with your real logic
        if (APRandomizer.getRecipeManager().isRecipeLocked(result.getItem())) {
            cir.setReturnValue(ItemStack.EMPTY);
        }
    }
}
