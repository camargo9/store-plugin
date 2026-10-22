package br.camargodev.storePlugin.registry;

import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistryCategories {
    Map<String, Inventory> categories = new HashMap<>();

    public List<String> getAll() {
        return new ArrayList<>(categories.keySet());
    }

    public Inventory getCategory(String id) {
        return categories.get(id);
    }

    public void register(String id, Inventory menu) {
        categories.put(id, menu);
    }

    public void remove(String id) {
        categories.remove(id);
    }
}
