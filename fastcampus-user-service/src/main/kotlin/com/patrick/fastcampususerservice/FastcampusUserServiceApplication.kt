package com.patrick.fastcampususerservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class FastcampusUserServiceApplication

fun main(args: Array<String>) {
    runApplication<FastcampusUserServiceApplication>(*args)
}
