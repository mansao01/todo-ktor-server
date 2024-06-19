package com.mansao01.entity

import kotlinx.serialization.Serializable


@Serializable
data class ErrorResponse(val error: String)