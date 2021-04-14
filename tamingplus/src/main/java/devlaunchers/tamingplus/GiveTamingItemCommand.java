package devlaunchers.tamingplus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveTamingItemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int whistleType = -4;
            if (args[0] != null)
                whistleType = Integer.parseInt(args[0]) - 4;
            ItemStack tamingItem = new ItemStack(Material.COAL);


            if(whistleType >= -3 && whistleType <= 3) {
                tamingItem.setType(Material.IRON_NUGGET);
                String typeOfMobString = "";
                int typeOfMobInt = whistleType * whistleType;
                //1:wolf 4:cat 9:parrot
                if (typeOfMobInt == 1)
                    typeOfMobString = "dog";
                if (typeOfMobInt == 4)
                    typeOfMobString = "cat";
                if (typeOfMobInt == 9)
                    typeOfMobString = "parrot";
                String sitOrStand = "";
                if (whistleType < 0) {
                    sitOrStand = "standing ";
                } else {
                    sitOrStand = "sitting ";
                }
                ItemMeta meta = tamingItem.getItemMeta();
                meta.setCustomModelData(whistleType + 4);
                meta.setDisplayName(ChatColor.BLUE + sitOrStand.concat(typeOfMobString).concat(" whistle"));
                tamingItem.setItemMeta(meta);
            }
            else{
                ItemMeta meta = tamingItem.getItemMeta();
                meta.setCustomModelData(1);
                meta.setDisplayName(ChatColor.BLUE + "Untaming coal");
                tamingItem.setItemMeta(meta);
            }

            int numWhistles = 1;
            if (args[1] != null)
                numWhistles = Integer.parseInt(args[1]);

            // Set the amount of the ItemStack
            tamingItem.setAmount(numWhistles);

            // Give the player our items (comma-seperated list of all ItemStack)
            player.getInventory().addItem(tamingItem);

        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}
