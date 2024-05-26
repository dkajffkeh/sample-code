import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") version "2.7.13-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("kapt") version "1.6.21"
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
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-kapt")
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
        implementation("com.fasterxml.jackson.core:jackson-databind:2.12.3")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
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
            implementation(project(":tcp-protocol"))
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
            implementation("org.springframework.boot:spring-boot-starter-activemq")
            implementation("org.springframework.boot:spring-boot-starter-artemis")
        }
    }
}

project(":netty-client") {
    dependencies {
        dependencies {
            implementation(project(":tcp-protocol"))
            implementation("org.springframework.boot:spring-boot-starter-web")
        }
    }
}

project(":reactor-netty-server") {
    dependencies {
        dependencies {
            implementation(project(":tcp-protocol"))
            implementation("org.springframework.boot:spring-boot-starter-webflux")
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
            implementation("org.springframework.boot:spring-boot-starter-webflux")
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
            runtimeOnly("com.mysql:mysql-connector-j")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
        }
    }
}

project(":reactor") {
    dependencies {
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-webflux")
            compileOnly("org.projectlombok:lombok")
            annotationProcessor("org.projectlombok:lombok")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("io.projectreactor:reactor-test")
        }
    }
}

project(":fastcampus-user-service") {
    dependencies {
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
            testImplementation("org.springframework.boot:spring-boot-starter-webflux")
            implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
            implementation("org.jetbrains.kotlin:kotlin-reflect")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
            kapt("org.springframework.boot:spring-boot-configuration-processor")
            runtimeOnly("com.h2database:h2")
            runtimeOnly("io.r2dbc:r2dbc-h2")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("io.projectreactor:reactor-test")
        }
    }
}

project(":elasticsearch-sample") {
    dependencies {
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-webflux")
            developmentOnly("org.springframework.boot:spring-boot-devtools")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("io.projectreactor:reactor-test")
            implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
        }
    }
}


