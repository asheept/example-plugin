package io.github.asheept

import org.bukkit.plugin.java.JavaPlugin

class ExamplePlugin : JavaPlugin() {

    override fun onEnable() {

        println("hello")
        server.pluginManager.registerEvents(ExampleListener(this), this)
    }
}