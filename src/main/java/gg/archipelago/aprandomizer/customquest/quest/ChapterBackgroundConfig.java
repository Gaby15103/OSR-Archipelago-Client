package gg.archipelago.aprandomizer.customquest.quest;

import com.google.gson.annotations.SerializedName;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class ChapterBackgroundConfig {
    @SerializedName("image")
    private String image;

    @SerializedName("width")
    private double width;

    @SerializedName("height")
    private double height;

    @SerializedName("x")
    private double x;

    @SerializedName("y")
    private double y;

    @SerializedName("rotation")
    private double rotation;

    // Convert image path to a Minecraft ResourceLocation
    public ResourceLocation getImageResourceLocation() {
        return new ResourceLocation(image);
    }

    // Getters and setters

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    // Serialize the background configuration to NBT
    public CompoundTag toNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("image", image);
        tag.putDouble("width", width);
        tag.putDouble("height", height);
        tag.putDouble("x", x);
        tag.putDouble("y", y);
        tag.putDouble("rotation", rotation);
        return tag;
    }

    // Deserialize the background configuration from NBT
    public static ChapterBackgroundConfig fromNBT(CompoundTag tag) {
        ChapterBackgroundConfig config = new ChapterBackgroundConfig();
        config.setImage(tag.getString("image"));
        config.setWidth(tag.getDouble("width"));
        config.setHeight(tag.getDouble("height"));
        config.setX(tag.getDouble("x"));
        config.setY(tag.getDouble("y"));
        config.setRotation(tag.getDouble("rotation"));
        return config;
    }
}
