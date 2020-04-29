package me.kamlax.guildpermissions.data;

import me.kamlax.guildpermissions.GuildPermissionsPlugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserData {
    private final UUID uuid;
    private GuildPermissionsPlugin plugin;

    private boolean break_blocks, place_blocks, place_tnt, break_beacon, teleport_players, open_chest, spilling_water_lava;

    public UserData(final UUID uuid, final GuildPermissionsPlugin plugin) {
        this.uuid = uuid;
        this.plugin = plugin;
    }

    public UserData(final ResultSet resultSet) throws SQLException {
        this.uuid = UUID.fromString(resultSet.getString("uuid"));
        this.break_blocks = resultSet.getBoolean("break_blocks");
        this.place_blocks = resultSet.getBoolean("place_blocks");
        this.place_tnt = resultSet.getBoolean("place_tnt");
        this.break_beacon = resultSet.getBoolean("break_beacon");
        this.teleport_players = resultSet.getBoolean("teleport_players");
        this.open_chest = resultSet.getBoolean("open_chest");
        this.spilling_water_lava = resultSet.getBoolean("spilling_water_lava");
    }

    public void insert() {
        this.plugin.getDatabaseManager().update("INSERT INTO `guildpermissions_users` (`uuid`, `break_blocks`, `place_blocks`, `place_tnt`, `break_beacon`, `teleport_players`, `open_chest`, `spilling_water_lava`) VALUES (" +
                "'" + this.uuid + "'," +
                "'" + (this.break_blocks ? 1 : 0) + "', " +
                "'"+(this.place_blocks ? 1 : 0) + "', " +
                "'"+(this.place_tnt ? 1 : 0) + "', " +
                "'"+(this.break_beacon ? 1 : 0) + "', " +
                "'"+(this.teleport_players ? 1 : 0) + "', " +
                "'"+(this.open_chest ? 1 : 0) + "', " +
                "'"+(this.spilling_water_lava ? 1 : 0) + "');");
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public boolean isBreak_beacon() {
        return break_beacon;
    }

    public boolean isBreak_blocks() {
        return break_blocks;
    }

    public boolean isOpen_chest() {
        return open_chest;
    }

    public boolean isPlace_blocks() {
        return place_blocks;
    }

    public boolean isPlace_tnt() {
        return place_tnt;
    }

    public boolean isSpilling_water_lava() {
        return spilling_water_lava;
    }

    public boolean isTeleport_players() {
        return teleport_players;
    }

    public void setBreak_blocks(boolean break_blocks) {
        this.break_blocks = break_blocks;
        this.plugin.getDatabaseManager().update("UPDATE `guildpermissions_users` SET `break_blocks` = '" + (this.break_blocks ? 1 : 0) + "' WHERE `uuid` = '" + this.uuid + "'");
    }

    public void setBreak_beacon(boolean break_beacon) {
        this.break_beacon = break_beacon;
        this.plugin.getDatabaseManager().update("UPDATE `guildpermissions_users` SET `break_beacon` = '" + (this.break_beacon ? 1 : 0) + "' WHERE `uuid` = '" + this.uuid + "'");
    }

    public void setOpen_chest(boolean open_chest) {
        this.open_chest = open_chest;
        this.plugin.getDatabaseManager().update("UPDATE `guildpermissions_users` SET `open_chest` = '" + (this.open_chest ? 1 : 0) + "' WHERE `uuid` = '" + this.uuid + "'");
    }

    public void setPlace_blocks(boolean place_blocks) {
        this.place_blocks = place_blocks;
        this.plugin.getDatabaseManager().update("UPDATE `guildpermissions_users` SET `place_blocks` = '" + (this.place_blocks ? 1 : 0) + "' WHERE `uuid` = '" + this.uuid + "'");
    }

    public void setPlace_tnt(boolean place_tnt) {
        this.place_tnt = place_tnt;
        this.plugin.getDatabaseManager().update("UPDATE `guildpermissions_users` SET `place_tnt` = '" + (this.place_tnt ? 1 : 0) + "' WHERE `uuid` = '" + this.uuid + "'");
    }

    public void setSpilling_water_lava(boolean spilling_water_lava) {
        this.spilling_water_lava = spilling_water_lava;
        this.plugin.getDatabaseManager().update("UPDATE `guildpermissions_users` SET `spilling_water_lava` = '" + (this.spilling_water_lava ? 1 : 0) + "' WHERE `uuid` = '" + this.uuid + "'");
    }

    public void setTeleport_players(boolean teleport_players) {
        this.teleport_players = teleport_players;
        this.plugin.getDatabaseManager().update("UPDATE `guildpermissions_users` SET `teleport_players` = '" + (this.teleport_players ? 1 : 0) + "' WHERE `uuid` = '" + this.uuid + "'");
    }
}
