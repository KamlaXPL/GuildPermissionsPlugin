package me.kamlax.guildpermissions.manager;

import me.kamlax.guildpermissions.GuildPermissionsPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    private final GuildPermissionsPlugin plugin;
    private Connection connection;

    public DatabaseManager(final GuildPermissionsPlugin plugin) {
        this.plugin = plugin;
        this.connect();
    }

    private void connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://"+
                            this.plugin.getConfiguration().getMysqlHost()+":"+
                            this.plugin.getConfiguration().getMysqlPort()+"/"+
                            this.plugin.getConfiguration().getMysqlBase(),
                    this.plugin.getConfiguration().getMysqlUser(),
                    this.plugin.getConfiguration().getMysqlPass());
            this.update("CREATE TABLE IF NOT EXISTS `guildpermissions_users` (`uuid` varchar(36) PRIMARY KEY NOT NULL, " +
                    "`break_blocks` int(1) NOT NULL," +
                    "`place_blocks` int(1) NOT NULL," +
                    "`place_tnt` int(1) NOT NULL,"+
                    "`break_beacon` int(1) NOT NULl,"+
                    "`teleport_players` int(1) NOT NULL,"+
                    "`open_chest` int(1) NOT NULL,"+
                    "`spilling_water_lava` int(1) NOT NULL);");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void update(final String update) {
        try (final PreparedStatement preparedStatement = this.connection.prepareStatement(update)) {
            if (preparedStatement == null) return;

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ResultSet query() {
        try {
            return this.connection.prepareStatement("SELECT * FROM `guildpermissions_users`").executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
