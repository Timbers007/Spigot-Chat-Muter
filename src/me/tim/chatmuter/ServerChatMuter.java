package me.tim.chatmuter;

import me.tim.chatmuter.command.ChatMutedCommand;
import me.tim.chatmuter.command.MuteChatCommand;
import me.tim.chatmuter.command.ShowChatAttemptsCommand;
import me.tim.chatmuter.event.AsyncPlayerChatEventListener;
import me.tim.chatmuter.event.CommandPreProcessEventListener;
import me.tim.chatmuter.util.Metrics;
import me.tim.chatmuter.util.ConfigHelper;
import me.tim.chatmuter.util.MuteUtil;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ServerChatMuter extends JavaPlugin
{
    private static ServerChatMuter instance;

    private File configFile;
    private FileConfiguration config;

    public void onEnable()
    {
        ServerChatMuter.instance = this;

        setupConfig();
        initializeFromConfig();

        this.getServer().getPluginManager().registerEvents(new AsyncPlayerChatEventListener(), this);
        this.getServer().getPluginManager().registerEvents(new CommandPreProcessEventListener(), this);

        this.getCommand("mutechat").setExecutor(new MuteChatCommand());
        this.getCommand("chatmuted").setExecutor(new ChatMutedCommand());
        this.getCommand("showchatattempts").setExecutor(new ShowChatAttemptsCommand());

        if (MuteUtil.sendStats)
            sendStatistics();
    }

    public void setupConfig()
    {
        boolean existed = true;

        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists())
        {
            existed = false;
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
            saveResource("README.txt", false);
        }

        config = new YamlConfiguration();

        try
        {
            config.load(configFile);
        }
        catch (IOException | InvalidConfigurationException e)
        {
            e.printStackTrace();
        }
    }

    public void sendStatistics()
    {
        int pluginId = 7819;
        Metrics metrics = new Metrics(this, pluginId);
    }

    public void initializeFromConfig()
    {
        ConfigHelper.updateMessages();

        MuteUtil.isChatMuted = grabConfig().getBoolean("chat-muted");
        MuteUtil.sendStats = grabConfig().getBoolean("enable-statistics");
    }

    public File getConfigFile()
    {
        return this.configFile;
    }

    /**
     * This one must be used to grab the custom config file.
     * Do not use the built-in getConfig()!
     */
    public FileConfiguration grabConfig()
    {
        return this.config;
    }

    public static ServerChatMuter getInstance()
    {
        return ServerChatMuter.instance;
    }
}
