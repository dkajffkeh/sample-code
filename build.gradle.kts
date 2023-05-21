plugins {
    java
    id("org.springframework.boot") version "2.7.13-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

extra["springCloudVersion"] = "2021.0.5"

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
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }
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



