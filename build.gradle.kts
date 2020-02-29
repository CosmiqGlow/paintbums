plugins {
    java
}

group = "de.cosmiqglow"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        setUrl("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        content {
            includeGroup("org.bukkit")
            includeGroup("org.spigotmc")
        }
    }
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.15.2-R0.1-SNAPSHOT")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}