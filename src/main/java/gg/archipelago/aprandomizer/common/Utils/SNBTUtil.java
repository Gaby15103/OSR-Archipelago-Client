package gg.archipelago.aprandomizer.common.Utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;

public class SNBTUtil {
    public static CompoundTag readSnbt(String snbt) throws RuntimeException {
        try {
            return TagParser.parseTag(snbt);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse SNBT: " + snbt, e);
        }
    }
}

