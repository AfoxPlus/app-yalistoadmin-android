object Versions {
    //Kotlin
    const val kotlinVersion                     = "1.9.10"
    //SDK
    const val compileSdkVersion                 = 34
    const val buildToolsVersion                 = "31.0.0"
    const val minSdkVersion                     = 23
    const val targetSdkVersion                  = 34

    const val testInstrumentationRunner         = "androidx.test.runner.AndroidJUnitRunner"

    //Android
    const val coreVersion                       = "1.12.0"
    const val appcompatVersion                  = "1.6.1"
    const val activityVersion                   = "1.6.1"
    const val fragmentVersion                   = "1.5.5"

    //Compose
    const val activityComposeVersion            = "1.8.0"
    const val constraintlayoutComposeVersion    = "1.0.1"
    const val navigationComposeVersion          = "2.7.4"
    const val bomCompose                        = "2023.06.01"

    //Architecture
    const val hiltVersion                       = "2.46"
    const val coroutinesVersion                 = "1.7.0"
    const val retrofit2Version                  = "2.9.0"
    const val okhttp3Version                    = "4.11.0"
    const val okHttpJsonMockVersion             = "1.1.1"
    const val roomVersion                       = "2.5.1"
    const val lifecycleVersion                  = "2.6.1"
    const val chuckerVersion                    = "3.5.2"

    //Business Modules
    const val uikitVersion                      = "6.0.2"
    const val networkVersion                    = "6.0.1"
    const val productsVersion                   = "6.0.1"
    const val homeVersion                       = "6.0.1"
    const val restaurantsVersion                = "6.0.1"
    const val ordersVersion                     = "6.0.1"
    const val demo_config                     	= "6.0.1"

    //Views
    const val materialVersion                   = "1.9.0"
    const val recyclerviewVersion               = "1.2.1"
    const val constraintLayoutVersion           = "2.1.4"
    const val glideVersion                      = "4.15.1"
    const val fbShimmerVersion                  = "0.5.0"

    //Test
    const val jUnitVersion                      = "4.13.2"
    const val androidJUnitVersion               = "1.1.3"
    const val espressoVersion                   = "3.4.0"
    const val testCoreVersion                   = "2.1.0"
    const val truthVersion                      = "1.1.3"
    const val mockitoVersion                    = "2.28.2"
    const val mockitoKotlinVersion              = "4.0.0"
    const val mockitoInlineVersion              = "4.3.1"
    const val kotlinCoroutineVersion            = "1.6.0"

    //Scan
    const val zxingAndroidVersion               = "4.3.0"
    const val zxingCoreVersion                  = "3.3.0"

}

object Deps {

    object Jetpack {
        const val kotlin                        = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
        const val core                          = "androidx.core:core-ktx:${Versions.coreVersion}"
        const val appcompat                     = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
        const val activity                      = "androidx.activity:activity-ktx:${Versions.activityVersion}"
        const val fragment                      = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"
    }

    object JetpackCompose {
        const val activity                      = "androidx.activity:activity-compose:${Versions.activityComposeVersion}"
        const val constraintlayout              = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintlayoutComposeVersion}"
        const val navigation                    = "androidx.navigation:navigation-compose:${Versions.navigationComposeVersion}"
        const val bom                           = "androidx.compose:compose-bom:${Versions.bomCompose}"
        const val ui                            = "androidx.compose.ui:ui"
        const val graphics                      = "androidx.compose.ui:ui-graphics"
        const val toolingPreview                = "androidx.compose.ui:ui-tooling-preview"
        const val material3                     = "androidx.compose.material3:material3"
        const val materialIconExtended          = "androidx.compose.material:material-icons-extended"
    }


    object Arch {
        const val coroutinesCore                = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
        const val coroutinesAndroid             = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
        const val coreHiltAndroid               = "com.google.dagger:hilt-core:${Versions.hiltVersion}"
        const val hiltAndroid                   = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
        const val hiltCompiler                  = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"
        const val retrofit2                     = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
        const val gson                          = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2Version}"
        const val loggingInterceptor            = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Version}"
        const val okHttpJsonMock                = "com.github.mirrajabi:okhttp-json-mock:${Versions.okHttpJsonMockVersion}"
        const val room                          = "androidx.room:room-ktx:${Versions.roomVersion}"
        const val roomCompiler                  = "androidx.room:room-compiler:${Versions.roomVersion}"
        const val lifecycleViewModel            = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
        const val lifecycleLiveData             = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
        const val lifecycleRuntime              = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleVersion}"
        const val chucker                       = "com.github.chuckerteam.chucker:library:${Versions.chuckerVersion}"
        const val chuckerNoOp                   = "com.github.chuckerteam.chucker:library-no-op:${Versions.chuckerVersion}"
        const val zxingAndroid                  = "com.journeyapps:zxing-android-embedded:${Versions.zxingAndroidVersion}"
        const val zxingCore                     = "com.google.zxing:core:${Versions.zxingCoreVersion}"

        //Business Modules
        const val products               	    = "com.afoxplus.android:products:${Versions.productsVersion}"
        const val network               	    = "com.afoxplus.android:network:${Versions.networkVersion}"
        const val home                         	= "com.afoxplus.android:home:${Versions.homeVersion}"
        const val restaurants                   = "com.afoxplus.android:restaurants:${Versions.restaurantsVersion}"
        const val orders                        = "com.afoxplus.android:orders:${Versions.ordersVersion}"
        const val demo_config                   = "com.afoxplus.android:demo_config:${Versions.demo_config}"
    }

    object UI {
        const val uikit                         = "com.afoxplus.android:uikit:${Versions.uikitVersion}"
        const val materialDesign                = "com.google.android.material:material:${Versions.materialVersion}"
        const val constraintLayout              = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
        const val recyclerview                  = "androidx.recyclerview:recyclerview:${Versions.recyclerviewVersion}"
        const val glide                         = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
        const val glideCompiler                 = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
        const val fbShimmer                     = "com.facebook.shimmer:shimmer:${Versions.fbShimmerVersion}"
    }

    object Test {
        const val jUnit                         = "junit:junit:${Versions.jUnitVersion}"
        const val androidJUnit                  = "androidx.test.ext:junit:${Versions.androidJUnitVersion}"
        const val espresso                      = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
        const val testCore                      = "androidx.arch.core:core-testing:${Versions.testCoreVersion}"
        const val truth                         = "com.google.truth:truth:${Versions.truthVersion}"
        const val mockito                       = "org.mockito:mockito-core:${Versions.mockitoVersion}"
        const val mockitoKotlin                 = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlinVersion}"
        const val mockitoInline                 = "org.mockito:mockito-inline:${Versions.mockitoInlineVersion}"
        const val kotlinCoroutine               = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutineVersion}"
    }
}
