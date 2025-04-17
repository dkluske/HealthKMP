import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.binaryCompatibilityValidator)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    `maven-publish`
}

kotlin {
    jvm()
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.datetime)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
        }

        val androidMain by getting {
            dependencies {
                implementation("androidx.startup:startup-runtime:1.2.0")

                // Google Fit
                implementation("com.google.android.gms:play-services-fitness:21.2.0")
                implementation("com.google.android.gms:play-services-auth:21.3.0")

                // Health Connect
                implementation("androidx.health.connect:connect-client:1.1.0-alpha11")
            }
        }
    }
}

android {
    namespace = "com.viktormykhailiv.kmp.health"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

task("printVersionName") {
    println(providers.gradleProperty("VERSION_NAME").get())
}

apiValidation {
    @OptIn(kotlinx.validation.ExperimentalBCVApi::class)
    klib {
        enabled = true
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "io.dkluske.health"
            artifactId = "healthKMP"
            version = providers.gradleProperty("VERSION_NAME").get()

            from(components["kotlin"])
            pom {
                name = "HealthKMP"
                description = "A kotlin multiplatform library to connect to Apple HealthKit and Android Health"
                developers {
                    developer {
                        id = "vitoksmile"
                        name = "Viktor Mykhailiv"
                    }
                    developer {
                        id = "dKluske"
                        name = "Dominik Kluske"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/dkluske/HealthKMP.git"
                    developerConnection = "scm:git:git://github.com/dkluske/HealthKMP.git"
                }
            }
        }
    }
}