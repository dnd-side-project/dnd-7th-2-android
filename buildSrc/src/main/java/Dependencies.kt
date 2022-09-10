object Version {
    const val COROUTINE = "1.6.1"
    const val CORE_KTX = "1.8.0"
    const val APPCOMPAT = "1.5.0"
    const val MATERIAL = "1.6.1"
    const val CONSTRAINT_LAYOUT = "2.1.4"
    const val VIEWMODEL = "2.6.0-alpha01"
    const val ANNOTATION = "1.4.0"
    const val NAVIGATION_KTX = "2.5.0"
    const val ACTIVITY_KTX = "1.5.0"
    const val FRAGMENT_KTX = "1.5.0"
    const val TEST_JUNIT = "1.1.3"
    const val ESPRESSO = "3.4.0"

    const val JUNIT = "4.13.2"

    const val RETROFIT = "2.9.0"
    const val MOSHI = "1.9.3"
    const val OKHTTP = "5.0.0-alpha.7"

    const val HILT = "2.42"

    const val GLIDE = "4.13.2"
    const val GLIDE_COMPILER = "4.12.0"

    const val MATERIAL_CALENDAR = "2.0.1"
    const val THREE_TEN_ABP = "1.1.1"
}

object Library {
    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT}"
        const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"
        const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.VIEWMODEL}"
        const val ANNOTATION = "androidx.annotation:annotation:${Version.ANNOTATION}"
        const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${Version.NAVIGATION_KTX}"
        const val NAVIGATION_UI_KTX = "androidx.navigation:navigation-ui-ktx:${Version.NAVIGATION_KTX}"
        const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Version.ACTIVITY_KTX}"
        const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Version.FRAGMENT_KTX}"
        const val TEST_JUNIT = "androidx.test.ext:junit:${Version.TEST_JUNIT}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
    }

    object Junit {
        const val JUNIT4 = "junit:junit:${Version.JUNIT}"
        const val JUNIT5 = "org.junit.jupiter:junit-jupiter:5.7.2"
        const val junitVintageEngine = "org.junit.vintage:junit-vintage-engine:5.7.2"
        const val TRUTH = "com.google.truth:truth:1.1.2"
        const val MOCKK = "io.mockk:mockk:1.12.0"
    }

    object Network {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Version.RETROFIT}"
        const val MOSHI = "com.squareup.retrofit2:converter-moshi:${Version.RETROFIT}"
        const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Version.MOSHI}"
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${Version.OKHTTP}"
        const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP}"
        const val MOCKWEBSERVER = "com.squareup.okhttp3:mockwebserver:${Version.OKHTTP}"
    }

    object Hilt {
        const val HILT = "com.google.dagger:hilt-android:${Version.HILT}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILT}"
        const val CORE = "com.google.dagger:hilt-core:${Version.HILT}"
        const val COMPILER = "com.google.dagger:hilt-compiler:${Version.HILT}"
    }

    object Coroutine {
        const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINE}"
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINE}"
        const val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2"
    }

    object Glide {
        const val GLIDE = "com.github.bumptech.glide:glide:${Version.GLIDE}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${Version.GLIDE_COMPILER}"
    }

    object Calendar {
        const val MATERIAL_CALENDAR = "com.github.prolificinteractive:material-calendarview:${Version.MATERIAL_CALENDAR}"
        const val THREE_TEN_ABP = "com.jakewharton.threetenabp:threetenabp:${Version.THREE_TEN_ABP}"
    }
}