pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}

rootProject.name = "sample-code"
include("gateway")
include("kafka-sample")
include("activeMQ-sample")
include("redis-sample")
include("netty-server")
include("netty-client")
include("tcp-protocol")
