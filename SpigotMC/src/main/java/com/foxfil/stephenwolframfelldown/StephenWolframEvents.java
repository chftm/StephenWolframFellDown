package com.foxfil.stephenwolframfelldown;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.EulerAngle;

import java.lang.reflect.Field;
import java.util.UUID;

public class StephenWolframEvents implements Listener {

    public Main main;
    public StephenWolframEvents(Main main) {
        this.main = main;
    }

    @EventHandler
    public boolean onEntityInteract (PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked().getType().equals(EntityType.ARMOR_STAND)) {
            if (e.getRightClicked().getCustomName() != null) {
                if (e.getRightClicked().getCustomName().equals("Stephen")) {
                    ArmorStand rotatesStand = (ArmorStand) e.getRightClicked();
                    Player player = e.getPlayer();
                    Location layLoc = rotatesStand.getLocation();

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
                        if (main.getConfig().getString("language").equals("english")) {
                            player.sendMessage(ChatColor.RED + "[!] Something went wrong!");
                        } else {
                            player.sendMessage(ChatColor.RED + "[!] Что-то пошло не так!");
                        }
                        error.printStackTrace();
                        return false;
                    }
                    head.setItemMeta(headMeta);

                    // Spawn two armor stands
                    // head + body
                    ArmorStand top = (ArmorStand) player.getWorld().spawnEntity(layLoc.add(0, -1.3, 0), EntityType.ARMOR_STAND);
                    top.setGravity(false);
                    top.setBasePlate(false);
                    top.setCanPickupItems(false);
                    top.setMarker(false);
                    top.setSmall(false);
                    top.setVisible(false);
                    top.setArms(true);
                    top.setCustomName("StephenThatFellDown");
                    top.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
                    top.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
                    top.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING_OR_CHANGING);
                    top.getEquipment().setChestplate(chestplate);
                    top.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    top.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING_OR_CHANGING);
                    top.getEquipment().setHelmet(head);
                    top.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);

                    // boots + leggins
                    ArmorStand base = (ArmorStand) player.getWorld().spawnEntity(layLoc.add(0.5, 0.63, 0), EntityType.ARMOR_STAND);
                    base.setGravity(false);
                    base.setBasePlate(false);
                    base.setCanPickupItems(false);
                    base.setVisible(false);
                    base.setArms(false);
                    base.setCustomName("StephenThatFellDown");
                    base.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
                    base.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
                    base.getEquipment().setBoots(boots);
                    base.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    base.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING_OR_CHANGING);
                    base.getEquipment().setLeggings(leggins);
                    base.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.REMOVING_OR_CHANGING);
                    base.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.ADDING_OR_CHANGING);

                    // Set poses to make the two armor stands look like one horizontal armor stand
                    top.setHeadPose(new EulerAngle(Math.toRadians(90), 0, 0));
                    top.setBodyPose(new EulerAngle(Math.toRadians(90), 0, 0));
                    top.setRightArmPose(new EulerAngle(Math.toRadians(90), 0, 0));
                    top.setLeftArmPose(new EulerAngle(Math.toRadians(90), 0, 0));

                    base.setRightLegPose(new EulerAngle(Math.toRadians(90), 0, 0));
                    base.setLeftLegPose(new EulerAngle(Math.toRadians(90), 0, 0));

                    rotatesStand.remove();
                    if (main.getConfig().getString("language").equals("english")) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§b§lStephen Wolfram fell down!"));
                    } else {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§b§lСтивен Вольфрам упал!"));
                    }

                } else if (e.getRightClicked().getCustomName().equals("StephenThatFellDown")) {
                    // Removing Stephen that fell
                    ArmorStand armor_stnd = (ArmorStand) e.getRightClicked();
                    for (Entity mob : armor_stnd.getNearbyEntities(0.5, 0.5, 0.5)) {
                        if (mob.getCustomName() != null) {
                            if (mob.getCustomName().equals("StephenThatFellDown")) {
                                mob.remove();
                            }
                        }
                    }
                    Location locSpawn = armor_stnd.getLocation();
                    armor_stnd.remove();

                    // Spawning Stephen
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
                        if (main.getConfig().getString("language").equals("english")) {
                            e.getPlayer().sendMessage(ChatColor.RED + "[!] Something went wrong!");
                        } else {
                            e.getPlayer().sendMessage(ChatColor.RED + "[!] Что-то пошло не так!");
                        }
                        error.printStackTrace();
                        return false;
                    }
                    head.setItemMeta(headMeta);


                    // Creating an armor stand
                    ArmorStand stand = (ArmorStand) e.getPlayer().getWorld().spawnEntity(locSpawn.add(0, 1.3, 0), EntityType.ARMOR_STAND);
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
                }
            }
        }
        return false;
    }

    @EventHandler
    public void onEntityDamagedByEntity (EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Firework) {
            Firework fw = (Firework) e.getDamager();
            if (fw.getCustomName() != null) {
                if (fw.getCustomName().equals("StephenFirework")) {
                    e.setCancelled(true);
                }
            }
        }


        if (e.getDamager() instanceof Player && e.getEntity() instanceof ArmorStand) {
            if (e.getEntity().getCustomName() != null) {
                if (e.getEntity().getCustomName().equals("Stephen")) {
                    e.setCancelled(true);
                    ArmorStand armr_stnd = (ArmorStand) e.getEntity();
                    armr_stnd.remove();


                    // Firework
                    Firework firework = e.getEntity().getWorld().spawn(armr_stnd.getLocation(), Firework.class);
                    FireworkMeta fireworkMeta = (FireworkMeta) firework.getFireworkMeta();
                    fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BALL).build());
                    fireworkMeta.setPower(1);
                    firework.setFireworkMeta(fireworkMeta);
                    firework.setCustomName("StephenFirework");
                    firework.detonate();

                    if (main.getConfig().getString("language").equals("english")) {
                        e.getDamager().sendMessage(ChatColor.GRAY + "Stephen Wolfram was removed.");
                    } else {
                        e.getDamager().sendMessage(ChatColor.GRAY + "Стивен Вольфрам был удалён.");
                    }
                    ((Player) e.getDamager()).playSound(armr_stnd.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1.0F, 1.0F);
                } else if (e.getEntity().getCustomName().equals("StephenThatFellDown")) {
                    e.setCancelled(true);
                    ArmorStand armr_stnd = (ArmorStand) e.getEntity();
                    for (Entity mob : armr_stnd.getNearbyEntities(0.5, 0.5, 0.5)) {
                        if (mob.getCustomName() != null) {
                            if (mob.getCustomName().equals("StephenThatFellDown")) {
                                mob.remove();
                            }
                        }
                    }
                    ((Player) e.getDamager()).playSound(armr_stnd.getLocation(), Sound.ENTITY_VILLAGER_HURT, 1.0F, 1.0F);
                    armr_stnd.remove();


                    // Firework
                    Firework firework = e.getEntity().getWorld().spawn(armr_stnd.getLocation(), Firework.class);
                    FireworkMeta fireworkMeta = (FireworkMeta) firework.getFireworkMeta();
                    fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.RED).with(FireworkEffect.Type.BALL).build());
                    fireworkMeta.setPower(1);
                    firework.setFireworkMeta(fireworkMeta);
                    firework.setCustomName("StephenFirework");
                    firework.detonate();

                    if (main.getConfig().getString("language").equals("english")) {
                        e.getDamager().sendMessage(ChatColor.GRAY + "Stephen Wolfram was removed.");
                    } else {
                        e.getDamager().sendMessage(ChatColor.GRAY + "Стивен Вольфрам был удалён.");
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityDamaged (EntityDamageEvent e) {
        if (e.getEntity() instanceof ArmorStand) {
            if (e.getEntity().getCustomName() != null) {
                if (e.getEntity().getCustomName().equals("Stephen") | e.getEntity().getCustomName().equals("StephenThatFellDown")) {
                    e.setCancelled(true);
                }
            }
        }
    }

}