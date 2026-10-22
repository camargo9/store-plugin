package br.camargodev.storePlugin.inventory;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class MenuInventory implements InventoryHolder {
    public final Inventory inventory;

    public MenuInventory() {
        this.inventory = Bukkit.createInventory(this, 36, "Item Store");
    }

    public Inventory getInventory() {
        return inventory;
    }
}
