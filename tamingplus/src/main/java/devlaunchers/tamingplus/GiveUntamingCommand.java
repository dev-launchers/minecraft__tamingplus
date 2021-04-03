package devlaunchers.tamingplus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveUntamingCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack untamingItem = new ItemStack(Material.COAL);
            ItemMeta meta = untamingItem.getItemMeta();
            meta.setCustomModelData(1);
            meta.setDisplayName(ChatColor.BLUE + "Untaming Coal");
            untamingItem.setItemMeta(meta);

            int numUntaming = 1;
            if (args[1] != null)
                numUntaming = Integer.parseInt(args[1]);

            // Set the amount of the ItemStack
            untamingItem.setAmount(numUntaming);

            // Give the player our items (comma-seperated list of all ItemStack)
            player.getInventory().addItem(untamingItem);

        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}
