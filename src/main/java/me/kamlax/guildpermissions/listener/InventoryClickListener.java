package me.kamlax.guildpermissions.listener;

import me.kamlax.guildpermissions.GuildPermissionsPlugin;
import me.kamlax.guildpermissions.helper.ChatHelper;
import me.kamlax.guildpermissions.data.UserData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class InventoryClickListener implements Listener {
    private final GuildPermissionsPlugin plugin;

    public InventoryClickListener(final GuildPermissionsPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onClickInventory(final InventoryClickEvent event) {
        final String[] split;
        if ((split = event.getInventory().getName().split(" ")).length == 2 && split[0].equalsIgnoreCase(ChatHelper.fixText("&7Uprawnienia&8:&a"))) {
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
            final Player member = Bukkit.getPlayer(split[1]);

            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || event.getCurrentItem().getItemMeta().getDisplayName() == null) return;

            final ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
            final List<String> break_blocks_lore = itemMeta.getLore(),
                    place_blocks_lore = itemMeta.getLore(),
                    place_tnt_lore = itemMeta.getLore(),
                    break_beacon_lore = itemMeta.getLore(),
                    teleport_players_lore = itemMeta.getLore(),
                    open_chest_lore = itemMeta.getLore(),
                    spilling_water_lava_lore = itemMeta.getLore();

            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatHelper.fixText("&8>> &aNiszczenie blokow na terenie gildii"))) {
                if (!member.isOnline()) return;
                final UserData userData = this.plugin.getUserManager().getUser(member.getUniqueId());
                userData.setBreak_blocks(!userData.isBreak_blocks());
                break_blocks_lore.set(1, ChatHelper.fixText(" &8>> &7Kliknij, aby &" + (userData.isBreak_blocks() ? "czabrac uprawnienie" : "anadac uprawnienie")));
                itemMeta.setLore(break_blocks_lore);
                event.getCurrentItem().setItemMeta(itemMeta);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatHelper.fixText("&8>> &aStawianie blokow na terenie gildii"))) {
                if (!member.isOnline()) return;
                final UserData userData = this.plugin.getUserManager().getUser(member.getUniqueId());
                userData.setPlace_blocks(!userData.isPlace_blocks());
                place_blocks_lore.set(1, ChatHelper.fixText(" &8>> &7Kliknij, aby &" + (userData.isPlace_blocks() ? "czabrac uprawnienie" : "anadac uprawnienie")));
                itemMeta.setLore(place_blocks_lore);
                event.getCurrentItem().setItemMeta(itemMeta);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatHelper.fixText("&8>> &aStawianie tnt na terenie gildii"))) {
                if (!member.isOnline()) return;
                final UserData userData = this.plugin.getUserManager().getUser(member.getUniqueId());
                userData.setPlace_tnt(!userData.isPlace_tnt());
                place_tnt_lore.set(1, ChatHelper.fixText(" &8>> &7Kliknij, aby &" + (userData.isPlace_tnt() ? "czabrac uprawnienie" : "anadac uprawnienie")));
                itemMeta.setLore(place_tnt_lore);
                event.getCurrentItem().setItemMeta(itemMeta);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals(ChatHelper.fixText("&8>> &aNiszczenie beacona na terenie gildii"))) {
                if (!member.isOnline()) return;
                final UserData userData = this.plugin.getUserManager().equalsIgnoreCase(member.getUniqueId());
                userData.setBreak_beacon(!userData.isBreak_beacon());
                break_beacon_lore.set(1, ChatHelper.fixText(" &8>> &7Kliknij, aby &" + (userData.isBreak_beacon() ? "czabrac uprawnienie" : "anadac uprawnienie")));
                itemMeta.setLore(break_beacon_lore);
                event.getCurrentItem().setItemMeta(itemMeta);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatHelper.fixText("&8>> &aTeleportowanie osob na terenie gildii"))) {
                if (!member.isOnline()) return;
                final UserData userData = this.plugin.getUserManager().getUser(member.getUniqueId());
                userData.setTeleport_players(!userData.isTeleport_players());
                teleport_players_lore.set(1, ChatHelper.fixText(" &8>> &7Kliknij, aby &" + (userData.isTeleport_players() ? "czabrac uprawnienie" : "anadac uprawnienie")));
                itemMeta.setLore(teleport_players_lore);
                event.getCurrentItem().setItemMeta(itemMeta);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatHelper.fixText("&8>> &aOtwieranie skrzynek na terenie gildii"))) {
                if (!member.isOnline()) return;
                final UserData userData = this.plugin.getUserManager().getUser(member.getUniqueId());
                userData.setOpen_chest(!userData.isOpen_chest());
                open_chest_lore.set(1, ChatHelper.fixText(" &8>> &7Kliknij, aby &" + (userData.isOpen_chest() ? "czabrac uprawnienie" : "anadac uprawnienie")));
                itemMeta.setLore(open_chest_lore);
                event.getCurrentItem().setItemMeta(itemMeta);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatHelper.fixText("&8>> &aWylewanie lawy/wody na terenie gildii"))) {
                if (!member.isOnline()) return;
                final UserData userData = this.plugin.getUserManager().getUser(member.getUniqueId());
                userData.setSpilling_water_lava(!userData.isSpilling_water_lava());
                spilling_water_lava_lore.set(1, ChatHelper.fixText(" &8>> &7Kliknij, aby &" + (userData.isSpilling_water_lava() ? "czabrac uprawnienie" : "anadac uprawnienie")));
                itemMeta.setLore(spilling_water_lava_lore);
                event.getCurrentItem().setItemMeta(itemMeta);
            }
        }
    }
}
