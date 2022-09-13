plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlin")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(Library.AndroidX.PAGING3_COMMON)

    implementation(Library.Coroutine.CORE)
    implementation(Library.Coroutine.TEST)

    implementation(Library.Hilt.CORE)
    kapt(Library.Hilt.COMPILER)
}