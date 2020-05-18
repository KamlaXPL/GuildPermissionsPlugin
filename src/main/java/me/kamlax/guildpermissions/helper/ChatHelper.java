package me.kamlax.guildpermissions.helper;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class ChatHelper {

    @Contract(pure = true)
    private ChatHelper() {
    }

    @NotNull
    public static String fixText(@NotNull String message) {
        return ChatColor.translateAlternateColorCodes('&', message
                .replace(">>", "\u00BB")
                .replace("<<", "\u00AB"));
    }

    @NotNull
    public static List<String> fixText(@NotNull List<String> messages) {
        return messages.stream()
                .map(ChatHelper::fixText)
                .collect(Collectors.toList());
    }

    public static boolean sendMessage(@NotNull final Player player, @NotNull final String message) {
        player.sendMessage(fixText(message));
        return true;
    }
}
