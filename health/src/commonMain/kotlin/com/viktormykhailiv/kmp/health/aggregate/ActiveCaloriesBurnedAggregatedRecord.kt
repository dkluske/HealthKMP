package com.viktormykhailiv.kmp.health.aggregate

import com.viktormykhailiv.kmp.health.HealthAggregatedRecord
import com.viktormykhailiv.kmp.health.HealthDataType
import com.viktormykhailiv.kmp.health.HealthDataType.ActiveCaloriesBurned
import com.viktormykhailiv.kmp.health.IntervalRecord
import kotlinx.datetime.Instant

/**
 * Captures the aggregated number of burned calories
 * @param total The total aggregated number of burned calories
 */
class ActiveCaloriesBurnedAggregatedRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val total: Double
) : HealthAggregatedRecord,
    IntervalRecord {
    override val dataType: HealthDataType = ActiveCaloriesBurned
}