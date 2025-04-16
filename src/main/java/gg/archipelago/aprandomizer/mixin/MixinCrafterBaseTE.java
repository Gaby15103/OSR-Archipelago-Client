package gg.archipelago.aprandomizer.mixin;

import gg.archipelago.aprandomizer.APRandomizer;
import mcjty.rftoolsutility.modules.crafter.blocks.CrafterBaseTE;
import mcjty.rftoolsutility.modules.crafter.data.CraftingRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(CrafterBaseTE.class)
public class MixinCrafterBaseTE {
    @Inject(method = "applyRecipe", at = @At("HEAD"), cancellable = true, remap = false)
    private void ap$checkIfRecipeIsLocked(CallbackInfo ci) {
        try {
            CrafterBaseTE crafter = (CrafterBaseTE) (Object) this;


            Field selectedField = CrafterBaseTE.class.getDeclaredField("selected");
            selectedField.setAccessible(true); // Accède à la variable privée
            int selected = (int) selectedField.get(crafter); // Récupère la valeur de 'selected'

            Field recipes = CrafterBaseTE.class.getDeclaredField("recipes");
            recipes.setAccessible(true);
            CraftingRecipe[] recipeArray = (CraftingRecipe[]) recipes.get(crafter);
            CraftingRecipe recipe = recipeArray[selected];


            if (recipe != null &&  APRandomizer.getRecipeManager().isRecipeLocked(recipe.getResult().getItem())) {
                System.out.println("Blocked crafter recipe: " + recipe.toString());
                ci.cancel();
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
