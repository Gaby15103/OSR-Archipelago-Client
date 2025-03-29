package gg.archipelago.aprandomizer.customquest.task;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class FluidTask extends Task {
    private Fluid fluid;

    public FluidTask(String ID, Fluid fluid) {
        super(ID, TaskType.FLUID);
        this.fluid = fluid;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();  // Save common fields
        tag.putString("id", getId());
        tag.putString("type", getType().name());
        if (fluid != null) {
            tag.putString("fluid", Objects.requireNonNull(ForgeRegistries.FLUIDS.getKey(fluid)).toString());  // Correct fluid name storage
        }
        return tag;
    }

    public static FluidTask fromNBT(CompoundTag tag) {
        String id = tag.getString("id");
        Fluid fluid = ForgeRegistries.FLUIDS.getValue(new ResourceLocation(tag.getString("fluid")));  // Load fluid by name using ForgeRegistries
        return new FluidTask(id, fluid);
    }

    public Fluid getFluid() {
        return fluid;
    }

    public void setFluid(Fluid fluid) {
        this.fluid = fluid;
    }
}