package me.hsgamer.bettergui.bettermaterial;

import java.util.Optional;
import me.hsgamer.bettergui.bettermaterial.lib.UMaterial;
import me.hsgamer.bettergui.bettermaterial.lib.Versionable;
import me.hsgamer.bettergui.object.Icon;
import me.hsgamer.bettergui.object.property.item.ItemProperty;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TypeProperty extends ItemProperty<String, Optional<UMaterial>> {

  public TypeProperty(Icon icon) {
    super(icon);
  }

  @Override
  public Optional<UMaterial> getParsed(Player player) {
    String value = getValue().replace('-', '_').replace(' ', '_');
    value = parseFromString(value, player);
    return Optional.ofNullable(UMaterial.match(value));
  }

  @Override
  public ItemStack parse(Player player, ItemStack itemStack) {
    Optional<UMaterial> parsed = getParsed(player);
    parsed.ifPresent(uMaterial -> {
      Material material = uMaterial.getMaterial();
      if (material != null) {
        itemStack.setType(material);
        if (Versionable.LEGACY) {
          itemStack.setDurability(uMaterial.getData());
        }
      }
    });
    return itemStack;
  }

  @Override
  public boolean compareWithItemStack(Player player, ItemStack itemStack) {
    Optional<UMaterial> material = getParsed(player);
    return material.isPresent() && material.get().equals(UMaterial.match(itemStack));
  }
}
