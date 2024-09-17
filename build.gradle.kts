plugins {
    id("com.android.application") version "8.1.4"  apply false
    id("com.android.library") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.8.22"  apply false
    id("org.jetbrains.kotlin.kapt") version "1.8.22"  apply false
    id("org.jetbrains.kotlin.plugin.parcelize") version "1.7.20" apply false
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0" apply false
    id("com.google.firebase.crashlytics") version "3.0.2" apply false
}
/*
allprojects {
    configurations.all {
        resolutionStrategy {
            dependencySubstitution {
                substitute(module("com.afoxplus.android:uikit:6.2.1")).using(project(":uikit"))
            }
        }
    }
}*/
