package io.github.asheept

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class ExampleListener(private val plugin: ExamplePlugin): Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent)
    {
        event.player.sendMessage("hello")

        plugin.logger.warning("message")
    }
}