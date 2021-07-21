package com.dynamicdevz.dynamiccoroutines.model

data class JikanResponse(
    val last_page: Int,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val results: List<Result>
)