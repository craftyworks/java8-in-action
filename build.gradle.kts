plugins {
    java
    kotlin("jvm") version "1.3.61"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.apache.commons", "commons-lang3", "3.9")
    testImplementation("junit:junit:4.12")
    testImplementation("org.assertj", "assertj-core", "3.15.0")
}

sourceSets["main"].java.srcDir("src/main/java")

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}
