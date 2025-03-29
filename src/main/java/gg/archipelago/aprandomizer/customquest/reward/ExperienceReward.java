package gg.archipelago.aprandomizer.customquest.reward;

import net.minecraft.nbt.CompoundTag;

public class ExperienceReward extends Reward {
    private int experiencePoints;

    public ExperienceReward(String id, int experiencePoints) {
        super(id, RewardType.EXPERIENCE);
        this.experiencePoints = experiencePoints;
    }

    @Override
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", getId());
        tag.putString("type", getType().name());
        tag.putInt("experience_points", experiencePoints);
        return tag;
    }

    public static ExperienceReward fromFtbNBT(CompoundTag tag) {
        String id = tag.getString("id");
        int experiencePoints = tag.getInt("experience_points");
        return new ExperienceReward(id, experiencePoints);
    }

    public static ExperienceReward fromNBT(CompoundTag tag) {
        String id = tag.getString("id");
        int experiencePoints = tag.getInt("experience_points");
        return new ExperienceReward(id, experiencePoints);
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }
}