package me.moe.bruhbot.commands

import me.jakejmattson.discordkt.api.arguments.BooleanArg
import me.moe.bruhbot.conversations.ConfigurationConversation
import me.moe.bruhbot.dataclasses.Configuration
import me.moe.bruhbot.services.PermissionLevel
import me.moe.bruhbot.services.requiredPermissionLevel
import me.jakejmattson.discordkt.api.arguments.ChannelArg
import me.jakejmattson.discordkt.api.arguments.EveryArg
import me.jakejmattson.discordkt.api.arguments.RoleArg
import me.jakejmattson.discordkt.api.dsl.commands
import me.jakejmattson.discordkt.api.services.ConversationService

fun guildConfigCommands(configuration: Configuration, conversationService: ConversationService) = commands("Configuration") {
    command("configure") {
        description = "Configure a guild to use this bot."
        requiredPermissionLevel = PermissionLevel.Staff
        execute {
            if (configuration.hasGuildConfig(guild!!.id.longValue))
                return@execute respond("Guild configuration exists. To modify it use the commands to set values.")

            conversationService.startPublicConversation<ConfigurationConversation>(author, channel.asChannel(), guild!!)
            respond("Guild setup")
        }
    }

    command("setprefix") {
        description = "Set the bot prefix."
        requiredPermissionLevel = PermissionLevel.Staff
        execute(EveryArg) {
            if (!configuration.hasGuildConfig(guild!!.id.longValue))
                return@execute respond("Please run the **configure** command to set this initially.")

            val prefix = args.first
            configuration[guild!!.id.longValue]?.prefix = prefix
            configuration.save()

            respond("Prefix set to: **$prefix**")
        }
    }

    command("setstaffrole") {
        description = "Set the bot staff role."
        requiredPermissionLevel = PermissionLevel.Staff
        execute(RoleArg) {
            if (!configuration.hasGuildConfig(guild!!.id.longValue))
                return@execute respond("Please run the **configure** command to set this initially.")

            val role = args.first
            configuration[guild!!.id.longValue]?.staffRole = role.id.longValue
            configuration.save()

            respond("Role set to: **${role.name}**")
        }
    }

    command("setdeletebruh") {
        description = "Whether to delete bruh or not"
        requiredPermissionLevel = PermissionLevel.Staff
        execute(BooleanArg) {
            if (!configuration.hasGuildConfig(guild!!.id.longValue))
                return@execute respond("Please run the **configure** command to set this initially.")

            val deleteBruh = args.first
            configuration[guild!!.id.longValue]?.deleteBruh = deleteBruh
            configuration.save()

            respond("**Will now ${if (deleteBruh) "delete" else "not delete"} messages containing bruh**")
        }
    }
}