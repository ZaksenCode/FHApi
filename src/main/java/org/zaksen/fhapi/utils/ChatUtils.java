package org.zaksen.fhapi.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.zaksen.fhapi.FHApi;

public class ChatUtils {

    public static String formatText(String text) {
        return ChatColor.translateAlternateColorCodes(FHApi.getFormattedMessages().get("color_symbol").charAt(0), text);
    }

    public static void sendPrefixMSG(Player player, String message) {
        player.sendMessage(FHApi.getFormattedMessages().get("msg_prefix") + message);
    }
}