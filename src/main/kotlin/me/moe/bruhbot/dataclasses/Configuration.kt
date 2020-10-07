package me.moe.bruhbot.dataclasses

import com.gitlab.kordlib.core.entity.Guild
import com.gitlab.kordlib.core.entity.Role
import me.jakejmattson.discordkt.api.dsl.Data

data class Configuration(
        val ownerId: String = "insert-owner-id",
        var prefix: String = "++",
        val guildConfigurations: MutableMap<Long, GuildConfiguration> = mutableMapOf(),
) : Data("config/config.json") {
    operator fun get(id: Long) = guildConfigurations[id]
    fun hasGuildConfig(guildId: Long) = guildConfigurations.containsKey(guildId)

    fun setup(guild: Guild, prefix: String, staffRole: Role) {
        if (guildConfigurations[guild.id.longValue] != null) return

        val newConfiguration = GuildConfiguration(
                prefix,
                staffRole.id.longValue
        )
        guildConfigurations[guild.id.longValue] = newConfiguration
        save()
    }
}

data class GuildConfiguration(
        var prefix: String,
        var staffRole: Long,
        var deleteBruh: Boolean = true
)
