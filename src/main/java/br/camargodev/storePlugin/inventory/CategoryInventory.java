package br.camargodev.storePlugin.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CategoryInventory implements InventoryHolder {
    private final Inventory inventory;

    public CategoryInventory(String name) {
        this.inventory = Bukkit.createInventory(this, 36, name);
    }

    public Inventory getInventory() {
        return inventory;
    }

}
