package me.hsgamer.bettergui.bettermaterial;

import me.hsgamer.bettergui.bettermaterial.lib.Versionable;
import me.hsgamer.bettergui.builder.PropertyBuilder;
import me.hsgamer.bettergui.object.addon.Addon;

public final class Main extends Addon {

  @Override
  public boolean onLoad() {
    if (Versionable.LEGACY) {
      return true;
    } else {
      getPlugin().getLogger().warning("You are using a new version of Minecraft");
      getPlugin().getLogger().warning("You don't need to use this addon");
      return false;
    }
  }

  @Override
  public void onEnable() {
    PropertyBuilder.registerItemProperty("id", TypeProperty.class);
    PropertyBuilder.registerItemProperty("material", TypeProperty.class);
  }
}
