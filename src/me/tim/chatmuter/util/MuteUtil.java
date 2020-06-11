package me.tim.chatmuter.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MuteUtil
{
    public static final HashSet<String> playersViewingChatAttempts = new HashSet<>();

    public static List<String> commandsMuted = new ArrayList<>();

    public static String chatMutedAnnouncement = "";
    public static String chatMuteLiftedAnnouncement = "";
    public static String chatMutedMessage = "";
    public static String bypassedChatMuteMessage = "";
    public static String checkChatMutedPositiveMessage = "";
    public static String checkChatMutedNegativeMessage = "";

    public static boolean isChatMuted;
    public static boolean sendStats;

    /**
     * Displays the chat muted message to the CommandSender if enabled via the config.
     * @param player
     */
    public static void displayChatMutedMessage(Player player)
    {
        if (!chatMutedMessage.equals(""))
            player.sendMessage(MuteUtil.chatMutedMessage.replace("%USERNAME%", player.getName()).replace("%DISPLAYNAME%", player.getDisplayName()).replace("%CUSTOMNAME%", player.getCustomName() == null ? player.getDisplayName() : player.getCustomName()));
    }

    /**
     * Displays the chat muted announcement to the CommandSender if enabled via the config.
     * @param sender
     */
    public static void displayChatMutedAnnouncement(CommandSender sender)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (!chatMutedAnnouncement.equals(""))
                Bukkit.getServer().broadcastMessage(MuteUtil.chatMutedAnnouncement.replace("%USERNAME%", player.getName()).replace("%DISPLAYNAME%", player.getDisplayName()).replace("%CUSTOMNAME%", player.getCustomName() == null ? player.getDisplayName() : player.getCustomName()));
        }
        else
        {
            if (!chatMutedAnnouncement.equals(""))
                Bukkit.getServer().broadcastMessage(MuteUtil.chatMutedAnnouncement.replace("%USERNAME%", sender.getName()).replace("%DISPLAYNAME%", sender.getName()).replace("%CUSTOMNAME%", sender.getName()));
        }
    }

    /**
     * Displays the chat mute lifted to the CommandSender if enabled via the config.
     * @param sender
     */
    public static void displayChatMuteLiftedAnnouncement(CommandSender sender)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (!chatMuteLiftedAnnouncement.equals(""))
                Bukkit.getServer().broadcastMessage(MuteUtil.chatMuteLiftedAnnouncement.replace("%USERNAME%", player.getName()).replace("%DISPLAYNAME%", player.getDisplayName()).replace("%CUSTOMNAME%", player.getCustomName() == null ? player.getDisplayName() : player.getCustomName()));
        }
        else
        {
            if (!chatMuteLiftedAnnouncement.equals(""))
                Bukkit.getServer().broadcastMessage(MuteUtil.chatMuteLiftedAnnouncement.replace("%USERNAME%", sender.getName()).replace("%DISPLAYNAME%", sender.getName()).replace("%CUSTOMNAME%", sender.getName()));
        }
    }

    /**
     * Displays the chat muted message to the player if enabled via the config.
     * @param sender
     */
    public static void displayBypassMessage(Player sender)
    {
        Player player = (Player) sender;
        if (!bypassedChatMuteMessage.equals(""))
            player.sendMessage(MuteUtil.bypassedChatMuteMessage.replace("%USERNAME%", player.getName()).replace("%DISPLAYNAME%", player.getDisplayName()).replace("%CUSTOMNAME%", player.getCustomName() == null ? player.getDisplayName() : player.getCustomName()));
    }

    /**
     * Displays the chat is muted message for /chatmuted
     * @param sender
     */
    public static void displayChatMutedPositiveMessage(CommandSender sender)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (!checkChatMutedPositiveMessage.equals(""))
                Bukkit.getServer().broadcastMessage(MuteUtil.checkChatMutedPositiveMessage.replace("%USERNAME%", player.getName()).replace("%DISPLAYNAME%", player.getDisplayName()).replace("%CUSTOMNAME%", player.getCustomName() == null ? player.getDisplayName() : player.getCustomName()));
        }
        else
        {
            if (!checkChatMutedPositiveMessage.equals(""))
                Bukkit.getServer().broadcastMessage(MuteUtil.checkChatMutedPositiveMessage.replace("%USERNAME%", sender.getName()).replace("%DISPLAYNAME%", sender.getName()).replace("%CUSTOMNAME%", sender.getName()));
        }
    }

    /**
     * Displays the chat is not muted message for /chatmuted
     * @param sender
     */
    public static void displayChatMutedNegativeMessage(CommandSender sender)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (!checkChatMutedNegativeMessage.equals(""))
                Bukkit.getServer().broadcastMessage(MuteUtil.checkChatMutedNegativeMessage.replace("%USERNAME%", player.getName()).replace("%DISPLAYNAME%", player.getDisplayName()).replace("%CUSTOMNAME%", player.getCustomName() == null ? player.getDisplayName() : player.getCustomName()));
        }
        else
        {
            if (!checkChatMutedNegativeMessage.equals(""))
                Bukkit.getServer().broadcastMessage(MuteUtil.checkChatMutedNegativeMessage.replace("%USERNAME%", sender.getName()).replace("%DISPLAYNAME%", sender.getName()).replace("%CUSTOMNAME%", sender.getName()));
        }
    }

    /**
     * If a player tries to chat during a mute and does not have permission, this method is called and handles
     * all forwarding messages such as with /showchatattempts and messaging the chatting player.
     */
    public static void handleChatDeniedDuringMute(Player player, String message)
    {
        MuteUtil.displayChatMutedMessage(player);
        for (Player p : Bukkit.getServer().getOnlinePlayers())
        {
            if (MuteUtil.playersViewingChatAttempts.contains(p.getName()))
            {
                p.sendMessage(ChatColor.RED + "The following message was blocked due to the server mute:");
                p.sendMessage("[" + player.getName() + "] " + message);
            }
        }

        ConsoleCommandSender consoleCommandSender = Bukkit.getServer().getConsoleSender();
        if (MuteUtil.playersViewingChatAttempts.contains(consoleCommandSender.getName()))
        {
            consoleCommandSender.sendMessage(ChatColor.RED + "The following message was blocked due to the server mute:");
            consoleCommandSender.sendMessage("[" + player.getName() + "] " + message);
        }
    }
}
