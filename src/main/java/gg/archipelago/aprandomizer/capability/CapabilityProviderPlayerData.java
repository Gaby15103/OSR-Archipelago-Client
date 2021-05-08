package gg.archipelago.aprandomizer.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.world.NoteBlockEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityProviderPlayerData implements ICapabilitySerializable<INBT> {


    private PlayerData playerData = new PlayerData();

    /**
     * Asks the Provider if it has the given capability
     * @param capability<T> capability to be checked for
     * @param facing the side of the provider being checked (null = no particular side)
     * @param <T> The interface instance that is used
     * @return a lazy-initialisation supplier of the interface instance that is used to access this capability
     *         In this case, we don't actually use lazy initialisation because the instance is very quick to create.
     *         See CapabilityProviderFlowerBag for an example of lazy initialisation
     */
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> capability, @Nullable Direction facing) {
        if (CapabilityPlayerData.CAPABILITY_PLAYER_DATA == capability) {
            return (LazyOptional<T>)LazyOptional.of(()-> playerData);
            // why are we using a lambda?  Because LazyOptional.of() expects a NonNullSupplier interface.  The lambda automatically
            //   conforms itself to that interface.  This save me having to define an inner class implementing NonNullSupplier.
            // The explicit cast to LazyOptional<T> is required because our CAPABILITY_ELEMENTAL_FIRE can't be typed.  Our code has
            //   checked that the requested capability matches, so the explicit cast is safe (unless you have made a mistake and mixed them up!)
        }
        return LazyOptional.empty();
        // Note that if you are implementing getCapability in a derived class which implements ICapabilityProvider
        // eg you have added a new MyEntity which has the method MyEntity::getCapability instead of using AttachCapabilitiesEvent to attach a
        // separate class, then you should call
        // return super.getCapability(capability, facing);
        //   instead of
        // return LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return CapabilityPlayerData.CAPABILITY_PLAYER_DATA.writeNBT(playerData, null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        CapabilityPlayerData.CAPABILITY_PLAYER_DATA.readNBT(playerData,null,nbt);
    }
}
