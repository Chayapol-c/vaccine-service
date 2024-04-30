package com.example.plugins

import com.example.plugins.routes.reservationRouting
import com.example.plugins.routes.serviceSiteRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        serviceSiteRouting()
        reservationRouting()
    }
}
