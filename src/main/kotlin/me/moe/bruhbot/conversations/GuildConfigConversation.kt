package me.moe.bruhbot.conversations

import com.gitlab.kordlib.core.entity.Guild
import me.moe.bruhbot.dataclasses.Configuration
import me.jakejmattson.discordkt.api.arguments.EveryArg
import me.jakejmattson.discordkt.api.arguments.RoleArg
import me.jakejmattson.discordkt.api.dsl.Conversation
import me.jakejmattson.discordkt.api.dsl.conversation

class ConfigurationConversation(private val configuration: Configuration): Conversation() {
    @Conversation.Start
    fun createConfigurationConversation(guild: Guild) = conversation {
        val prefix = promptMessage(EveryArg, "Bot prefix:")
        val staffRole = promptMessage(RoleArg, "Staff role:")

        configuration.setup(guild, prefix, staffRole)
    }
}