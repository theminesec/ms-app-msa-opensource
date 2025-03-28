// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    /**
     * Use `apply false` in the top-level build.gradle file to add a Gradle
     * plugin as a build dependency but not apply it to the current (root)
     * project. Don't use `apply false` in sub-projects. For more information,
     * see Applying external plugins with same version to subprojects.
     * https://docs.gradle.org/current/userguide/plugins.html#sec:subprojects_plugins_dsl
     */
    alias(libs.plugins.kotlin.android) apply false
    // alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.gms.googleServices) apply false
}

allprojects {
    configurations.all {
        resolutionStrategy {
            preferProjectModules()
            cacheChangingModulesFor(0, "seconds")
        }
    }
}

tasks.register<Delete>("clean").configure {
    delete(rootProject.buildDir)
}