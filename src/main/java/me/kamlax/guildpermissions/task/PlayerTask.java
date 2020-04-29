package me.kamlax.guildpermissions.task;

import me.kamlax.guildpermissions.GuildPermissionsPlugin;
import me.kamlax.guildpermissions.data.UserData;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerTask extends BukkitRunnable {

    private final GuildPermissionsPlugin plugin;

    public PlayerTask(final GuildPermissionsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (final Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            final UserData userData = this.plugin.getUserManager().getUser(onlinePlayer.getUniqueId());
            final User user = User.get(onlinePlayer);
            if (user.hasGuild() && userData == null) {
                this.plugin.getUserManager().createUser(onlinePlayer.getUniqueId());
            }
            if (userData != null && !user.hasGuild()) {
                this.plugin.getUserManager().deleteUser(userData);
            }
        }
    }
}
