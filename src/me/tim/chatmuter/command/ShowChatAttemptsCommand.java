package me.tim.chatmuter.command;

import me.tim.chatmuter.util.MuteUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShowChatAttemptsCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (commandSender.hasPermission("chatmuter.cmd.showchatattempts"))
        {
            if (MuteUtil.playersViewingChatAttempts.contains(commandSender.getName()))
            {
                MuteUtil.playersViewingChatAttempts.remove(commandSender.getName());
                commandSender.sendMessage(ChatColor.GOLD + "You are no longer viewing messages sent during chat mutes.");
            }
            else
            {
                MuteUtil.playersViewingChatAttempts.add(commandSender.getName());
                commandSender.sendMessage(ChatColor.GOLD + "You are now viewing messages sent during chat mutes.");
            }
        }

        return true;
    }
}
