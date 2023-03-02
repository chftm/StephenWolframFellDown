package com.foxfil.stephenwolframfelldown;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LanguageCommand implements CommandExecutor {
    public Main main;
    public LanguageCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (sender.hasPermission("setlanguage.permission")) {
                if (args.length == 1) {
                    if (args[0].equals("english")) {
                        if (main.getConfig().getString("language").equals("russian")) {
                            main.getConfig().set("language", "english");
                            player.sendMessage(ChatColor.GRAY + "Your language was changed to English.");
                        } else {
                            player.sendMessage(ChatColor.RED + "[!] Your language is already English!");
                        }
                    } else if (args[0].equals("russian")) {
                        if (main.getConfig().getString("language").equals("english")) {
                            main.getConfig().set("language", "russian");
                            player.sendMessage(ChatColor.GRAY + "Ваш язык был сменен на русский.");
                        } else {
                            player.sendMessage(ChatColor.RED + "[!] Ваш язык уже русский!");
                        }
                    } else {
                        if (main.getConfig().getString("language").equals("english")) {
                            player.sendMessage(ChatColor.RED + "[!] Plugin do not support this language yet!");
                        } else {
                            player.sendMessage(ChatColor.RED + "[!] Плагин еще не поддерживает этот язык!");
                        }
                    }
                } else {
                    if (main.getConfig().getString("language").equals("english")) {
                        player.sendMessage(ChatColor.RED + "[!] You should use arguments in this command!");
                    } else {
                        player.sendMessage(ChatColor.RED + "[!] Вы должны использовать аргументы в этой команде!");
                    }
                }
            } else {
                if (main.getConfig().getString("language").equals("english")) {
                    player.sendMessage(ChatColor.RED + "[!] You don't have permission to use this command!");
                } else {
                    player.sendMessage(ChatColor.RED + "[!] У вас нет разрешения на использование этой команды!!");
                }
            }
        }


        return false;
    }
}
