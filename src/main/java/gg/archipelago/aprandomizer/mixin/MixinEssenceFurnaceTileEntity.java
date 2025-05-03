package gg.archipelago.aprandomizer.mixin;

import com.blakebr0.mysticalagriculture.tileentity.EssenceFurnaceTileEntity;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EssenceFurnaceTileEntity.class)
public class MixinEssenceFurnaceTileEntity {
    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/blakebr0/mysticalagriculture/tileentity/EssenceFurnaceTileEntity;canBurn(Lnet/minecraft/world/item/crafting/Recipe;Lnet/minecraft/core/NonNullList;I)Z",
                    shift = At.Shift.BEFORE
            ),
            cancellable = true,
            remap = false
    )
    private static void blockCertainOutputs(
            Level level,
            BlockPos pos,
            BlockState state,
            EssenceFurnaceTileEntity tile,
            CallbackInfo ci
    ) {
        Recipe<?> recipe = level.getRecipeManager()
                .getRecipeFor(RecipeType.SMELTING, tile, level)
                .orElse(null);

        if (recipe != null) {
            if (APRandomizer.getRecipeManager().isRecipeLocked(recipe.getId())) {
                ci.cancel();
            }
        }
    }
}
