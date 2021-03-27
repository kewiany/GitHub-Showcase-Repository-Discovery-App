plugins {
    id(BuildPlugin.androidApplication)
    id(BuildPlugin.kotlinAndroid)
    id(BuildPlugin.kotlinAndroidExtensions)
    id("kotlinx-serialization") version "1.3.60"
}

android {
    compileSdkVersion(AndroidSdkVersions.compile)
    defaultConfig {
        applicationId = "xyz.kewiany.showcase"
        minSdkVersion(AndroidSdkVersions.min)
        targetSdkVersion(AndroidSdkVersions.target)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            isDebuggable = true
        }
        create("qa") {
            applicationIdSuffix = ".qa"
            versionNameSuffix = "-qa"
            isMinifyEnabled = true
        }
        getByName("release") {
            isMinifyEnabled = true
        }
    }
    flavorDimensions("type")
    productFlavors {
        create("apiMocked") {
            setDimension("type")
            buildConfigField("String", "API_BASE_URL", "\"https://api.github.com\"")
        }
        create("apiProduction") {
            setDimension("type")
            buildConfigField("String", "API_BASE_URL", "\"https://api.github.com\"")
        }
    }
    variantFilter {
        if (buildType.name == "qa" && this.flavors.map { it.name }.contains("apiMocked")) {
            ignore = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        freeCompilerArgs = listOf(
            "-Xuse-experimental=kotlin.ExperimentalUnsignedTypes",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

dependencies {
    implementation(Library.kotlinStdLib)
    implementation(Library.AndroidX.core)
    implementation(Library.AndroidX.appCompat)
    implementation(Library.material)
    implementation(Library.Navigation.fragment)
    implementation(Library.Navigation.ui)
    implementation(Library.Koin.ext)
    implementation(Library.Koin.viewmodel)
    implementation(Library.coroutinesCore)
    implementation(Library.constraintLayout)
    implementation(Library.swipeRefreshLayout)
    implementation(Library.serializationJson)
    implementation(Library.Fuel.core)
    implementation(Library.Fuel.coroutines)
    implementation(Library.Fuel.kotlinXSerialization)
    testImplementation(TestLibrary.Kotest.framework)
    testImplementation(TestLibrary.Kotest.assertions)
    testImplementation(TestLibrary.Kotest.property)
    testImplementation(TestLibrary.coroutines)
    testImplementation(TestLibrary.junit)
    testImplementation(TestLibrary.mockito)
    testImplementation(TestLibrary.smokk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> { kotlinOptions.jvmTarget = "1.8" }