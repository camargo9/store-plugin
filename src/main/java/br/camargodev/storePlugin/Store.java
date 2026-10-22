package br.camargodev.storePlugin;

import br.camargodev.storePlugin.inventory.MenuInventory;
import br.camargodev.storePlugin.services.CreateCategoryService;
import br.camargodev.storePlugin.tabs.AddCategoryTab;
import br.camargodev.storePlugin.tabs.AddItemTab;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Store implements TabExecutor {
    private final MenuInventory menuInventory;
    private final CreateCategoryService createCategory;
    private final AddCategoryTab addCategoryTab;
    private final AddItemTab addItemTab;

    public Store(
            MenuInventory menuInventory,
            CreateCategoryService createCategory,
            AddCategoryTab addCategoryTab,
            AddItemTab addItemTab
    ) {
        this.menuInventory = menuInventory;
        this.createCategory = createCategory;
        this.addCategoryTab = addCategoryTab;
        this.addItemTab = addItemTab;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) return false;

        if (args.length == 0) {
            player.openInventory(menuInventory.getInventory());
        }

        if (args[0].equalsIgnoreCase("add-category")) {
            if (args.length > 4) {
                player.sendMessage(Component.text("Too many arguments or a name with a space, please try again.", NamedTextColor.RED));
            }

            try {
                String nameCategory = args[1];
                Integer positionCategory = Integer.parseInt(args[2]);
                Material materialCategory = Material.matchMaterial(args[3]);

                createCategory.create(nameCategory, positionCategory, materialCategory, player);

            } catch (NumberFormatException event) {
                player.sendMessage(event.getMessage());
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {

        if (args.length == 1) {
            return List.of("add-category", "remove-category", "add-item", "remove-item");
        }

        if (args[0].equalsIgnoreCase("add-category")) {
            return addCategoryTab.complete(args, menuInventory);
        }

        if (args[0].equalsIgnoreCase("add-item")) {
            return addItemTab.complete(args);
        }

        return List.of();
    }
}