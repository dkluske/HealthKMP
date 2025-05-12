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
    androidTarget {
        publishLibraryVariants("release")
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm()

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

apiValidation {
    @OptIn(kotlinx.validation.ExperimentalBCVApi::class)
    klib {
        enabled = true
    }
}

publishing {
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/dkluske/HealthKMP")
            credentials {
                username = project.properties["gpr.user"] as? String ?: System.getenv("GITHUB_ACTOR")
                password = project.properties["gpr.key"] as? String ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "io.dkluske.health"
            artifactId = "healthkmp"
            version = project.version.toString()

            from(components["kotlin"])
            pom {
                name = "HealthKMP"
                description = "A kotlin multiplatform library to connect to Apple HealthKit and Android Health\nOriginal work from vitoksmile/HealthKMP"
                developers {
                    developer {
                        id = "vitoksmile"
                        name = "Viktor Mykhailiv"
                        description = "Original work"
                    }
                    developer {
                        id = "dKluske"
                        name = "Dominik Kluske"
                        description = "Developer of this fork"
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
