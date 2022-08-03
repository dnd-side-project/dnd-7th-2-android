plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Library.AndroidX.COROUTINE)
    implementation(Library.AndroidX.CORE_KTX)
    implementation(Library.AndroidX.APPCOMPAT)
    implementation(Library.AndroidX.MATERIAL)
    implementation(Library.AndroidX.CONSTRAINT_LAYOUT)
    implementation(Library.AndroidX.VIEWMODEL)
    implementation(Library.AndroidX.ANNOTATION)
    implementation(Library.AndroidX.NAVIGATION_FRAGMENT_KTX)
    implementation(Library.AndroidX.NAVIGATION_UI_KTX)
    implementation(Library.AndroidX.ACTIVITY_KTX)
    implementation(Library.AndroidX.FRAGMENT_KTX)
    androidTestImplementation(Library.AndroidX.TEST_JUNIT)
    androidTestImplementation(Library.AndroidX.ESPRESSO)

    testImplementation(Library.Junit.JUNIT)

    implementation(Library.Network.RETROFIT)
    implementation(Library.Network.MOSHI)
    implementation(Library.Network.MOSHI_KOTLIN)
    implementation(Library.Network.OKHTTP)
    implementation(Library.Network.LOGGING_INTERCEPTOR)

    implementation(Library.Hilt.HILT)
    kapt(Library.Hilt.HILT_COMPILER)

    implementation(Library.Glide.GLIDE)
    annotationProcessor(Library.Glide.GLIDE_COMPILER)
}