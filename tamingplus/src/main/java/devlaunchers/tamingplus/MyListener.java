package devlaunchers.tamingplus;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Sittable;
import org.bukkit.inventory.meta.ItemMeta;

/*
Whistle custom model data
1:parrot stand
2:cat stand
3:wolf stand
4:wolf calm
5:wolf sit
6:cat sit
7:parrot sit
*/


//Untameable  mobs, a plugin written by SuperSilly12356

public class MyListener implements Listener {
    //java -Xmx2048M -Xms2048M -jar paper.jar nogui

    public void petSitStand(PlayerInteractEvent event, int whistleType){
        World world = event.getPlayer().getWorld();
        Player player = event.getPlayer();
        String typeOfMobString = "";
        int typeOfMobInt = whistleType * whistleType;
        //1:wolf 4:cat 9:parrot
        if (typeOfMobInt  == 1)
            typeOfMobString = "dogs";
        if (typeOfMobInt  == 4)
            typeOfMobString = "cats";
        if (typeOfMobInt  == 9)
            typeOfMobString = "parrots";
        Boolean mobIsSittableTameable;
        Location playerLocation = player.getLocation();
        for (Entity selectedEntity : world.getLivingEntities()) {
            Location entityLocation = selectedEntity.getLocation();
            int entityPlayerXCoordDifference = Math.abs(playerLocation.getBlockX()- entityLocation.getBlockX());
            int entityPlayerYCoordDifference = Math.abs(playerLocation.getBlockY()- entityLocation.getBlockY());
            double entityPlayerCoordDifference = Math.sqrt(entityPlayerXCoordDifference * entityPlayerXCoordDifference + entityPlayerYCoordDifference * entityPlayerYCoordDifference);
            if(entityPlayerCoordDifference < 50){
                mobIsSittableTameable = false;
                if (typeOfMobInt == 1 && selectedEntity instanceof Wolf) {
                    mobIsSittableTameable = true;
                }
                if (typeOfMobInt == 4 && selectedEntity instanceof Cat) {
                    mobIsSittableTameable = true;
                }
                if (typeOfMobInt == 9 && selectedEntity instanceof Parrot) {
                    mobIsSittableTameable = true;
                }
                if (mobIsSittableTameable) {
                    Tameable selectedTamableEntity = (Tameable)selectedEntity;
                    Sittable selectedSittableEntity = (Sittable)selectedEntity;
                    if (selectedTamableEntity.isTamed()) {
                        if (selectedTamableEntity.getOwnerUniqueId().equals(player.getUniqueId())) {
                            selectedSittableEntity.setSitting(whistleType > 0);
                        }
                    }
                }
            }
        }
        if(whistleType > 0){
            player.sendMessage(player.getUniqueId(), "Your ".concat(typeOfMobString).concat(" are standing!"));
        }
        else {
            player.sendMessage(player.getUniqueId(), "Your ".concat(typeOfMobString).concat(" are standing!"));
        }
    }

    public void dogCalm(PlayerInteractEvent event){
        World world = event.getPlayer().getWorld();
        Player player = event.getPlayer();
        player.sendMessage(player.getUniqueId(), "Your dogs are calm!");
        for (Entity selectedEntity : world.getLivingEntities()) {
            if (selectedEntity instanceof Wolf) {
                Wolf selectedWolf = (Wolf) selectedEntity;
                selectedWolf.setTarget(null);
            }
        }
    }
    public void untamePet(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        Entity clickedEntity = event.getRightClicked();
        if(clickedEntity instanceof Tameable) {
            Tameable clickedTamedMob = (Tameable) clickedEntity;
            if(clickedTamedMob.isTamed()){
                if (clickedTamedMob.getOwnerUniqueId().equals(player.getUniqueId())) {
                    player.sendMessage(player.getUniqueId(), "Your mob was successfully untamed!");
                    clickedTamedMob.setTamed(false);
                    if (clickedTamedMob instanceof Sittable) {
                        Sittable clickedSittableMob = (Sittable) clickedTamedMob;
                        clickedSittableMob.setSitting(false);
                    }
                    if (clickedTamedMob instanceof Vehicle) {
                        Vehicle clickedTamedVehicle = (Vehicle) clickedTamedMob;
                        clickedTamedVehicle.eject();
                    }
                } else {
                    event.getPlayer().sendMessage(player.getUniqueId(), "That's not your mob to untame!");
                }
            }
        }
    }

/*
Custom model data for coal
custom model data = 1 for untaming item

*/
    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
    	ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getHand().toString().equals("OFF_HAND")) {
            itemInHand = event.getPlayer().getInventory().getItemInOffHand();
        }
        Material itemInHandType = itemInHand.getType();
        if(itemInHandType.equals(Material.COAL)){
            ItemMeta itemInHandMeta = itemInHand.getItemMeta();
            if(itemInHandMeta.hasCustomModelData()) {
                int itemInHandCustomModelData = itemInHandMeta.getCustomModelData();
                if (itemInHandCustomModelData == 1) {
                    untamePet(event);
                }
            }
        }
    }

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

			ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
			if (event.getHand().toString().equals("OFF_HAND")) {
				itemInHand = event.getPlayer().getInventory().getItemInOffHand();
			}
			Material itemInHandType = itemInHand.getType();
			if (itemInHandType.equals(Material.IRON_NUGGET)) {
				ItemMeta itemInHandMeta = itemInHand.getItemMeta();
				if (itemInHandMeta.hasCustomModelData()) {
					int itemInHandCustomModelData = itemInHandMeta.getCustomModelData();
					if (itemInHandCustomModelData == 4) {
						dogCalm(event);
					} else if (itemInHandCustomModelData >= 1 && itemInHandCustomModelData <= 7) {
						petSitStand(event, (itemInHandCustomModelData - 4));
					}
				}
			}
		}
	}
}