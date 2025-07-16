package com.qburst.bind.skillforge

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform