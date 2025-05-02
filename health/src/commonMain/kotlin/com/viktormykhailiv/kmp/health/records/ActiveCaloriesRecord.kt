package com.viktormykhailiv.kmp.health.records

import com.viktormykhailiv.kmp.health.HealthDataType
import com.viktormykhailiv.kmp.health.HealthDataType.ActiveCalories
import com.viktormykhailiv.kmp.health.IntervalRecord
import kotlinx.datetime.Instant

class ActiveCaloriesRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val total: Double
): IntervalRecord {
    override val dataType: HealthDataType = ActiveCalories

    init {
        require(startTime <= endTime) { "startTime must be before endTime." }
        require(total >= 0) { "total must be higher or equal 0 calories" }
    }
}