plugins {
    application
    id("io.freefair.lombok") version "8.6"
    checkstyle
    jacoco
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    implementation("info.picocli:picocli:4.7.5")
    implementation("org.jacoco:org.jacoco.core:0.8.11")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
    useJUnitPlatform()
}

checkstyle {
    configFile = rootProject.file("config/checkstyle/checkstyle.xml")
}

application {
    mainClass = "hexlet.code.App"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = false
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("reports/jacoco/test/html")
    }
}

jacoco {
    toolVersion = "0.8.11"
    reportsDirectory = layout.buildDirectory.dir("reports/jacoco")
}
