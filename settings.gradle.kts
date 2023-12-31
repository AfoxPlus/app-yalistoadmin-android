pluginManagement {
    repositories {
        google()
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
rootProject.name = "app-yalistoadmin-android"
include(":app")
