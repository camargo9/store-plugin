package br.camargodev.storePlugin.listeners;

import br.camargodev.storePlugin.inventory.MenuInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuInventoryListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTopInventory().getHolder() instanceof MenuInventory) {
            event.setCancelled(true);
        }
    }
}
