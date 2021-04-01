package devlaunchers.tamingplus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveWhistleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            int whistleType = 1;
            if (args[0] != null)
                whistleType = Integer.parseInt(args[0]) - 4;

            String typeOfMobString = "";
            int typeOfMobInt = whistleType * whistleType;
            //1:wolf 4:cat 9:parrot
            if (typeOfMobInt  == 1)
                typeOfMobString = "dog";
            if (typeOfMobInt  == 4)
                typeOfMobString = "cat";
            if (typeOfMobInt  == 9)
                typeOfMobString = "parrot";
            String sitOrStand = "";
            if(whistleType < 0){
                sitOrStand = "standing ";
            } else {
                sitOrStand = "sitting ";
            }
            ItemStack whistleItem = new ItemStack(Material.IRON_NUGGET);
            ItemMeta meta = whistleItem.getItemMeta();
            meta.setCustomModelData(whistleType + 4);
            meta.setDisplayName(ChatColor.BLUE + sitOrStand.concat(typeOfMobString).concat(" whistle"));
            whistleItem.setItemMeta(meta);

            int numWhistles = 1;
            if (args[1] != null)
                numWhistles = Integer.parseInt(args[1]);

            // Set the amount of the ItemStack
            whistleItem.setAmount(numWhistles);

            // Give the player our items (comma-seperated list of all ItemStack)
            player.getInventory().addItem(whistleItem);

        }

        // If the player (or console) uses our command correct, we can return true
        return true;
    }
}
