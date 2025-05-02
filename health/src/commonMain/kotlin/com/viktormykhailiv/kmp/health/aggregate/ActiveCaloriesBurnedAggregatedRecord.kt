package com.viktormykhailiv.kmp.health.aggregate

import com.viktormykhailiv.kmp.health.HealthAggregatedRecord
import com.viktormykhailiv.kmp.health.HealthDataType
import com.viktormykhailiv.kmp.health.HealthDataType.ActiveCaloriesBurned
import com.viktormykhailiv.kmp.health.IntervalRecord
import kotlinx.datetime.Instant

class ActiveCaloriesBurnedAggregatedRecord(
    override val startTime: Instant,
    override val endTime: Instant,
    val total: Double
) : HealthAggregatedRecord,
    IntervalRecord {
    override val dataType: HealthDataType = ActiveCaloriesBurned
}