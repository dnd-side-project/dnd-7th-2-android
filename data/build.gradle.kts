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

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":domain"))

    implementation(Library.AndroidX.PAGING3_COMMON)

    implementation(Library.Network.RETROFIT)
    implementation(Library.Network.MOSHI)
    implementation(Library.Network.MOSHI_KOTLIN)
    implementation(Library.Network.LOGGING_INTERCEPTOR)
    implementation(Library.Network.OKHTTP)

    implementation(Library.Hilt.CORE)
    kapt(Library.Hilt.COMPILER)

    implementation(Library.Coroutine.CORE)
    implementation(Library.Coroutine.TEST)

    testImplementation(Library.Junit.JUNIT5)
    testImplementation(Library.Junit.TRUTH)
    testImplementation(Library.Junit.MOCKK)
    testImplementation(Library.Network.MOCKWEBSERVER)
}