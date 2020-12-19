import org.gradle.api.JavaVersion

object Config {
    const val application_id = "ru.maxdexter.translatorcoincoroutine"
    const val compile_sdk = 30
    const val min_sdk = 23
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules{

}

object Versions{
    const val appcompat = "1.2.0"
    const val constraintLayout = "2.0.4"
    const val support_lib = "1.0.0"
    const val lifecycle = "2.2.0"
    const val recyclerview = "1.1.0"
    // Test
    const val jUnit = "4.12"
    const val runner = "1.2.0"
    const val espressoCore = "3.2.0"
    //Design
    const val material = "1.2.1"
    //Kotlin
    const val core_ktx = "1.3.2"
    const val stdlib_jdk7 = "1.4.20"
    const val kotlinx_coroutines = "1.3.7"

    //Retrofit 2
    const val retrofit = "2.9.0"
    const val retrofit_logging_interceptor = "4.9.0"
    const val retrofit_coroutines_adapter= "0.9.2"

    //Koin
    const val koin = "2.0.1"
    //Navigation Components
    const val navigation = "2.3.2"

    //Glide
    const val glide = "4.11.0"

    //Room
    const val room = "2.2.5"

    // Tools
    const val multidex = "1.0.3"

}

object Tools {
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
}

object Libs {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val support_lib = "androidx.legacy:legacy-support-v4:${Versions.support_lib}"
    const val lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
}
    //Test
    object TestImpl {
        const val junit = "junit:junit:${Versions.jUnit}"
        const val runner = "androidx.test:runner:${Versions.runner}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    }

    //Design
object Design {
    const val material = "com.google.android.material:material:${Versions.material}"
}
    //Kotlin
object Kotlin {
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.stdlib_jdk7}"
    const val kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines}"
    const val kotlinx_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinx_coroutines}"
}
    //Retrofit 2
object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.retrofit_logging_interceptor}"
    const val coroutines_adapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit_coroutines_adapter}"
}
    //Koin for Android
object Koin {
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_android_viewmodel = "org.koin:koin-android-viewmodel:${Versions.koin}"
}
    //Navigation Components
object Navigation {
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
}
    // Glide
object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_annotationProcessor = "com.github.bumptech.glide:compiler:${Versions.glide}"
}
    // Room
object Room {
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val room_compiler_kapt = "androidx.room:room-compiler:${Versions.room}"
}