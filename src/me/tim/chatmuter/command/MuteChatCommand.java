package me.tim.chatmuter.command;

import me.tim.chatmuter.util.ConfigHelper;
import me.tim.chatmuter.util.MuteUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.IOException;

public class MuteChatCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (commandSender.hasPermission("chatmuter.cmd.mutechat"))
        {
            if (args.length == 0)
            {
                MuteUtil.isChatMuted = !MuteUtil.isChatMuted;

                try {
                    ConfigHelper.modify(ConfigHelper.CHAT_MUTED, MuteUtil.isChatMuted);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (MuteUtil.isChatMuted)
                {
                    MuteUtil.displayChatMutedAnnouncement(commandSender);
                }
                else
                {
                    MuteUtil.displayChatMuteLiftedAnnouncement(commandSender);
                }
            }
            else if (args.length == 1)
            {
                if (args[0].equalsIgnoreCase("quiet"))
                {
                    MuteUtil.isChatMuted = !MuteUtil.isChatMuted;

                    try {
                        ConfigHelper.modify(ConfigHelper.CHAT_MUTED, MuteUtil.isChatMuted);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    commandSender.sendMessage( ChatColor.RED + "Invalid Syntax! " + ChatColor.GOLD + "Did you mean /chatmute [quiet]?");
                }
            }
            else
            {
                commandSender.sendMessage( ChatColor.RED + "Invalid Syntax! " + ChatColor.GOLD + "Did you mean /chatmute [quiet]?");
            }
        }

        return true;
    }
}
