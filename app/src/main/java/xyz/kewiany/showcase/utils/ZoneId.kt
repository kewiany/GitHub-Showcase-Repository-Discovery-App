package xyz.kewiany.showcase.utils

import org.threeten.bp.Clock
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId

val now: Instant
    get() = Instant.now()
val zoneID: ZoneId = Clock.systemDefaultZone().zone