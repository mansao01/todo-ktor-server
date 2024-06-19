package com.mansao01.routes

import com.mansao01.entity.ErrorResponse
import com.mansao01.entity.OnlyMsgResponse
import com.mansao01.entity.ToDoDraft
import com.mansao01.repository.ToDoRepository
import com.mansao01.repository.ToDoRepositoryImp
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json


fun Application.todoRouting() {
    val repository: ToDoRepository = ToDoRepositoryImp()
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }
    routing {
        get("/") {
            call.respondText("ToDo API is working")
        }
        get("/todos") {
            call.respond(repository.getAllToDos())
        }

        get("todo/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, ErrorResponse("Id parameter should be number"))
                return@get
            }
            val todo = repository.getToDo(id)
            if (todo == null) call.respond(HttpStatusCode.NotFound, ErrorResponse("todo not found"))
            else call.respond(HttpStatusCode.OK, todo)
        }

        post("/todo") {
            val todoDraft = call.receive<ToDoDraft>()
            val todo = repository.addToDo(todoDraft)
            println(todo)
            call.respond(todo)

        }

        put("todo/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, ErrorResponse("Id parameter should be number"))
                return@put
            }
            val todoDraft = call.receive<ToDoDraft>()
            val result = repository.updateToDo(id, todoDraft)
            println(result.toString())
            if (result) call.respond(HttpStatusCode.OK, OnlyMsgResponse("Update success"))
            else call.respond(HttpStatusCode.NotFound, ErrorResponse("todo not found"))
        }

        delete("todo/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, ErrorResponse("Id parameter should be number"))
                return@delete
            }

            val result = repository.removeToDo(id)
            if (result) call.respond(HttpStatusCode.OK, OnlyMsgResponse("delete success"))
            else call.respond(HttpStatusCode.NotFound, ErrorResponse("todo not found"))

        }
    }
}
