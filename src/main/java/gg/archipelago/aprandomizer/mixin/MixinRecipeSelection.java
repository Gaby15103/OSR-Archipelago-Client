package gg.archipelago.aprandomizer.mixin;

import com.illusivesoulworks.polymorph.api.common.capability.IRecipeData;
import com.illusivesoulworks.polymorph.common.crafting.RecipeSelection;
import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.List;
import java.util.Optional;

@Mixin(RecipeSelection.class)
public class MixinRecipeSelection {

    @Unique
    private static <T extends Recipe<C>, C extends Container> void oSR_Archipelago_Client$blockLockedRecipes(
            List<T> allRecipes, CallbackInfoReturnable<Optional<T>> cir) {
        for (T recipe : allRecipes) {
            if (APRandomizer.getRecipeManager().isRecipeLocked(recipe.getId())) {
                cir.setReturnValue(Optional.empty());
                return;
            }
        }
    }

    @Inject(method = "getRecipe", at = @At("HEAD"), cancellable = true, remap = false)
    private static <T extends Recipe<C>, C extends Container> void blockLockedRecipesForGetRecipe(
            RecipeType<T> type, C inventory, Level level, Optional<? extends IRecipeData<?>> pOpt, List<T> recipes, CallbackInfoReturnable<Optional<T>> cir) {
        List<T> allRecipes = level.getRecipeManager().getRecipesFor(type, inventory, level);
        oSR_Archipelago_Client$blockLockedRecipes(allRecipes, cir);
    }

    @Inject(method = "getPlayerRecipe*", at = @At("HEAD"), cancellable = true, remap = false)
    private static <T extends Recipe<C>, C extends Container> void blockLockedRecipesForPlayer(
            AbstractContainerMenu containerMenu, RecipeType<T> type, C inventory, Level level, Player player, List<T> recipes,
            CallbackInfoReturnable<Optional<T>> cir) {
        List<T> allRecipes = level.getRecipeManager().getRecipesFor(type, inventory, level);
        oSR_Archipelago_Client$blockLockedRecipes(allRecipes, cir);
    }

    @Inject(method = "getPlayerRecipe*", at = @At("HEAD"), cancellable = true, remap = false)
    private static <T extends Recipe<C>, C extends Container> void blockLockedRecipesForPlayerWithSlots(
            AbstractContainerMenu containerMenu, RecipeType<T> type, C inventory, Level level, List<Slot> slots,
            CallbackInfoReturnable<Optional<T>> cir) {
        Player player = null;
        for (Slot slot : slots) {
            Container var9 = slot.container;
            if (var9 instanceof Inventory inv) {
                player = inv.player;
                break;
            }
        }
        if (player != null) {
            List<T> allRecipes = level.getRecipeManager().getRecipesFor(type, inventory, level);
            oSR_Archipelago_Client$blockLockedRecipes(allRecipes, cir);
        } else {
            cir.setReturnValue(Optional.empty());
        }
    }

    @Inject(method = "getDefaultRecipe", at = @At("HEAD"), cancellable = true, remap = false)
    private static <T extends Recipe<C>, C extends Container> void blockLockedRecipesForDefault(
            RecipeType<T> type, C inventory, Level level, CallbackInfoReturnable<Optional<T>> cir) {
        Optional<T> defaultRecipe = level.getRecipeManager().getRecipeFor(type, inventory, level);
        defaultRecipe.ifPresent(recipe -> {
            if (APRandomizer.getRecipeManager().isRecipeLocked(recipe.getId())) {
                cir.setReturnValue(Optional.empty());
            }
        });
    }
}