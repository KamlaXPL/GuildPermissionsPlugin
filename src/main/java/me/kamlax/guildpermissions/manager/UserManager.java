package me.kamlax.guildpermissions.manager;

import me.kamlax.guildpermissions.GuildPermissionsPlugin;
import me.kamlax.guildpermissions.data.UserData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class UserManager {


    private final GuildPermissionsPlugin plugin;
    private final HashMap<UUID, UserData> users;

    public UserManager(final GuildPermissionsPlugin plugin) {
        this.plugin = plugin;
        this.users = new HashMap<>();
        this.loadUsers();
    }


    public UserData getUser(final UUID uuid) {
        return this.users.get(uuid);
    }

    public void loadUsers() {
        try {
            final ResultSet resultSet = this.plugin.getDatabaseManager().query();
            while (resultSet.next()) {
                final UUID player = UUID.fromString(resultSet.getString("uuid"));
                final UserData user = new UserData(resultSet);
                this.users.put(player, user);
            }
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createUser(final UUID uuid) {
        final UserData user = new UserData(uuid, plugin);
        user.insert();
        this.users.put(user.getUuid(), user);
    }

    public void deleteUser(final UserData user) {
        this.users.remove(user.getUuid());
        this.plugin.getDatabaseManager().update("DELETE FROM `guildpermissions_users` WHERE `uuid` = '" + user.getUuid().toString() + "'");
    }
}
