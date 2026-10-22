package br.camargodev.storePlugin.listeners;

import br.camargodev.storePlugin.registry.RegistryCategories;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.persistence.PersistentDataType;

public class OpenCategory implements Listener {
    private final RegistryCategories registryCategories;
    private final NamespacedKey categoryId;

    public OpenCategory(RegistryCategories registryCategories, NamespacedKey categoryId) {
        this.registryCategories = registryCategories;
        this.categoryId = categoryId;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String itemPdc = event.getCurrentItem().getPersistentDataContainer().get(categoryId, PersistentDataType.STRING);

        if (registryCategories.getCategory(itemPdc) != null) {
            player.openInventory(registryCategories.getCategory(itemPdc));
        }
    }
}
