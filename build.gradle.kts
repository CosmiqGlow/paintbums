plugins {
    java
}

group = "de.cosmiqglow"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        setUrl("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        content {
            includeGroup("org.bukkit")
            includeGroup("org.spigotmc")
        }
    }
    maven ( url = "https://oss.sonatype.org/content/repositories/snapshots" )
    maven (url = "https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.15.2-R0.1-SNAPSHOT")
    implementation("de.cosmiqglow:fluctu:1.0.0-SNAPSHOT")
    implementation("me.clip:placeholderapi:2.10.4")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}