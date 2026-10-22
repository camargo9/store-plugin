package br.camargodev.storePlugin.tabs;

import br.camargodev.storePlugin.registry.RegistryCategories;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public class AddItemTab {
    private RegistryCategories registryCategories;

    public AddItemTab(RegistryCategories registryCategories) {
        this.registryCategories = registryCategories;
    }

    public List<String> complete(String[] args) {

        // store add-item <category> <material>

        if (args.length == 2) {
            return registryCategories.getAll();
        }

        if (args.length == 3) {
            String typed = args[2].toLowerCase();
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
