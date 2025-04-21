package gg.archipelago.aprandomizer.mixin;

import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import vazkii.botania.api.recipe.ManaInfusionRecipe;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;

@Mixin(ManaPoolBlockEntity.class)
public class ManaPoolBlockEntityMixin {
    @Inject(method = "getMatchingRecipe", at = @At("RETURN"), cancellable = true, remap = false)
    private void ap$filterLockedRecipes(ItemStack stack, BlockState state, CallbackInfoReturnable<ManaInfusionRecipe> cir) {
        ManaInfusionRecipe result = cir.getReturnValue();

        if (result != null) {
            if (APRandomizer.getRecipeManager().isRecipeLocked(result.getId())) {
                cir.setReturnValue(null); // No recipe match
            }
        }
    }
}
