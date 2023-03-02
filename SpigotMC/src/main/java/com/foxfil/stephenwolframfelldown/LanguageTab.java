package com.foxfil.stephenwolframfelldown;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LanguageTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> results = new ArrayList<>();
        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("english", "russian"), new ArrayList<>());
        }

        return results;
    }
}
