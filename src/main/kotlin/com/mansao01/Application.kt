package com.mansao01

import com.mansao01.routes.todoRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.callloging.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
//    kotlin serialization


//    logging
    install(CallLogging)

    todoRouting()
}
