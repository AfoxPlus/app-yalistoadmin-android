pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://maven.pkg.github.com/afoxplus/app-yalisto-android")
            credentials {
                username = extra.has("REPO_USERID_AFOXPLUS").let {
                    if (it)
                        extra.get("REPO_USERID_AFOXPLUS") as String else System.getenv("REPO_USERID")
                }
                password = extra.has("REPO_TOKEN_AFOXPLUS").let {
                    if (it)
                        extra.get("REPO_TOKEN_AFOXPLUS") as String else System.getenv("REPO_TOKEN")
                }
            }
        }
    }
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.vanniktech:gradle-dependency-graph-generator-plugin:0.8.0")
    }
}

rootProject.name = "app-yalistoadmin-android"
include(":app")


/*
include("uikit")
project(":uikit").projectDir = File("${settings.rootProject.projectDir.parent}/app-android-uikit/module")
*/
