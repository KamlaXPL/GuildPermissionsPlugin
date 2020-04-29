package me.kamlax.guildpermissions.listener;

import me.kamlax.guildpermissions.GuildPermissionsPlugin;
import me.kamlax.guildpermissions.helper.ChatHelper;
import me.kamlax.guildpermissions.data.UserData;
import net.dzikoysk.funnyguilds.basic.guild.Guild;
import net.dzikoysk.funnyguilds.basic.guild.Region;
import net.dzikoysk.funnyguilds.basic.guild.RegionUtils;
import net.dzikoysk.funnyguilds.basic.user.User;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListeners implements Listener {
    private final GuildPermissionsPlugin plugin;

    public PlayerListeners(GuildPermissionsPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onBreak(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final UserData userData = this.plugin.getUserManager().getUser(player.getUniqueId());
        final User user = User.get(player);
        final Region region = RegionUtils.getAt(player.getLocation());

        if (region == null) return;

        final Guild guild = region.getGuild();

        if (user.hasGuild()) {
            if (guild.getMembers().contains(user)) {
                if (!userData.isBreak_blocks() && !user.isOwner()) {
                    event.setCancelled(true);
                    ChatHelper.sendMessage(player, this.plugin.getConfiguration().getBlocksBreak());
                }
            }
        }
    }

    @EventHandler
    public void onPlace(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final UserData userData = this.plugin.getUserManager().getUser(player.getUniqueId());
        final User user = User.get(player);
        final Region region = RegionUtils.getAt(player.getLocation());

        if (region == null) return;

        final Guild guild = region.getGuild();

        if (user.hasGuild()) {
            if (guild.getMembers().contains(user)) {
                if (!userData.isPlace_blocks() && !user.isOwner()) {
                    event.setCancelled(true);
                    ChatHelper.sendMessage(player, this.plugin.getConfiguration().getBlocksPlace());
                }
            }
        }
    }

    @EventHandler
    public void onPlaceTnT(final BlockPlaceEvent event) {
        final Player player = event.getPlayer();
        final UserData userData = this.plugin.getUserManager().getUser(player.getUniqueId());
        final User user = User.get(player);
        final Region region = RegionUtils.getAt(player.getLocation());

        if (region == null) return;

        final Guild guild = region.getGuild();

        if (user.hasGuild()) {
            if (guild.getMembers().contains(user)) {
                if (event.getBlock().getTypeId() == 46) {
                    if (!userData.isPlace_tnt() && !user.isOwner()) {
                        event.setCancelled(true);
                        ChatHelper.sendMessage(player, this.plugin.getConfiguration().getPlaceTnt());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final UserData userData = this.plugin.getUserManager().getUser(player.getUniqueId());
        final User user = User.get(player);
        final Region region = RegionUtils.getAt(player.getLocation());

        if (region == null) return;

        final Guild guild = region.getGuild();

        if (user.hasGuild()) {
            if (guild.getMembers().contains(user)) {
                if (event.hasItem() && event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName() && event.getItem().getItemMeta().getDisplayName().equals(ChatHelper.fixText(this.plugin.getConfiguration().getNameThrowTnT())) && event.getMaterial() == Material.TNT) {
                    if (!userData.isPlace_tnt() && !user.isOwner()) {
                        event.setCancelled(true);
                        ChatHelper.sendMessage(player, this.plugin.getConfiguration().getThrowTnt());
                    }
                }
                if (event.getClickedBlock().getType() == Material.CHEST) {
                    if (!userData.isOpen_chest() && !user.isOwner()) {
                        event.setCancelled(true);
                        ChatHelper.sendMessage(player, this.plugin.getConfiguration().getOpenChest());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onChestOpen(final InventoryOpenEvent event) {
        final Player player = (Player) event.getPlayer();
        final UserData userData = this.plugin.getUserManager().getUser(player.getUniqueId());
        final User user = User.get(player);
        final Region region = RegionUtils.getAt(player.getLocation());

        if (region == null) return;

        final Guild guild = region.getGuild();

        if (user.hasGuild()) {
            if (guild.getMembers().contains(user)) {
                if (!userData.isOpen_chest() && !user.isOwner()) {
                    event.setCancelled(true);
                    ChatHelper.sendMessage(player, this.plugin.getConfiguration().getOpenChest());
                }
            }
        }
    }


    @EventHandler
    public void onBreakBeacon(final BlockBreakEvent event) {
        final Player player = event.getPlayer();
        final UserData userData = this.plugin.getUserManager().getUser(player.getUniqueId());
        final User user = User.get(player);
        final Region region = RegionUtils.getAt(player.getLocation());

        if (region == null) return;

        final Guild guild = region.getGuild();

        if (user.hasGuild()) {
            if (guild.getMembers().contains(user)) {
                if (event.getBlock().getTypeId() == 138) {
                    if (!userData.isBreak_beacon() && !user.isOwner()) {
                        event.setCancelled(true);
                        ChatHelper.sendMessage(player, this.plugin.getConfiguration().getBreakBeacon());
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocessEvent(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        final UserData userData = this.plugin.getUserManager().getUser(player.getUniqueId());
        final User user = User.get(player);
        final String command = event.getMessage().toLowerCase().split(" ")[0];
        final Region region = RegionUtils.getAt(player.getLocation());

        if (region == null) return;

        final Guild guild = region.getGuild();

        if (user.hasGuild()) {
            if (guild.getMembers().contains(user)) {
                for (final String string : this.plugin.getConfiguration().getBlockedCommands())
                    if (command.equalsIgnoreCase("/"+string)) {
                        if (!userData.isTeleport_players() && !user.isOwner()) {
                            event.setCancelled(true);
                            ChatHelper.sendMessage(player, this.plugin.getConfiguration().getTeleportPlayers());
                        }
                    }
            }
        }
    }

    @EventHandler
    public void onBucketFill(final PlayerBucketFillEvent event) {
        final Player player = event.getPlayer();
        final UserData userData = this.plugin.getUserManager().getUser(player.getUniqueId());
        final User user = User.get(player);
        final Region region = RegionUtils.getAt(player.getLocation());

        if (region == null) return;

        final Guild guild = region.getGuild();

        if (user.hasGuild()) {
            if (guild.getMembers().contains(user)) {
                if (!userData.isSpilling_water_lava() && !user.isOwner()) {
                    event.setCancelled(true);
                    ChatHelper.sendMessage(player, this.plugin.getConfiguration().getSpillingWaterLava());
                }
            }
        }
    }

    @EventHandler
    public void onBucketEmpty(final PlayerBucketEmptyEvent event) {
        final Player player = event.getPlayer();
        final UserData userData = this.plugin.getUserManager().getUser(player.getUniqueId());
        final User user = User.get(player);
        final Region region = RegionUtils.getAt(player.getLocation());

        if (region == null) return;

        final Guild guild = region.getGuild();

        if (user.hasGuild()) {
            if (guild.getMembers().contains(user)) {
                if (!userData.isSpilling_water_lava() && !user.isOwner()) {
                    event.setCancelled(true);
                    ChatHelper.sendMessage(player, this.plugin.getConfiguration().getSpillingWaterLava());
                }
            }
        }
    }
}
