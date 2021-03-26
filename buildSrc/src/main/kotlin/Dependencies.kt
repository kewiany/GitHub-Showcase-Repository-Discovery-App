object BuildPlugin {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val androidGradlePlugin = "com.android.tools.build:gradle:4.2.0-alpha16"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlinSerializationPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Library {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.60"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3"
    const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0"

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.3.2"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
    }

    object Navigation {
        const val fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Koin {
        const val ext = "org.koin:koin-androidx-ext:${Versions.koin}"
        const val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    }

    object Fuel {
        const val core = "com.github.kittinunf.fuel:fuel:${Versions.fuel}"
        const val coroutines = "com.github.kittinunf.fuel:fuel-coroutines:${Versions.fuel}"
        const val kotlinXSerialization = "com.github.kittinunf.fuel:fuel-kotlinx-serialization:${Versions.fuel}"
    }

    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0-beta01"
    const val material = "com.google.android.material:material:1.2.1"
}

object TestLibrary {

    object Kotest {
        const val framework = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}.RC2"
        const val assertions = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
        const val property = "io.kotest:kotest-property-jvm:${Versions.kotest}"
    }

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.3"
    const val junit = "junit:junit:4.12"
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito}"
    const val smokk = "com.github.langara:SMokK:0.0.3"
}