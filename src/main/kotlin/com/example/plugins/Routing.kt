package com.example.plugins

import com.example.plugins.routes.reservationRouting
import com.example.plugins.routes.serviceSiteRouting
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        serviceSiteRouting()
        reservationRouting()

        swaggerUI("swagger", swaggerFile = "openapi/documentation.yaml") {
            version = "4.15.5"
        }
    }
}
