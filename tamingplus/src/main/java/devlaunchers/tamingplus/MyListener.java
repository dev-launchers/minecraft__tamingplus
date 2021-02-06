package devlaunchers.tamingplus;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Sittable;
//Untameable  mobs, a plugin written by SuperSilly12356

public class MyListener implements Listener {
    //java -Xmx2048M -Xms2048M -jar paper.jar nogui

    //java -Xmx2048M -Xms2048M -jar paper.jar nogui




    public void dogSit(PlayerInteractEvent event){
        World world = event.getPlayer().getWorld();
        Player player = event.getPlayer();
        player.sendMessage(player.getUniqueId(), "Your dogs are sitting!");
        for (Entity selectedEntity : world.getLivingEntities()) {
            if (selectedEntity instanceof Tameable) {
                Wolf selectedWolf = (Wolf) selectedEntity;
                if(selectedWolf.isTamed()){
                    if (selectedWolf.getOwnerUniqueId().equals(player.getUniqueId())) {
                        selectedWolf.setSitting(true);
                    }
                }

            }
        }
    }

    public void dogStand(PlayerInteractEvent event){
        World world = event.getPlayer().getWorld();
        Player player = event.getPlayer();
        player.sendMessage(player.getUniqueId(), "Your dogs are standing!");
        for (Entity selectedEntity : world.getLivingEntities()) {
            if (selectedEntity instanceof Tameable) {
                Wolf selectedWolf = (Wolf) selectedEntity;
                if(selectedWolf.isTamed()){
                    if (selectedWolf.getOwnerUniqueId().equals(player.getUniqueId())) {
                        selectedWolf.setSitting(false);
                    }
                }

            }
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

    public void catSit(PlayerInteractEvent event){
        World world = event.getPlayer().getWorld();
        Player player = event.getPlayer();
        player.sendMessage(player.getUniqueId(), "Your cats are standing!");
        for (Entity selectedEntity : world.getLivingEntities()) {
            if (selectedEntity instanceof Tameable) {
                Cat selectedCat = (Cat) selectedEntity;
                if(selectedCat.isTamed()){
                    if (selectedCat.getOwnerUniqueId().equals(player.getUniqueId())) {
                        selectedCat.setSitting(true);
                    }
                }

            }
        }
    }

    public void catStand(PlayerInteractEvent event){
        World world = event.getPlayer().getWorld();
        Player player = event.getPlayer();
        player.sendMessage(player.getUniqueId(), "Your cats are standing!");
        for (Entity selectedEntity : world.getLivingEntities()) {
            if (selectedEntity instanceof Tameable) {
                Cat selectedCat = (Cat) selectedEntity;
                if(selectedCat.isTamed()){
                    if (selectedCat.getOwnerUniqueId().equals(player.getUniqueId())) {
                        selectedCat.setSitting(false);
                    }
                }

            }
        }
    }

    public void parrotSit(PlayerInteractEvent event){
        World world = event.getPlayer().getWorld();
        Player player = event.getPlayer();
        player.sendMessage(player.getUniqueId(), "Your cats are standing!");
        for (Entity selectedEntity : world.getLivingEntities()) {
            if (selectedEntity instanceof Tameable) {
                Parrot selectedParrot = (Parrot) selectedEntity;
                if(selectedParrot.isTamed()){
                    if (selectedParrot.getOwnerUniqueId().equals(player.getUniqueId())) {
                        selectedParrot.setSitting(true);
                    }
                }

            }
        }
    }

    public void parrotStand(PlayerInteractEvent event){
        World world = event.getPlayer().getWorld();
        Player player = event.getPlayer();
        player.sendMessage(player.getUniqueId(), "Your cats are standing!");
        for (Entity selectedEntity : world.getLivingEntities()) {
            if (selectedEntity instanceof Tameable) {
                Parrot selectedParrot = (Parrot) selectedEntity;
                if(selectedParrot.isTamed()){
                    if (selectedParrot.getOwnerUniqueId().equals(player.getUniqueId())) {
                        selectedParrot.setSitting(false);
                    }
                }

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


    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getHand().toString().equals("OFF_HAND")) {
            itemInHand = event.getPlayer().getInventory().getItemInOffHand();
        }
        Material itemInHandType = itemInHand.getType();
        if(itemInHandType!=Material.AIR) {
            String itemInHandDisplayName = itemInHand.getItemMeta().getDisplayName();
            if (itemInHandType.equals(Material.IRON_NUGGET) && itemInHandDisplayName.equalsIgnoreCase("sitting parrot whistle")) {
                untamePet(event);
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event){
        ItemStack itemInHand = event.getPlayer().getInventory().getItemInMainHand();
        if (event.getHand().toString().equals("OFF_HAND")) {
            itemInHand = event.getPlayer().getInventory().getItemInOffHand();
        }
        Material itemInHandType = itemInHand.getType();
        if(itemInHandType!=Material.AIR){
            String itemInHandDisplayName = itemInHand.getItemMeta().getDisplayName();
            if (itemInHandType.equals(Material.IRON_NUGGET) && itemInHandDisplayName.equalsIgnoreCase("sitting dog whistle")) {
                dogSit(event);
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            }
            if (itemInHandType.equals(Material.IRON_NUGGET) && itemInHandDisplayName.equalsIgnoreCase("standing dog whistle")) {
                dogStand(event);
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            }
            if (itemInHandType.equals(Material.IRON_NUGGET) && itemInHandDisplayName.equalsIgnoreCase("calming dog whistle")) {
                dogCalm(event);
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            }
            if (itemInHandType.equals(Material.IRON_NUGGET) && itemInHandDisplayName.equalsIgnoreCase("sitting cat whistle")) {
                catSit(event);
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            }
            if (itemInHandType.equals(Material.IRON_NUGGET) && itemInHandDisplayName.equalsIgnoreCase("standing cat whistle")) {
                catStand(event);
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            }
            if (itemInHandType.equals(Material.IRON_NUGGET) && itemInHandDisplayName.equalsIgnoreCase("standing parrot whistle")) {
                parrotStand(event);
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            }
            if (itemInHandType.equals(Material.IRON_NUGGET) && itemInHandDisplayName.equalsIgnoreCase("sitting parrot whistle")) {
                parrotSit(event);
                itemInHand.setAmount(itemInHand.getAmount() - 1);
            }
        }



    }






}