package gg.archipelago.aprandomizer.managers.recipemanager;

import dev.latvian.mods.kubejs.event.EventJS;

public class CustomRecipeEventJS extends EventJS {
    private final String recipeId;
    private final boolean isRestricted;

    public CustomRecipeEventJS(String recipeId, boolean isRestricted) {
        this.recipeId = recipeId;
        this.isRestricted = isRestricted;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public boolean isRestricted() {
        return isRestricted;
    }
}
