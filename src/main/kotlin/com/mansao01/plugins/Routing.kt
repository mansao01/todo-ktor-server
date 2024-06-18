package com.mansao01.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}

fun Application.todoRouting() {
    routing {
        get("/todos") {

        }

        get("todo/{id}"){
            val id  = call.parameters["id"]
            call.respondText("ToDo list number $id")

        }

        post("/todo") {

        }

        put("todo/{id}"){
            val id  = call.parameters["id"]

        }

        delete("todo/{id}"){
            val id  = call.parameters["id"]


        }
    }
}
