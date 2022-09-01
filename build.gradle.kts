plugins {
    kotlin("jvm") version Dependency.Kotlin.Version
    id("io.papermc.paperweight.userdev") version "1.3.7"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    paperDevBundle("1.19-R0.1-SNAPSHOT")
    implementation("io.github.monun:kommand-api:2.12.0")
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
        val plugins = File("C:\\Users\\asheept\\Desktop\\Server\\1.19.2 example\\plugins")
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
