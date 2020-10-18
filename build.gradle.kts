

plugins {
    kotlin("js")
    id("org.jlleitschuh.gradle.ktlint")
}
group = "eu.yeger"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlinx")
    maven(url = "https://dl.bintray.com/kotlin/kotlin-js-wrappers")
    maven(url = "https://dl.bintray.com/deryeger/maven")
}

dependencies {
    val kotlinxHtmlJsVersion: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-html-js:$kotlinxHtmlJsVersion")

    val kotlinReactVersion: String by project
    implementation("org.jetbrains:kotlin-react:$kotlinReactVersion")
    implementation("org.jetbrains:kotlin-react-dom:$kotlinReactVersion")

    val kotlinStyledVersion: String by project
    implementation("org.jetbrains:kotlin-styled:$kotlinStyledVersion")

    val cykAlgorithmVersion: String by project
    implementation("eu.yeger:cyk-algorithm:$cykAlgorithmVersion")

    testImplementation(kotlin("test-js"))
}

kotlin {
    js {
        browser {
            binaries.executable()
            webpackTask {
                cssSupport.enabled = true
            }
            runTask {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
}

tasks {
    getByName("compileKotlinJs").dependsOn(ktlintFormat)
}
