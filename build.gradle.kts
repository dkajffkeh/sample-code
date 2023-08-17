import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") version "2.7.13-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

extra["springCloudVersion"] = "2021.0.5"

repositories {
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "com.patrick"
    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_17

    tasks.register("prepareKotlinBuildScriptModel") {}

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        implementation("io.netty:netty-all")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }
}

project(":tcp-protocol") {
    dependencies {
        implementation("io.projectreactor.netty:reactor-netty:1.1.2")
    }

    val jar: Jar by tasks
    val bootJar: BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true

}


project(":gateway") {
    dependencies {
        implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    }
}


project(":kafka-sample") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.apache.kafka:kafka-streams")
        implementation("org.springframework.cloud:spring-cloud-bus")
        implementation("org.springframework.cloud:spring-cloud-stream")
        implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka")
        implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka-streams")
        implementation("org.springframework.kafka:spring-kafka")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.springframework.kafka:spring-kafka-test")
    }
}

project(":activeMQ-sample") {
    dependencies {
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("org.springframework.boot:spring-boot-starter-activemq")
            implementation("org.springframework.boot:spring-boot-starter-artemis")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
        }
    }
}

project(":redis-sample") {
    dependencies {
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("org.springframework.boot:spring-boot-starter-data-redis")
            implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("io.projectreactor:reactor-test")
        }
    }
}

project(":netty-server") {
    dependencies {
        dependencies {
            implementation(project(":tcp-protocol"))
        }
    }
}

project(":netty-client") {
    dependencies {
        dependencies {
            implementation(project(":tcp-protocol"))
        }
    }
}

project(":reactor-netty-server") {
    dependencies {
        dependencies {
            implementation(project(":tcp-protocol"))
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("io.projectreactor.netty:reactor-netty:1.1.2")
            implementation("org.apache.httpcomponents:httpclient:4.5.14")
        }
    }
}

project(":reactor-netty-client") {
    dependencies {
        dependencies {
            implementation(project(":tcp-protocol"))
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("io.projectreactor.netty:reactor-netty:1.1.2")
            implementation("org.apache.httpcomponents:httpclient:4.5.14")
        }
    }
}

project(":jpa-sample") {
    dependencies {
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-data-jpa")
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("org.hibernate:hibernate-ehcache")
            runtimeOnly("com.h2database:h2")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
        }
    }
}


