package com.foxfil.stephenwolframfelldown;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class StephenWolframFellDownCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("summonstephen.permission")) {

                // Chestplate
                ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
                LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
                chestplateMeta.setColor(Color.fromRGB(153, 221, 255));
                chestplate.setItemMeta(chestplateMeta);

                // Leggins
                ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS);
                LeatherArmorMeta legginsMeta = (LeatherArmorMeta) leggins.getItemMeta();
                legginsMeta.setColor(Color.fromRGB(77, 38, 0));
                leggins.setItemMeta(legginsMeta);

                // Boots
                ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
                LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
                bootsMeta.setColor(Color.BLACK);
                boots.setItemMeta(bootsMeta);

                // Head (Stephen Wolfram)
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta headMeta = (SkullMeta) head.getItemMeta();
                GameProfile profile = new GameProfile(UUID.randomUUID(), null);
                profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjRhNTY1YjE0NTQxNzBmM2VkMTczMzJmOTQxZDZhNjM0ZTQ3MGE5ZjhlYThjMzIwYWM0NDM5MWFiNDg0NWI1MiJ9fX0"));
                Field field;
                try {
                    field = headMeta.getClass().getDeclaredField("profile");
                    field.setAccessible(true);
                    field.set(headMeta, profile);
                } catch (NoSuchFieldException | IllegalAccessException error) {
                    player.sendMessage(ChatColor.RED + "[!] Something went wrong!");
                    error.printStackTrace();
                    return false;
                }
                head.setItemMeta(headMeta);


                // Creating an armor stand
                ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
                stand.setBasePlate(false);
                stand.setArms(true);
                stand.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
                stand.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
                stand.getEquipment().setBoots(boots);
                stand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
                stand.getEquipment().setChestplate(chestplate);
                stand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
                stand.getEquipment().setLeggings(leggins);
                stand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
                stand.getEquipment().setHelmet(head);
                stand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
                stand.setCustomName("Stephen");
                stand.setCanPickupItems(false);
                stand.setGravity(false);
                stand.setRotation(90, 0);

                // Firework
                Firework firework = player.getWorld().spawn(stand.getLocation(), Firework.class);
                FireworkMeta fireworkMeta = (FireworkMeta) firework.getFireworkMeta();
                fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.GREEN).with(FireworkEffect.Type.BALL).build());
                fireworkMeta.setPower(1);
                firework.setFireworkMeta(fireworkMeta);
                firework.setCustomName("StephenFirework");
                firework.detonate();

                player.sendMessage(ChatColor.GREEN + "Stephen Wolfram was successfully summoned!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1.0F, 1.0F);


            } else {
                player.sendMessage(ChatColor.RED + "[!] You don't have permission to use this command!");
            }
        }




        return false;
    }
}
