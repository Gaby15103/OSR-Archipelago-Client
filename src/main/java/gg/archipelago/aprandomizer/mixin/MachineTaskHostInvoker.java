package gg.archipelago.aprandomizer.mixin;

import com.enderio.machines.common.blockentity.task.host.MachineTaskHost;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MachineTaskHost.class)
public interface MachineTaskHostInvoker {
    @Invoker("getLevel")
    Level callGetLevel();
}
