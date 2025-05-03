package gg.archipelago.aprandomizer.mixin;

import com.brandon3055.draconicevolution.blocks.tileentity.TileFusionCraftingCore;
import com.brandon3055.draconicevolution.api.crafting.IFusionRecipe;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.item.crafting.Recipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileFusionCraftingCore.class)
public abstract class MixinTileFusionCraftingCore {
    @Inject(method = "startCraft", at = @At("HEAD"), cancellable = true, remap = false)
    public void onStartCraft(CallbackInfo ci) {
        TileFusionCraftingCore tileEntity = (TileFusionCraftingCore) (Object) this;

        IFusionRecipe recipe = tileEntity.getActiveRecipe();

        assert recipe != null;
        if (APRandomizer.getRecipeManager().isRecipeLocked(recipe.getId())) {
            ci.cancel();
        }
    }
}