package me.kamlax.guildpermissions.command;

import me.kamlax.guildpermissions.GuildPermissionsPlugin;
import me.kamlax.guildpermissions.data.UserData;
import me.kamlax.guildpermissions.helper.ChatHelper;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GuildPermissionsCommand implements CommandExecutor {

    private final GuildPermissionsPlugin guildPermissionsPlugin;

    public GuildPermissionsCommand(final GuildPermissionsPlugin guildPermissionsPlugin) {
        this.guildPermissionsPlugin = guildPermissionsPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String... args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Ta komenda jest przeznaczona tylko dla graczy!");
            return true;
        }

        final Player player = (Player) sender;
        final User owner = User.get(player);

        if (!owner.hasGuild()) return ChatHelper.sendMessage(player, this.guildPermissionsPlugin.getConfiguration().getNoGuild());
        if (!owner.isOwner()) return ChatHelper.sendMessage(player, this.guildPermissionsPlugin.getConfiguration().getOnlyLeader());
        if (args.length == 0) return ChatHelper.sendMessage(player, this.guildPermissionsPlugin.getConfiguration().getCorrectUsage());

        final Player targetPlayer = Bukkit.getPlayer(args[0]);

        if (targetPlayer == null) return ChatHelper.sendMessage(player, this.guildPermissionsPlugin.getConfiguration().getPlayerOffline());

        final User user = User.get(targetPlayer.getUniqueId());
        final UserData userData = this.guildPermissionsPlugin.getUserManager().getUser(targetPlayer.getUniqueId());

        if (args[0].equalsIgnoreCase(player.getName())) return ChatHelper.sendMessage(player, this.guildPermissionsPlugin.getConfiguration().getManageYourPermissions());
        if (user == null || userData == null || !user.hasGuild()) return ChatHelper.sendMessage(player, this.guildPermissionsPlugin.getConfiguration().getHasNotGuild());
        if (!user.getGuild().getTag().equalsIgnoreCase(owner.getGuild().getTag())) return ChatHelper.sendMessage(player, this.guildPermissionsPlugin.getConfiguration().getNoInYourGuild());

        player.openInventory(this.guildPermissionsPlugin.getGuildPermissionsInventory().createGuildPermissionInventory(targetPlayer));
        return true;
    }
}
