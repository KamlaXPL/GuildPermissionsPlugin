package me.kamlax.guildpermissions;

import me.kamlax.guildpermissions.command.PermissionsCommand;
import me.kamlax.guildpermissions.configuration.MainConfiguration;
import me.kamlax.guildpermissions.inventory.GuildPermissionsInventory;
import me.kamlax.guildpermissions.listener.InventoryClickListener;
import me.kamlax.guildpermissions.listener.PlayerListeners;
import me.kamlax.guildpermissions.manager.DatabaseManager;
import me.kamlax.guildpermissions.manager.UserManager;
import me.kamlax.guildpermissions.task.PlayerTask;
import org.bukkit.plugin.java.JavaPlugin;

public class GuildPermissionsPlugin extends JavaPlugin {

    private DatabaseManager databaseManager;
    private UserManager userManager;
    private MainConfiguration configuration;
    private GuildPermissionsInventory guildPermissionsInventory;

    public void onEnable() {
        configuration = new MainConfiguration(this);
        databaseManager = new DatabaseManager(this);
        userManager = new UserManager(this);
        guildPermissionsInventory = new GuildPermissionsInventory(this);
        new InventoryClickListener(this);
        new PlayerListeners(this);
        new PlayerTask(this).runTaskTimerAsynchronously(this, 60L, 100L);
        getCommand("uprawnienia").setExecutor(new PermissionsCommand(this, guildPermissionsInventory));
    }

    public void onDisable() {
        this.databaseManager.disconnect();
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public MainConfiguration getConfiguration() {
        return configuration;
    }
}
