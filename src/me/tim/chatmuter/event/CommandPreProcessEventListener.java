package me.tim.chatmuter.event;

import me.tim.chatmuter.util.MuteUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPreProcessEventListener implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onCommandPreProcessEvent(PlayerCommandPreprocessEvent event)
    {
        if (MuteUtil.isChatMuted)
        {
            int space = event.getMessage().indexOf(" ");
            String command = event.getMessage().substring(1, space == -1 ? event.getMessage().length() : space);
            for (String cmd : MuteUtil.commandsMuted)
            {
                if ((cmd.equals("*")) || (cmd.length() >= command.length() && command.equalsIgnoreCase(cmd)))
                {
                    if (!event.getPlayer().hasPermission("chatmuter.chatmuted.bypass"))
                    {
                        MuteUtil.handleChatDeniedDuringMute(event.getPlayer(), event.getMessage());
                        event.setCancelled(true);
                    }
                    else
                    {
                        MuteUtil.displayBypassMessage(event.getPlayer());
                    }

                    break;
                }
            }
        }
    }
}
