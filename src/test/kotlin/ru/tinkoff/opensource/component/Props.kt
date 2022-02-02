package ru.tinkoff.opensource.component

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.lang.System.getenv

@Component
class Props {

    @Value("\${spring.application.name}")
    var applicationName: String = getenv("appName") ?: "top-ci-example-multi-module-one"

    @Value("\${server.port}")
    var port: String = "8080"

    private val host = getenv("ns-host") ?: "http://localhost:$port"

    val url
        get(): String {
            return "$host/$applicationName"
        }
}