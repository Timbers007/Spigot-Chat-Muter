Quick Guide for Chat Muter:

NOTE: Do not remove any fields from the config! If you wish to disable, for instance, a message from being sent, simply set the message to: ''

The following are also available for all messages:
%USERNAME% - Replaced with the username relevant to the message. (i.e. for chat-muted-message, %USERNAME% will be replaced with the username of the player who attempted to speak during a chat mute)
%DISPLAYNAME% - Replaced with the display name of the player who is relevant to the message. (i.e. for announcements, whoever executed the command will have their name inserted)
%CUSTOMNAME% - Replaced with the custom name of the player who is relevant to the message.

Permissions:
chatmuter.chatmuted.bypass - Allows the player to bypass chat mutes.
chatmuter.cmd.mutechat - Allows the player to toggle chat mutes. (/mutechat)
chatmuter.cmd.chatmuted - Allows the player to check if the chat is muted. (/chatmuted)
chatmuter.cmd.showchatattempts - Allows the player to toggle the ability to see attempts of chatting during a chat mute. (/showchatattempts)

Config Fields:

# Is the chat muted?
chat-muted: false

# This message will be sent to players who attempt to speak when the chat is muted.
chat-muted-message: '&6The chat is currently muted.'

# Sent to players who bypass the mute.
chat-mute-bypass-message: '&cYou have bypassed the mute.'

# Announced to everyone when the chat is muted, unless specified to be quiet.
chat-muted-announcement: '&6&lThe server chat has been temporarily muted by %USERNAME%.'

# Annouced to everyone when a chat mute is lifted, unless specified to be quiet.
chat-mute-lifted-announcement: '&6&lThe server chat is no longer muted.'

# Message sent if the chat is muted and /chatmuted is executed
chat-muted-positive: '&6The chat is currently muted.'

# Message sent if the chat is muted and /chatmuted is executed
chat-muted-negative: '&6The chat is not currently muted.'

# Commands that will be stopped from executing during a mute. Add '*' if you wish to disable all commands.
muted-commands:
  - 'msg'
  - 'w'

# Controls whether or not anonymous statistics about your server can be sent upon launch.
# Only the default stats are collected.
# You can read more at: https://bstats.org/
enable-statistics: true