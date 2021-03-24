plugins {
    id(BuildPlugin.androidApplication)
    id(BuildPlugin.kotlinAndroid)
    id(BuildPlugin.kotlinAndroidExtensions)
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
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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
    testImplementation(TestLibrary.Kotest.framework)
    testImplementation(TestLibrary.Kotest.assertions)
    testImplementation(TestLibrary.Kotest.property)
    testImplementation(TestLibrary.coroutines)
    testImplementation(TestLibrary.junit)
    testImplementation(TestLibrary.mockito)
}

tasks.withType<Test> {
    useJUnitPlatform()
}