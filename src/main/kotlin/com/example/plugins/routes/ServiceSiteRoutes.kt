package com.example.plugins.routes

import com.example.models.ServiceSite
import com.example.models.serviceSiteStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.serviceSiteRouting() {
    route("/sites") {
        get{
            if (serviceSiteStorage.isNotEmpty()) {
                call.respond(serviceSiteStorage)
            } else {
                call.respondText("No service site found", status = HttpStatusCode.NotFound)
            }

        }
    }
    route("/site") {
        get( "/{id?}" ) {
            val id = call.parameters["id"] ?: return@get call.respondText("Missing Id", status = HttpStatusCode.BadRequest)
            val serviceSite = serviceSiteStorage.find { it.id == id } ?: return@get call.respondText("No service site with id $id", status = HttpStatusCode.NotFound)
            call.respond(serviceSite)
        }

        post {
            val serviceSite = call.receive<ServiceSite>()
            serviceSiteStorage.add(serviceSite)
            call.respondText("Success", status = HttpStatusCode.Created)

        }

        put("/{id?}") {

        }

        delete("/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (serviceSiteStorage.removeIf { it.id == id }) {
                call.respondText("Success", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}