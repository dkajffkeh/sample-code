pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}

rootProject.name = "sample-code"
include("chat-app")
include("gateway")
include("kafka-sample")
include("activeMQ-sample")
include("redis-sample")
include("netty-server")
include("netty-client")
include("tcp-protocol")
include("jpa-sample")
include("reactor-netty-server")
include("reactor-netty-client")
include("reactor")
include("fastcampus-user-service")
include("elasticsearch-sample")
include("docker-app")

