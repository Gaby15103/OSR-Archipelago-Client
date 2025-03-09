package gg.archipelago.aprandomizer.data;

import com.google.common.collect.Lists;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class WorldData extends SavedData {

    private String seedName;
    private boolean jailPlayers;
    private Set<Long> locations;
    private int index = 0;
    private Map<String, Integer> playerIndex = new HashMap<>();

    public static WorldData initialize(DimensionDataStorage dataStorage) {
        return dataStorage.computeIfAbsent(WorldData::load, WorldData::create, "apdata");
    }

    public static WorldData create() {
        return new WorldData("", true, new long[0], new HashMap<>(), 0);
    }


    public void setSeedName(String seedName) {
        this.seedName = seedName;
        this.setDirty();
    }

    public String getSeedName() {
        return seedName;
    }

    public boolean getJailPlayers() {
        return jailPlayers;
    }

    public void setJailPlayers(boolean jailPlayers) {
        this.jailPlayers = jailPlayers;
        this.setDirty();
    }

    public void addLocation(Long location) {
        this.locations.add(location);
        this.setDirty();
    }

    public void addLocations(Long[] locations) {
        this.locations.addAll(Lists.newArrayList(Arrays.stream(locations).iterator()));
        this.setDirty();
    }

    public Set<Long> getLocations() {
        return locations;
    }

    public void updatePlayerIndex(String playerUUID, int index) {
        playerIndex.put(playerUUID, index);
        this.setDirty();
    }

    public int getPlayerIndex(String playerUUID) {
        return playerIndex.getOrDefault(playerUUID, 0);
    }

    public int getItemIndex() {
        return this.index;
    }

    public void setItemIndex(int index) {
        this.index = index;
        this.setDirty();
    }

    @Override
    public @NotNull CompoundTag save(CompoundTag tag) {
        tag.putString("seedName", seedName);
        tag.putBoolean("jailPlayers", jailPlayers);
        tag.putLongArray("locations",locations.stream().toList());
        tag.putLong("index", index);
        CompoundTag tagIndex = new CompoundTag();
        this.playerIndex.forEach(tagIndex::putLong);
        tag.put("playerIndex", tagIndex);
        return tag;
    }

    private WorldData(String seedName, boolean jailPlayers, long[] locations, Map<String, Integer> playerIndex, int itemIndex) {
        this.seedName = seedName;
        this.jailPlayers = jailPlayers;
        this.locations = new HashSet<>(Set.of(ArrayUtils.toObject(locations)));
        this.index = itemIndex;
        this.playerIndex = playerIndex;
    }

    public static WorldData load(CompoundTag tag) {
        CompoundTag indexTag = tag.getCompound("playerIndex");
        HashMap<String, Integer> indexMap = new HashMap<>();
        indexTag.getAllKeys().forEach(key -> indexMap.put(key, indexTag.getInt(key)));
        return new WorldData(
                tag.getString("seedName"),
                tag.getBoolean("jailPlayers"),
                tag.getLongArray("locations"),
                indexMap,
                tag.getInt("index")
                );
    }
}