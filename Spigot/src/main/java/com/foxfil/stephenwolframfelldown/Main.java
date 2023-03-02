package com.foxfil.stephenwolframfelldown;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("StephenWolframFellDown was activated!");
        getCommand("summonstephen").setExecutor(new StephenWolframFellDownCommand());
        Bukkit.getPluginManager().registerEvents(new StephenWolframEvents(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("StephenWolframFellDown was deactivated.");
    }
}
