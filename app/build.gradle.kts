plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") version "2.0.21-1.0.25"
}

android {
    namespace = "com.gw.myearnings"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.gw.myearnings"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)



    // ==========================
//  UI Components & Material Design
// ==========================
    implementation("com.google.android.material:material:1.12.0")

// ==========================
// Dependency Injection (Koin)
// ==========================
// Core Koin dependency (required)
    implementation("io.insert-koin:koin-android:3.5.0")
// Koin integration with Jetpack Compose
    implementation("io.insert-koin:koin-androidx-compose:3.5.0")
// Koin integration with Navigation in Compose
    implementation("io.insert-koin:koin-androidx-navigation:3.5.0")

// ==========================
//  Lifecycle & ViewModel
// ==========================
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

// ==========================
//  Room Database (Local Storage)
// ==========================
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
// Coroutine support for Room (for suspend functions)
    implementation("androidx.room:room-ktx:2.6.1")

// ==========================
//Animations & Navigation
// ==========================
// Navigation animation for Compose
    implementation("com.google.accompanist:accompanist-navigation-animation:0.31.2-alpha")
// Control system UI colors (status/navigation bar)
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.2-alpha")

// ==========================
// Paging & Pager (Horizontal Scrolls / Carousels)
// ==========================
// Core foundation (required for pager)
    implementation("androidx.compose.foundation:foundation:1.7.8")
// Horizontal pager support
    implementation("com.google.accompanist:accompanist-pager:0.25.0")
// Pager indicators (e.g., dots below pager)
    implementation("com.google.accompanist:accompanist-pager-indicators:0.25.0")

// ==========================
// Font Awesome Icons
// ==========================
    implementation("br.com.devsrsouza.compose.icons:font-awesome:1.1.1")

// ==========================
// Data Persistence & Coroutines
// ==========================
// DataStore for key-value preferences (modern SharedPreferences)
    implementation("androidx.datastore:datastore-preferences:1.0.0")
// Coroutine support for background threading
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


}