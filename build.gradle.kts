plugins {
    `java-library`
    id("net.tetratau.tokimak.userdev") version "0.1.1-SNAPSHOT"
}

group = "io.papermc.paperweight"
version = "1.0.0-SNAPSHOT"
description = "Test plugin for paperweight-userdev"

java {
    // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    maven("https://maven.fabricmc.net/")
    maven("https://mvn.tetratau.net/releases/")
}

dependencies {
    paperweight.paperDevBundle("1.19.3-R0.1-SNAPSHOT")
    // paperweight.foliaDevBundle("1.19.4-R0.1-SNAPSHOT")
    // paperweight.devBundle("com.example.paperfork", "1.19.4-R0.1-SNAPSHOT")
    api("net.fabricmc:fabric-loader:0.14.14")
    api("net.fabricmc:sponge-mixin:0.11.4+mixin.0.8.5") {
        exclude(module = "launchwrapper")
        exclude(module = "guava")
    }
}

tasks {
    // Configure reobfJar to run when invoking the build task
    assemble {
        dependsOn(reobfJar)
    }

    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

        // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
        // See https://openjdk.java.net/jeps/247 for more information.
        options.release.set(17)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    }

    /*
    reobfJar {
      // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
      // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
      outputJar.set(layout.buildDirectory.file("libs/PaperweightTestPlugin-${project.version}.jar"))
    }
     */
}
