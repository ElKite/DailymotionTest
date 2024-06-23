package com.elkite.utils

import java.time.Duration
import java.time.Instant

fun Long.formatTimestamp(): String {

    val now = Instant.now()
    val past = Instant.ofEpochSecond(this)
    val duration = Duration.between(past, now)

    return when {
        duration.toHours() < 1 -> "${duration.toMinutes()}m ago"
        duration.toHours() < 24 -> "${duration.toHours()}h ago"
        else -> "${duration.toDays()}d ago"
    }
}

fun Long.formatDuration(): String {
    val duration = Duration.ofSeconds(this)
    return when {
        duration.toMinutes() < 1 -> "${duration.seconds}s"
        duration.toHours() > 1 -> "${duration.toHours()}h ${duration.toMinutes() % 60}m"
        else -> "${duration.toMinutes()}m ${duration.seconds % 60}s"
    }
}