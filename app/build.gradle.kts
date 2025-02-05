import org.gradle.internal.impldep.com.fasterxml.jackson.core.JsonPointer.compile

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.pokedexmobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pokedexmobile"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Navigation
    implementation ("androidx.navigation:navigation-fragment:2.3.0")
    implementation ("androidx.navigation:navigation-ui:2.3.0")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    implementation ("com.google.android.material:material:1.3.0-alpha03")

    // Retrofit y su convertidor Gson
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

// AndroidX reemplaza las bibliotecas de soporte antiguas
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")

// Glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
}