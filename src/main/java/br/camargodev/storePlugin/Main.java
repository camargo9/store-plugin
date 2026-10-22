package br.camargodev.storePlugin;

import br.camargodev.storePlugin.inventory.MenuInventory;
import br.camargodev.storePlugin.listeners.MenuInventoryListener;
import br.camargodev.storePlugin.listeners.OpenCategory;
import br.camargodev.storePlugin.registry.RegistryCategories;
import br.camargodev.storePlugin.services.CreateCategoryService;
import br.camargodev.storePlugin.tabs.AddCategoryTab;
import br.camargodev.storePlugin.tabs.AddItemTab;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // NameSpacedKey
        NamespacedKey categoryId = new NamespacedKey(this, "categoryId");

        // Registry
        RegistryCategories registryCategories = new RegistryCategories();

        // Menu Inventory
        MenuInventory menuInventory = new MenuInventory();

        // Services
        CreateCategoryService createCategory = new CreateCategoryService(menuInventory, registryCategories, categoryId);

        // Tabs
        AddCategoryTab addCategoryTab = new AddCategoryTab();
        AddItemTab addItemTab = new AddItemTab(registryCategories);

        // Comandos
        getCommand("store").setExecutor(
                new Store(
                        menuInventory,
                        createCategory,
                        addCategoryTab,
                        addItemTab
                ));

        // Listeners
        getServer().getPluginManager().registerEvents(new MenuInventoryListener(), this);
        getServer().getPluginManager().registerEvents(new OpenCategory(registryCategories, categoryId), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
