package com.addontemplate.common

import com.addontemplate.common.api.TemplateManager
import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.events.CobblemonEvents
import com.cobblemon.mod.common.platform.events.PlatformEvents.SERVER_STARTING
import com.cobblemon.mod.common.platform.events.PlatformEvents.SERVER_STOPPED
import dev.architectury.event.events.common.PlayerEvent
import net.minecraft.server.MinecraftServer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object AddonTemplate
{
    const val MOD_ID: String = "addontemplate"
    val LOGGER: Logger = LoggerFactory.getLogger("addon-template")
    private var implementation: TemplateModImplementation? = null
    /** Don't call prior to server initialization. Has server ref cached after server starts. */
    @Suppress("MemberVisibilityCanBePrivate")
    internal var server: MinecraftServer? = null

    @JvmStatic
    fun initialize(
        implementation: TemplateModImplementation )
    {
        this.implementation = implementation
        implementation.registerEvents()
        implementation.initializeConfig()
        implementation.registerCommands()
    }

    @JvmStatic
    fun registerEvents()
    {
        SERVER_STARTING.subscribe( Priority.HIGHEST ) {
            event -> server = event.server
        }

        SERVER_STOPPED.subscribe( Priority.HIGHEST ) {
            //event -> { }
        }

        CobblemonEvents.BATTLE_VICTORY.subscribe( Priority.NORMAL ) {
            //event -> { }
        }

        PlayerEvent.PLAYER_QUIT.register( TemplateManager::handlePlayerLogoutEvent )
    }

}
