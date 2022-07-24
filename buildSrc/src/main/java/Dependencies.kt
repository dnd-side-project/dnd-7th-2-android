object Version {
    const val CORE_KTX = "1.8.0"
    const val APPCOMPAT = "1.4.2"
    const val MATERIAL = "1.6.1"
    const val CONSTRAINT_LAYOUT = "2.1.4"
    const val JUNIT = "4.13.2"
    const val TEST_JUNIT = "1.1.3"
    const val ESPRESSO = "3.4.0"
}

object Library {
    object AndroidX {
        const val CORE_KTX = "androidx.core:core-ktx:${Version.CORE_KTX}"
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"
        const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
        const val TEST_JUNIT = "androidx.test.ext:junit:${Version.TEST_JUNIT}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
    }

    object Junit {
        const val JUNIT = "junit:junit:${Version.JUNIT}"
    }
}