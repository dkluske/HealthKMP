package com.viktormykhailiv.kmp.health.records

import com.viktormykhailiv.kmp.health.HealthDataType
import com.viktormykhailiv.kmp.health.HealthDataType.ActiveCaloriesBurned
import com.viktormykhailiv.kmp.health.IntervalRecord
import kotlinx.datetime.Instant

/**
 * Captures the total calories burned
 * @param total The total amount of calories burned. Must be >= 0
 */
class ActiveCaloriesBurnedRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val total: Double
): IntervalRecord {
    override val dataType: HealthDataType = ActiveCaloriesBurned

    init {
        require(startTime <= endTime) { "startTime must be before endTime." }
        require(total >= 0) { "total must be higher or equal 0 calories" }
    }
}