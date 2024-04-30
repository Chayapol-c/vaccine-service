package com.example.models

import kotlinx.serialization.Serializable


@Serializable
data class ServiceSite(
    val id: String,
    val name: String,
    val location: String,
    val capacity: Int,
)

val serviceSiteStorage = mutableListOf<ServiceSite>()