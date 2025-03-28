@file:Suppress("PropertyName")

import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * The first section in the build configuration applies the Android Gradle plugin
 * to this build and makes the android block available to specify
 * Android-specific build options.
 */
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.gms.googleServices)
    alias(libs.plugins.firebase.crashlytics)
}

val MS_KS_FILE: String by project
val MS_KS_STORE_TYPE: String by project
val MS_KS_STORE_PASS: String by project
val MS_KS_KEY_ALIAS: String by project
val MS_KS_KEY_PASS: String by project

/**
 * Locate (and possibly download) a JDK used to build your kotlin
 * source code. This also acts as a default for sourceCompatibility,
 * targetCompatibility and jvmTarget. Note that this does not affect which JDK
 * is used to run the Gradle build itself, and does not need to take into
 * account the JDK version required by Gradle plugins (such as the
 * Android Gradle Plugin)
 */
kotlin {
    jvmToolchain(8)
}

/**
 * The android block is where you configure all your Android-specific
 * build options.
 */
android {
    /**
     * The app's namespace. Used primarily to access app resources.
     */
    namespace = "com.minesec.msa"

    /**
     * compileSdk specifies the Android API level Gradle should use to
     * compile your app. This means your app can use the API features included in
     * this API level and lower.
     */
    compileSdk = 34

    /**
     * The defaultConfig block encapsulates default settings and entries for all
     * build variants and can override some attributes in main/AndroidManifest.xml
     * dynamically from the build system. You can configure product flavors to override
     * these values for different versions of your app.
     */
    defaultConfig {
        // Uniquely identifies the package for publishing.
        applicationId = "com.minesec.msa"

        // Defines the minimum API level required to run the app.
        minSdk = 26

        // Specifies the API level used to test the app.
        targetSdk = 34

        /**
         * Version pattern
         * A.BB.CCC.DDD(MMM)
         * For MPoC starting from 2.00.000.DDD(MMM)
         * Normally A.BB should rarely change and should be part of the wildcard versioning
         *
         * A -
         * Major version change: Complete architecture changes of the application
         * Change would trigger full certification.
         *
         * BB -
         * Minor version change: Changes that might impact scope of certified product.
         * Change of BB will require delta certification on the next renewal of the solution
         *
         * CCC -
         * Functions version change: Bug fixes, new SDK version, changes in sub-core components, UI updates…
         * Change of CCC will NOT require any re-certification.
         *
         * DDD - First 3 digits of the build number
         * MMM - First 3 digits of CustomerID
         */
        // DO NOT CHANGE, re-cert require
        val majorVersion = 2
        // DO NOT CHANGE, re-cert require
        val minorVersion = 0
        // msa component lib version
//        val patchVersion: Int = 75
        val patchVersion: Int = libs.versions.mineSecMsaLibs.get().takeLast(3).takeLastWhile { it.isDigit() }.toInt()
        // license update, build config changes
        val buildVersion = 1
        // MineSec
        val customerId = 0

        println("majorVersion: $majorVersion,\nminorVersion: $minorVersion,\npatchVersion: $patchVersion,\nbuildVersion: $buildVersion,\ncustomerId: $customerId")

        val verCode = 0
            .plus(majorVersion.times(1_00_000_000))
            .plus(minorVersion.times(1_000_000))
            .plus(patchVersion.times(1_000))
            .plus(buildVersion)

        println("verCode: $verCode")

        val verName = "$majorVersion" +
                ".${minorVersion.toString().padStart(2, '0')}" +
                ".${patchVersion.toString().padStart(3, '0')}" +
                ".${buildVersion.toString().padStart(3, '0')}" +
                "(${customerId.toString().padStart(3, '0')})"

        versionCode = verCode
        versionName = verName

        val formatter = DateTimeFormatter.ofPattern("yyMMdd_hhmm")
        val currentTime = LocalDateTime.now().format(formatter)
        archivesName = "$applicationId-$versionName-${currentTime}"
    }

    signingConfigs {
        register("signingConfig") {
            storeFile = file(MS_KS_FILE)
            storePassword = MS_KS_STORE_PASS
            storeType = MS_KS_STORE_TYPE
            keyAlias = MS_KS_KEY_ALIAS
            keyPassword = MS_KS_KEY_PASS
            enableV1Signing = true
            enableV2Signing = true
        }
    }

    /**
     * The buildTypes block is where you can configure multiple build types.
     * By default, the build system defines two build types: debug and release. The
     * debug build type is not explicitly shown in the default build configuration,
     * but it includes debugging tools and is signed with the debug key. The release
     * build type applies ProGuard settings and is not signed by default.
     */
    buildTypes {
        all {
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "../proguard-rules.pro")
            //TODO you need to put your license here.
            buildConfigField("String", "License", "\"MineSec-lic_01HFRNNQKN6FN8WA3FK5M18BKM-20240111_053100.license\"")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("signingConfig")
            buildConfigField("String", "tmsBaseUrl", "\"https://mtms.mspayhub.com/api\"")
            buildConfigField("String", "srsBaseUrl", "\"https://srs.mspayhub.com/api\"")
        }
        debug {
            // sometime to test out the proguard rule
            isMinifyEnabled = false
            versionNameSuffix = ".stage"
            applicationIdSuffix = ".stage"
            buildConfigField("String", "tmsBaseUrl", "\"https://uat-mtms.mspayhub.com/api\"")
            buildConfigField("String", "srsBaseUrl", "\"https://uat-srs.mspayhub.com/api\"")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    gradle.taskGraph.whenReady {
        tasks.forEach { task ->
            //fixme,I always got stuck when building release version app.
            if (task.name.contains("uploadCrashlyticsMappingFileRelease")) {
                task.enabled = false
            }
        }
    }

    packaging {
        resources {
            excludes.add("org/apache/commons/codec/language/bm/*")
            excludes.add("META-INF/spring.tooling")
            excludes.add("META-INF/versions/9")
            excludes.add("META-INF/spring.handlers")
            excludes.add("META-INF/spring.schemas")
            excludes.add("org/openqa/grid/images/android.png")
            excludes.add("META-INF/license.txt")
            excludes.add("META-INF/DEPENDENCIES")
            excludes.add("META-INF/notice.txt")
            excludes.add("META-INF/INDEX.LIST")
            excludes.add("META-INF/io.netty.versions.properties")

            excludes += "/META-INF/DEPENDENCIES"
            excludes += "/META-INF/LICENSE"
            excludes += "/META-INF/LICENSE.txt"
            excludes += "/META-INF/license.txt"
            excludes += "/META-INF/NOTICE"
            excludes += "/META-INF/NOTICE.txt"
            excludes += "/META-INF/notice.txt"
            excludes += "/META-INF/ASL2.0"
            excludes += "/META-INF/*.kotlin_module"
        }
    }

    configurations.all {
        exclude(module = "bcprov-jdk15on")
        exclude(module = "bcpkix-jdk15on")
    }
}

/**
 * The dependencies block in the module-level build configuration file
 * specifies dependencies required to build only the module itself.
 * To learn more, go to Add build dependencies.
 */
dependencies {
    // external aar or jar
    // implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))

    ////////// MineSec //////////
    implementation(libs.ms.app.msa.lib.model)
    implementation(libs.ms.app.msa.lib.ui)
    debugImplementation(libs.ms.sdk.minehades.stage)
    releaseImplementation(libs.ms.sdk.minehades)
    implementation(libs.visa.sensory)

    // android Minesec toolkit dependencies
    implementation(libs.minesec.toolkit) {
        exclude(group = "org.slf4j")
        exclude(group = "com.google.zxing")
        exclude(group = "com.google.android.material")
    }

    ////////// Android //////////
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.security.crypto)
    implementation(libs.androidx.security.identityCredential)
    implementation(libs.androidx.swiperefreshlayout)

    ////////// UI //////////
    implementation(libs.material)
    implementation(libs.flexbox)
    implementation(libs.zxing.androidembedded)
    implementation(libs.lottie)
    implementation(libs.signaturepad)

    ////////// Concurrency //////////
    implementation(libs.rxjava2.android)
    implementation(libs.rxjava2)

    ////////// Network & Serialization //////////
    implementation(platform(libs.okhttp.bom))
    implementation(libs.okhttp)
    implementation(libs.okhttp.loggingInterceptor)
    implementation(libs.okhttp.tls)
    implementation(libs.retrofit)
    implementation(libs.retrofit.rxjava2)
    implementation(libs.retrofit.gsonconverter)

    ////////// Annotations, compiler //////////
    implementation(libs.androidx.annotation)

    ////////// Service //////////
    // firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.config)
    // gms
//    implementation(libs.gms.safetynet)
    // sentry
    implementation(libs.sentry.android)
    // Language
    implementation(libs.lingver)
    // PayServer
    implementation(libs.payServer)
    implementation(libs.poslib)
    // Printer
    implementation(libs.ominidriver)
    implementation(libs.nexgodriver)
    // ULID
    implementation(libs.ulid)
}
