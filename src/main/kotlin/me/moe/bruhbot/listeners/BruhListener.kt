package me.moe.bruhbot.listeners

import com.gitlab.kordlib.core.event.message.MessageCreateEvent
import me.jakejmattson.discordkt.api.dsl.listeners
import me.moe.bruhbot.dataclasses.Configuration

fun onMessageSent(configuration: Configuration) = listeners {
    on<MessageCreateEvent> {

        val guild = message.getGuildOrNull() ?: return@on
        val guildConfig = configuration[guild.id.longValue] ?: return@on

        if (message.author?.isBot == true)
            return@on

        if (message.content.startsWith(guildConfig.prefix))
            return@on

        if (message.embeds.isNotEmpty())
            return@on

        if (!guildConfig.deleteBruh)
            return@on

        if (message.content.toLowerCase().replace("\\s".toRegex(), "").contains("bruh"))
            message.delete()
    }
}