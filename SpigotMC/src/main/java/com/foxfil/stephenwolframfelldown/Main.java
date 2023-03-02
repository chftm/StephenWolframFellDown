package com.foxfil.stephenwolframfelldown;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("StephenWolframFellDown was activated!");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("setlanguage").setExecutor(new LanguageCommand(this));
        getCommand("setlanguage").setTabCompleter(new LanguageTab());
        getCommand("summonstephen").setExecutor(new StephenWolframFellDownCommand(this));
        Bukkit.getPluginManager().registerEvents(new StephenWolframEvents(this), this);
    }

    @Override
    public void onDisable() {
        System.out.println("StephenWolframFellDown was deactivated.");
    }
}
