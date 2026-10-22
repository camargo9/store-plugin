package br.camargodev.storePlugin.tabs;

import br.camargodev.storePlugin.inventory.MenuInventory;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddCategoryTab {

    public List<String> complete(String[] args, MenuInventory menuInventory) {

        if (args.length == 1) {
            return List.of("add-category", "add-item");
        }

        if (args.length == 2) {
            return List.of("<name>");
        }

        if (args.length == 3) {
            List<String> slots = new ArrayList<>();
            for (int i = 0; i <= menuInventory.getInventory().getSize(); i++) {
                slots.add(String.format("%02d", i));
            }

            return slots;
        }

        if (args.length == 4) {
            String typed = args[3].toLowerCase();
            return Arrays.stream(Material.values())
                    .filter(Material::isItem)
                    .filter(material -> !material.isAir())
                    .filter(material -> !material.name().startsWith("LEGACY_"))
                    .map(material -> material.name().toLowerCase())
                    .filter(name -> name.startsWith(typed))
                    .toList();
        }

        return List.of();
    }
}
