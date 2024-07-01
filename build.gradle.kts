plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
// https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
    implementation("org.seleniumhq.selenium:selenium-java:4.18.1")
// https://mvnrepository.com/artifact/com.aventstack/extentreports
    implementation("com.aventstack:extentreports:5.0.9")
// https://mvnrepository.com/artifact/commons-io/commons-io
    implementation("commons-io:commons-io:2.11.0")
// https://mvnrepository.com/artifact/org.testng/testng
    implementation("org.testng:testng:7.7.1")

}

tasks.test {
    useJUnitPlatform()
}
