plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.chatapp.convox"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.chatapp.convox"
        minSdk = 23
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    // AndroidX and other dependencies
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // Firebase BOM (manages all Firebase library versions)
    implementation(platform("com.google.firebase:firebase-bom:33.7.0"))

    // Firebase Core Services
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
    implementation(libs.firebase.storage)

    // Other dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.intuit.sdp:sdp-android:1.1.1")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation ("com.google.firebase:firebase-storage:21.0.0")

}

// Apply the Firebase plugin
apply(plugin = "com.google.gms.google-services")
