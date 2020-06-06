package me.kamlax.guildpermissions.inventory;

import me.kamlax.guildpermissions.GuildPermissionsPlugin;
import me.kamlax.guildpermissions.helper.ItemBuilderHelper;
import me.kamlax.guildpermissions.helper.ChatHelper;
import me.kamlax.guildpermissions.data.UserData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public final class GuildPermissionsInventory {

    private GuildPermissionsPlugin plugin;


    public GuildPermissionsInventory(final GuildPermissionsPlugin plugin) {
        this.plugin = plugin;
    }

        public Inventory createGuildPermissionInventory(final Player player) {
            final UserData userData = this.plugin.getUserManager().getUser(player.getUniqueId());
            final String user = Bukkit.getPlayer(userData.getUuid()).getName();
            final Inventory inventory = Bukkit.createInventory(null, 27, ChatHelper.fixText("&7Uprawnienia&8:&a" + " " + user));

            final ItemBuilderHelper break_blocks = new ItemBuilderHelper(Material.DIAMOND_PICKAXE, 1)
                    .setName("&8>> &aNiszczenie blokow na terenie gildii")
                    .addLore(Arrays.asList(
                            "",
                            " &8>> &7Kliknij, aby &" + (userData.isBreak_blocks() ? "czabrac uprawnienie" : "anadac uprawnienie"),
                            ""));

            final ItemBuilderHelper place_blocks = new ItemBuilderHelper(Material.STONE, 1)
                    .setName("&8>> &aStawianie blokow na terenie gildii")
                    .addLore(Arrays.asList(
                            "",
                            " &8>> &7Kliknij, aby &" + (userData.isPlace_blocks() ? "czabrac uprawnienie" : "anadac uprawnienie"),
                            ""));

            final ItemBuilderHelper place_tnt = new ItemBuilderHelper(Material.TNT, 1)
                    .setName("&8>> &aStawianie tnt na terenie gildii")
                    .addLore(Arrays.asList(
                            "",
                            " &8>> &7Kliknij, aby &" + (userData.isPlace_tnt() ? "czabrac uprawnienie" : "anadac uprawnienie"),
                            ""));

            final ItemBuilderHelper break_beacon = new ItemBuilderHelper(Material.BEACON, 1)
                    .setName("&8>> &aNiszczenie beacona na terenie gildii")
                    .addLore(Arrays.asList(
                            "",
                            " &8>> &7Kliknij, aby &" + (userData.isBreak_beacon() ? "czabrac uprawnienie" : "anadac uprawnienie"),
                            ""));

            final ItemBuilderHelper teleport_players = new ItemBuilderHelper(Material.WATCH, 1)
                    .setName("&8>> &aTeleportowanie osob na terenie gildii")
                    .addLore(Arrays.asList(
                            "",
                            " &8>> &7Kliknij, aby &" + (userData.isTeleport_players() ? "czabrac uprawnienie" : "anadac uprawnienie"),
                            ""));

            final ItemBuilderHelper open_chest = new ItemBuilderHelper(Material.CHEST, 1)
                    .setName("&8>> &aOtwieranie skrzynek na terenie gildii")
                    .addLore(Arrays.asList(
                            "",
                            " &8>> &7Kliknij, aby &" + (userData.isOpen_chest() ? "czabrac uprawnienie" : "anadac uprawnienie"),
                            ""));

            final ItemBuilderHelper spilling_water_lava = new ItemBuilderHelper(Material.BUCKET, 1)
                    .setName("&8>> &aWylewanie lawy/wody na terenie gildii")
                    .addLore(Arrays.asList(
                            "",
                            " &8>> &7Kliknij, aby &" + (userData.isSpilling_water_lava() ? "czabrac uprawnienie" : "anadac uprawnienie"),
                            ""));

            for (int i = 0; i < 27; ++i) {
                inventory.setItem(i, new ItemBuilderHelper(Material.STAINED_GLASS_PANE, (short) 15));
            }

            inventory.setItem(10, break_blocks.build());
            inventory.setItem(11, place_blocks.build());
            inventory.setItem(12, place_tnt.build());
            inventory.setItem(13, break_beacon.build());
            inventory.setItem(14, teleport_players.build());
            inventory.setItem(15, open_chest.build());
            inventory.setItem(16, spilling_water_lava.build());
            return inventory;
    }
}
