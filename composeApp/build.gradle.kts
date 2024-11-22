import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.sentry.plugin)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.sqldelight.android.driver)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.material3.desktop)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(libs.bundles.ktor.common)
            implementation(libs.sqldelight.coroutines.extensions)
            implementation(libs.napier)
            implementation(libs.precompose)
            implementation(libs.sentry.sdk)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.sqldelight.sqlite.driver)
        }
    }
}

android {
    namespace = "com.lgbtqspacey.admin"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.lgbtqspacey.admin"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0.0"
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

compose.desktop {
    application {
        mainClass = "com.lgbtqspacey.admin.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Exe)
            packageName = "com.lgbtqspacey.admin"
            packageVersion = "1.0.0"
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.lgbtqspacey.admin.commonMain.composeResources"
    generateResClass = auto
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.lgbtqspacey.database")
            generateAsync.set(true)

        }
    }
}

sentry {
    val env = project.rootProject.file("sentry.properties")
    val properties = Properties()
    properties.load(env.inputStream())

    includeSourceContext = true
    org = "lgbtqspacey"
    projectName = "admin-portal-client"
    authToken = properties.getProperty("sentry_auth_token")
}
