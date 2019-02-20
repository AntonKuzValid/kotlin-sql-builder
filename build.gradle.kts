import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.3.20"
//    kotlin("jvm") version "1.3.20"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}


repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    //    implementation(kotlin("stdlib"))
//    implementation(kotlin("reflect"))
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    // https://mvnrepository.com/artifact/org.hibernate.javax.persistence/hibernate-jpa-2.0-api
    compile(group = "org.hibernate.javax.persistence", name = "hibernate-jpa-2.0-api", version = "1.0.1.Final")

//    implementation("javax.persistence:javax.persistence-api:2.2")
    testImplementation("junit:junit:4.12")
}