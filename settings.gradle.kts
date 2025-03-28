@file:Suppress("UnstableApiUsage")

pluginManagement {
    /**
     * The pluginManagement.repositories block configures the
     * repositories Gradle uses to search or download the Gradle plugins and
     * their transitive dependencies. Gradle pre-configures support for remote
     * repositories such as JCenter, Maven Central, and Ivy. You can also use
     * local repositories or define your own remote repositories. The code below
     * defines the Gradle Plugin Portal, Google's Maven repository,
     * and the Maven Central Repository as the repositories Gradle should use to look for its
     * dependencies.
     */
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
        maven { url = uri("https://jitpack.io") }
        // maven { url = uri("https://developer.huawei.com/repo/") }
    }
}

dependencyResolutionManagement {
    /**
     * The dependencyResolutionManagement.repositories
     * block is where you configure the repositories and dependencies used by
     * all modules in your project, such as libraries that you are using to
     * create your application. However, you should configure module-specific
     * dependencies in each module-level build.gradle file. For new projects,
     * Android Studio includes Google's Maven repository and the Maven Central
     * Repository by default, but it does not configure any dependencies (unless
     * you select a template that requires some).
     */
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
        mavenLocal()
        // maven { url = uri("https://developer.huawei.com/repo/") }
        maven { url = uri("https://jitpack.io") }
        val GITHUB_USERNAME: String? by settings
        val GITHUB_TOKEN: String? by settings
        requireNotNull(GITHUB_USERNAME) {
            """
                Please set your MineSec Github credential in `gradle.properties`.
                On local machine,
                ** DO NOT **
                ** DO NOT **
                ** DO NOT **
                Do not put it in the project's file. (and accidentally commit and push)
                ** DO **
                Do set it in your machine's global (~/.gradle/gradle.properties)
            """.trimIndent()
        }
        requireNotNull(GITHUB_TOKEN)
        println("github username: $GITHUB_USERNAME")
        maven {
            name = "gprInternal"
            url = uri("https://maven.pkg.github.com/theminesec/ms-registry-internal")
            credentials {
                username = GITHUB_USERNAME
                password = GITHUB_TOKEN
            }
        }
        maven {
            name = "gprClient"
            url = uri("https://maven.pkg.github.com/theminesec/ms-registry-client")
            credentials {
                username = GITHUB_USERNAME
                password = GITHUB_TOKEN
            }
        }
    }
}

rootProject.name = "MS App - MSA White Label"
include(":app")

