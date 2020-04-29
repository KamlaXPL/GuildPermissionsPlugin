package me.kamlax.guildpermissions.command;

import me.kamlax.guildpermissions.GuildPermissionsPlugin;
import me.kamlax.guildpermissions.helper.ChatHelper;
import me.kamlax.guildpermissions.inventory.GuildPermissionsInventory;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionsCommand implements CommandExecutor {

    private final GuildPermissionsPlugin plugin;

    private final GuildPermissionsInventory guildPermissionsInventory;

    public PermissionsCommand(GuildPermissionsPlugin plugin, GuildPermissionsInventory guildPermissionsInventory) {
        this.plugin = plugin;
        this.guildPermissionsInventory = guildPermissionsInventory;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String... args) {
        final Player player = (Player)sender;

        final User user = User.get(player);

        if (!user.hasGuild()) return ChatHelper.sendMessage(player, this.plugin.getConfiguration().getNoGuild());

        if (!user.isOwner()) return ChatHelper.sendMessage(player, this.plugin.getConfiguration().getOnlyLeader());

        if (args.length == 0) return ChatHelper.sendMessage(player, this.plugin.getConfiguration().getCorrectUsage());

        final Player target = Bukkit.getPlayer(args[0]);

        final User targetUser = User.get(target.getUniqueId());

        if (args[0].equalsIgnoreCase(player.getName())) return ChatHelper.sendMessage(player, this.plugin.getConfiguration().getManageYourPermissions());

        if (targetUser == null || !targetUser.hasGuild()) return ChatHelper.sendMessage(player, this.plugin.getConfiguration().getHasNotGuild());

        if (!targetUser.isOnline()) return ChatHelper.sendMessage(player, this.plugin.getConfiguration().getPlayerOffline());

        if (!targetUser.getGuild().getTag().equalsIgnoreCase(targetUser.getGuild().getTag())) return ChatHelper.sendMessage(player, this.plugin.getConfiguration().getNoInYourGuild());

        player.openInventory(guildPermissionsInventory.createGuildPermissionInventory(target));

        return false;
    }
}
