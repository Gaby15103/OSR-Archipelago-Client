package gg.archipelago.aprandomizer.mixin;


import com.enderio.machines.common.blockentity.CrafterBlockEntity;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(CrafterBlockEntity.class)
public class MixinCrafterBlockEntity {
    @Inject(method = "updateRecipe", at = @At("TAIL"), remap = false)
    private void onUpdateRecipeTail(CallbackInfo ci) {
        CrafterBlockEntity self = (CrafterBlockEntity)(Object)this;
        CraftingRecipe recipe = getRecipe(self);

        if (APRandomizer.getRecipeManager().isRecipeLocked(recipe.getId())) {
            System.out.println("Blocked crafter recipe: " + recipe.getId());
            setRecipe(self, null);
            CrafterBlockEntity.PREVIEW.setStackInSlot(self, ItemStack.EMPTY);
        }
    }

    @Shadow
    @Final
    @Mutable
    private CraftingRecipe recipe;

    private CraftingRecipe getRecipe(CrafterBlockEntity instance) {
        return this.recipe;
    }

    private void setRecipe(CrafterBlockEntity instance, CraftingRecipe recipe) {
        this.recipe = recipe;
    }
}
