plugins {
    kotlin("jvm") version Dependency.Kotlin.Version
    id("io.papermc.paperweight.userdev") version "1.5.5"
}

group = "io.github.asheept"
version = "1.0-SNAPSHOT"
repositories {
    mavenCentral()
    maven("https://repo.dmulloy2.net/repository/public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("org.slf4j:slf4j-api:2.0.0")
    paperweight.paperDevBundle("1.20.1-R0.1-SNAPSHOT")
    implementation("net.kyori:adventure-api:4.14.0")
}

val pluginName = rootProject.name.split('-').joinToString("") { it.capitalize() }

extra.apply {
    set("pluginName", pluginName)
    set("packageName", rootProject.name.replace("-", ""))
    set("kotlinVersion", Dependency.Kotlin.Version)
}

tasks {
    // generate plugin.yml
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
            expand(extra.properties)
        }
    }

    register<Copy>("paperJar") {
        from(reobfJar)

        val jarName = "$pluginName.jar"
        rename { jarName }
        //val plugins = File("C:\\Users\\asheept\\Desktop\\Server\\1.19.2 lore\\plugins")
        val plugins = File("C:\\Users\\asheep\\Desktop\\server\\1.20.1 paper\\plugins")
        val plugin = File(plugins, "$pluginName.jar")
        val update = File(plugins, "update")

        if (plugin.exists())
            into(File(plugins, "update"))
        else
            into(plugins)

        doLast {
            update.mkdirs()
            File(update, "RELOAD").delete()
        }
    }
}
