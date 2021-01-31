package devlaunchers.untaming;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Sittable;
//Untameable  mobs, a plugin written by SuperSilly12356

public class MyListener implements Listener {
//java -Xmx2048M -Xms2048M -jar paper.jar nogui
    @EventHandler
    public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {
        Entity clickedEntity = event.getRightClicked();
        Player player = event.getPlayer();
        ItemStack mainhand = player.getInventory().getItemInMainHand();
        if(clickedEntity instanceof Tameable) {
            Tameable clickedTamedMob = (Tameable) clickedEntity;
            if(mainhand.getType().equals(Material.COAL) && clickedTamedMob.isTamed() && clickedTamedMob.getOwnerUniqueId().equals(player.getUniqueId()) && mainhand.getItemMeta().getDisplayName().equalsIgnoreCase("untaming coal")) {
                mainhand.setAmount(mainhand.getAmount() - 1);
                event.getPlayer().sendMessage(player.getUniqueId(), "Your mob was successfully untamed!");
                clickedTamedMob.setTamed(false);
                if(clickedTamedMob instanceof Sittable){
                   Sittable clickedSittableMob = (Sittable) clickedTamedMob;
                   if(clickedSittableMob.isSitting()){
                     clickedSittableMob.setSitting(false);
                   }
                }
                if(clickedTamedMob instanceof Vehicle){
                    Vehicle clickedTamedVehicle = (Vehicle) clickedTamedMob;
                    clickedTamedVehicle.eject();

                }
            }
            if(mainhand.getType().equals(Material.COAL) && clickedTamedMob.isTamed() && !(clickedTamedMob.getOwnerUniqueId().equals(player.getUniqueId())) && mainhand.getItemMeta().getDisplayName().equalsIgnoreCase("untaming coal")) {
                event.getPlayer().sendMessage(player.getUniqueId(), "That's not your mob to untame!");
            }
        }

    }

}
