buildscript {
    repositories {
        google()
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(BuildPlugin.androidGradlePlugin)
        classpath(BuildPlugin.kotlinGradlePlugin)
        classpath(BuildPlugin.kotlinSerializationPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean").configure {
    delete("build")
}