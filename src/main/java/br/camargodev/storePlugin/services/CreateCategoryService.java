package br.camargodev.storePlugin.services;

import br.camargodev.storePlugin.inventory.CategoryInventory;
import br.camargodev.storePlugin.inventory.MenuInventory;
import br.camargodev.storePlugin.registry.RegistryCategories;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class CreateCategoryService {
    private final MenuInventory menuInventory;
    private final RegistryCategories registryCategories;
    private final NamespacedKey categoryId;

    public CreateCategoryService(
            MenuInventory menuInventory,
            RegistryCategories registryCategories,
            NamespacedKey categoryId
    ) {
        this.menuInventory = menuInventory;
        this.registryCategories = registryCategories;
        this.categoryId = categoryId;
    }

    public void create(String name, Integer position, Material material, Player player) {
        // Cria no MenuInventory
        ItemStack item = new ItemStack(material);
        item.editMeta(meta -> meta.displayName(Component.text(name).decoration(TextDecoration.ITALIC, false)));
        item.editPersistentDataContainer(pdc -> pdc.set(categoryId, PersistentDataType.STRING, name));

        menuInventory.getInventory().setItem(position, item);

        registryCategories.register(name, new CategoryInventory(name).getInventory());

        player.sendMessage(Component.text("Category Created.", NamedTextColor.GREEN));
    }
}
