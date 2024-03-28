plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.kitchensync"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.kitchensync"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    /*packagingOptions {
        resources.excludes.add("META-INF/DEPENDENCIES")
        resources.excludes.add("mozilla/public-suffix-list.txt")
    }*/
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.fleeksoft.ksoup:ksoup:0.1.2")
    implementation("com.fleeksoft.ksoup:ksoup-network:0.1.2")
    //implementation("com.android.support:cardview-v7:+")
    implementation("org.seleniumhq.selenium:selenium-java:4.18.1")
    implementation("org.jsoup:jsoup:1.17.2")
    implementation("com.squareup.picasso:picasso:2.8")
    /*implementation("it.skrape:skrapeit:1.2.2")
    implementation("it.skrape:skrapeit-http-fetcher:1.2.2")*/
}