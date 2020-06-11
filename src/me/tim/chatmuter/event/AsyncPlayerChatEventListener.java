package me.tim.chatmuter.event;

import me.tim.chatmuter.util.MuteUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatEventListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChatEvent(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        if (MuteUtil.isChatMuted)
        {
            if (!player.hasPermission("chatmuter.chatmuted.bypass"))
            {
                MuteUtil.displayChatMutedMessage(player);
                for (Player p : Bukkit.getServer().getOnlinePlayers())
                {
                    if (MuteUtil.playersViewingChatAttempts.contains(p.getName()))
                    {
                        p.sendMessage(ChatColor.RED + "The following message was blocked due to the server mute:");
                        p.sendMessage("[" + event.getPlayer().getName() + "] " + event.getMessage());
                    }
                }

                ConsoleCommandSender consoleCommandSender = Bukkit.getServer().getConsoleSender();
                if (MuteUtil.playersViewingChatAttempts.contains(consoleCommandSender.getName()))
                {
                    consoleCommandSender.sendMessage(ChatColor.RED + "The following message was blocked due to the server mute:");
                    consoleCommandSender.sendMessage("[" + event.getPlayer().getName() + "] " + event.getMessage());
                }

                event.setCancelled(true);
            }
            else
            {
                MuteUtil.displayBypassMessage(player);
            }
        }
    }
}
