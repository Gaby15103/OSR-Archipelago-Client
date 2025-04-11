package gg.archipelago.aprandomizer.mixin;

import gg.archipelago.aprandomizer.APRandomizer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class MixinAbstractFurnaceBlockEntity {
    // directly reference a log4j logger.
    private static  final Logger LOGGER = LogManager.getLogger();
    @Redirect(
            method = "canBurn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/crafting/Recipe;assemble(Lnet/minecraft/world/Container;Lnet/minecraft/core/RegistryAccess;)Lnet/minecraft/world/item/ItemStack;"
            )
    )
    private ItemStack redirectAssemble(
            Recipe<?> recipe,
            Container container,
            RegistryAccess registryAccess
    ) {
        @SuppressWarnings("unchecked")
        Recipe<Container> typedRecipe = (Recipe<Container>) recipe;

        ItemStack result = typedRecipe.assemble(container, registryAccess);

        if (!result.isEmpty() && APRandomizer.getRecipeManager().isRecipeLocked(result.getItem())) {
            return ItemStack.EMPTY;
        }

        return result;
    }
}
