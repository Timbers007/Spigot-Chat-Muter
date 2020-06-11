package me.tim.chatmuter.command;

import me.tim.chatmuter.util.MuteUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatMutedCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if (commandSender.hasPermission("chatmuter.cmd.chatmuted"))
            if (MuteUtil.isChatMuted)
                MuteUtil.displayChatMutedPositiveMessage(commandSender);
            else
                MuteUtil.displayChatMutedNegativeMessage(commandSender);

        return true;
    }
}
