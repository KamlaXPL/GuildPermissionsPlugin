package me.kamlax.guildpermissions.configuration;

import me.kamlax.guildpermissions.GuildPermissionsPlugin;

import java.util.List;

public class MainConfiguration {
    private final GuildPermissionsPlugin plugin;
    private String mysqlHost, mysqlUser, mysqlPass, mysqlBase;
    private int mysqlPort;
    private String blocksBreak, blocksPlace, placeTnt, throwTnt, breakBeacon, teleportPlayers, openChest, spillingWaterLava, nameThrowTnT;
    private String noGuild, onlyLeader, correctUsage, manageYourPermissions, hasNotGuild, playerOffline, noInYourGuild;
    private List<String> blockedCommands;

    public MainConfiguration(final GuildPermissionsPlugin plugin) {
        this.plugin = plugin;
        this.loadConfiguration();
    }

    private void loadConfiguration() {
        this.plugin.saveDefaultConfig();
        this.mysqlHost = this.plugin.getConfig().getString("mysql.host");
        this.mysqlUser = this.plugin.getConfig().getString("mysql.user");
        this.mysqlPort = this.plugin.getConfig().getInt("mysql.port");
        this.mysqlPass = this.plugin.getConfig().getString("mysql.pass");
        this.mysqlBase = this.plugin.getConfig().getString("mysql.base");

        this.blocksBreak = this.plugin.getConfig().getString("messages.blocks.break");
        this.blocksPlace = this.plugin.getConfig().getString("messages.blocks.place");
        this.placeTnt = this.plugin.getConfig().getString("messages.place_tnt");
        this.throwTnt = this.plugin.getConfig().getString("messages.throw_tnt");
        this.breakBeacon = this.plugin.getConfig().getString("messages.break_beacon");
        this.teleportPlayers = this.plugin.getConfig().getString("messages.teleport_players");
        this.openChest = this.plugin.getConfig().getString("messages.open_chest");
        this.spillingWaterLava = this.plugin.getConfig().getString("messages.spilling_water_lava");

        this.noGuild = this.plugin.getConfig().getString("messages.command.no_guild");
        this.onlyLeader = this.plugin.getConfig().getString("messages.command.only_leader");
        this.correctUsage = this.plugin.getConfig().getString("messages.command.correct_usage");
        this.manageYourPermissions = this.plugin.getConfig().getString("messages.command.manage_your_permissions");
        this.hasNotGuild = this.plugin.getConfig().getString("messages.command.has_not_guild");
        this.playerOffline = this.plugin.getConfig().getString("messages.command.player_offline");
        this.noInYourGuild = this.plugin.getConfig().getString("messages.command.no_in_your_guild");

        this.nameThrowTnT = this.plugin.getConfig().getString("settings.name_throw_tnt");
        this.blockedCommands = this.plugin.getConfig().getStringList("settings.blocked_commands");
    }

    public int getMysqlPort() {
        return mysqlPort;
    }

    public String getMysqlBase() {
        return mysqlBase;
    }

    public String getMysqlHost() {
        return mysqlHost;
    }

    public String getMysqlPass() {
        return mysqlPass;
    }

    public String getMysqlUser() {
        return mysqlUser;
    }

    public List<String> getBlockedCommands() {
        return blockedCommands;
    }

    public String getBlocksBreak() {
        return blocksBreak;
    }

    public String getBlocksPlace() {
        return blocksPlace;
    }

    public String getBreakBeacon() {
        return breakBeacon;
    }

    public String getOpenChest() {
        return openChest;
    }

    public String getPlaceTnt() {
        return placeTnt;
    }

    public String getThrowTnt() {
        return throwTnt;
    }

    public String getSpillingWaterLava() {
        return spillingWaterLava;
    }

    public String getTeleportPlayers() {
        return teleportPlayers;
    }

    public String getNameThrowTnT() {
        return nameThrowTnT;
    }

    public String getCorrectUsage() {
        return correctUsage;
    }

    public String getHasNotGuild() {
        return hasNotGuild;
    }

    public String getManageYourPermissions() {
        return manageYourPermissions;
    }

    public String getNoGuild() {
        return noGuild;
    }

    public String getNoInYourGuild() {
        return noInYourGuild;
    }
    public String getOnlyLeader() {
        return onlyLeader;
    }

    public String getPlayerOffline() {
        return playerOffline;
    }
}
