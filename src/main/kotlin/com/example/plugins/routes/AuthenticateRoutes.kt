package com.example.plugins.routes

import com.example.models.UserSession
import com.example.plugins.redirects
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Route.authenticateRoute() {
    route(""){
        get("/login") {

        }

        get("/callback") {
            val currentPrincipal: OAuthAccessTokenResponse.OAuth2? = call.principal()
            currentPrincipal?.let { principal ->
                principal.state?.let { state ->
                    call.sessions.set(UserSession(state, principal.accessToken))
                    redirects[state]?.let { redirect ->
                        call.respondRedirect(redirect)
                        return@get
                    }
                }
            }
            call.respondRedirect("/home")
        }

        delete ("/logout") {

        }
    }
}