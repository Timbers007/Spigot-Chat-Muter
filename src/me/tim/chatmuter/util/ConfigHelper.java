package me.tim.chatmuter.util;

import me.tim.chatmuter.ServerChatMuter;

import java.io.IOException;

public class ConfigHelper
{
    public static final String CHAT_MUTED = "chat-muted";

    public static void modify(String path, Object value) throws IOException
    {
        ServerChatMuter.getInstance().grabConfig().set(path, value);
        ServerChatMuter.getInstance().grabConfig().save(ServerChatMuter.getInstance().getConfigFile());
    }

    public static void updateMessages()
    {
        MuteUtil.chatMutedMessage = ServerChatMuter.getInstance().grabConfig().getString("chat-muted-message").replace('&', '§');
        MuteUtil.chatMutedAnnouncement = ServerChatMuter.getInstance().grabConfig().getString("chat-muted-announcement").replace('&', '§');
        MuteUtil.chatMuteLiftedAnnouncement = ServerChatMuter.getInstance().grabConfig().getString("chat-mute-lifted-announcement").replace('&', '§');
        MuteUtil.bypassedChatMuteMessage = ServerChatMuter.getInstance().grabConfig().getString("chat-mute-bypass-message").replace('&', '§');
        MuteUtil.commandsMuted = ServerChatMuter.getInstance().grabConfig().getStringList("muted-commands");
        MuteUtil.checkChatMutedPositiveMessage = ServerChatMuter.getInstance().grabConfig().getString("chat-muted-positive").replace('&', '§');
        MuteUtil.checkChatMutedNegativeMessage = ServerChatMuter.getInstance().grabConfig().getString("chat-muted-negative").replace('&', '§');
    }
}
