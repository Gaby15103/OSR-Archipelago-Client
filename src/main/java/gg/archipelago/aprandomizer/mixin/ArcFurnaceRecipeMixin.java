package gg.archipelago.aprandomizer.mixin;

import blusunrize.immersiveengineering.api.crafting.ArcFurnaceRecipe;
import blusunrize.immersiveengineering.api.crafting.cache.CachedRecipeList;
import blusunrize.immersiveengineering.common.crafting.serializers.ArcFurnaceRecipeSerializer;
import com.google.gson.JsonObject;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;


@Mixin(ArcFurnaceRecipe.class)
public class ArcFurnaceRecipeMixin {
    @Inject(method = "findRecipe", at = @At("RETURN"), cancellable = true, remap = false)
    private static void blockLockedArcFurnaceRecipe(Level level, ItemStack input, NonNullList<ItemStack> additives, CallbackInfoReturnable<ArcFurnaceRecipe> cir) {
        ArcFurnaceRecipe recipe = cir.getReturnValue();
        if (recipe != null && APRandomizer.getRecipeManager().isRecipeLocked(recipe.getId())) {
            cir.setReturnValue(null);
        }
    }
}



