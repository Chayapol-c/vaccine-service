package com.example.plugins.routes

import com.example.models.serviceSiteStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.reservationRouting() {
    route("") {
        get("/reservations") {

        }

        get("/site/{id?}/time-slots") {
            val id = call.parameters["id"] ?: return@get call.respondText("Missing Id", status = HttpStatusCode.BadRequest)
            val serviceSite = serviceSiteStorage.find { it.id == id } ?: return@get call.respondText("No service site with id $id", status = HttpStatusCode.NotFound)

        }
    }
}