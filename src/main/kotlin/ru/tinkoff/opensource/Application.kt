package ru.tinkoff.opensource

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class Application

@Suppress("SpreadOperator")
fun main(args: Array<String>) {
    SpringApplicationBuilder(Application::class.java)
        .run(*args)
}
