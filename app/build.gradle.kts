plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.tokappq2"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.tokappq2"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}

tasks.withType<Test> {
    filter {
        isFailOnNoMatchingTests = false
    }
    doFirst {
        val patterns = ArrayList(filter.includePatterns)
        patterns.forEach { pattern ->
            val lower = pattern.lowercase()
            if (lower != pattern) {
                filter.includePatterns.add(lower)
            }
        }
    }
}