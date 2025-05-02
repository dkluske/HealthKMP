@file:Suppress("unused")

package com.viktormykhailiv.kmp.health

import com.viktormykhailiv.kmp.health.HealthDataType.ActiveCalories
import com.viktormykhailiv.kmp.health.HealthDataType.HeartRate
import com.viktormykhailiv.kmp.health.HealthDataType.Sleep
import com.viktormykhailiv.kmp.health.HealthDataType.Steps
import com.viktormykhailiv.kmp.health.HealthDataType.Weight
import com.viktormykhailiv.kmp.health.aggregate.ActiveCaloriesAggregatedRecord
import com.viktormykhailiv.kmp.health.aggregate.HeartRateAggregatedRecord
import com.viktormykhailiv.kmp.health.aggregate.SleepAggregatedRecord
import com.viktormykhailiv.kmp.health.aggregate.StepsAggregatedRecord
import com.viktormykhailiv.kmp.health.aggregate.WeightAggregatedRecord
import com.viktormykhailiv.kmp.health.records.ActiveCaloriesRecord
import com.viktormykhailiv.kmp.health.records.HeartRateRecord
import com.viktormykhailiv.kmp.health.records.SleepSessionRecord
import com.viktormykhailiv.kmp.health.records.StepsRecord
import com.viktormykhailiv.kmp.health.records.WeightRecord
import kotlinx.datetime.Instant
import kotlin.time.Duration

suspend fun HealthManager.readActiveCalories(
    startTime: Instant,
    endTime: Instant,
): Result<List<ActiveCaloriesRecord>> =
    readData(
        startTime = startTime,
        endTime = endTime,
        type = ActiveCalories
    ).map { it.filterIsInstance<ActiveCaloriesRecord>() }

suspend fun HealthManager.aggregateActiveCalories(
    startTime: Instant,
    endTime: Instant,
): Result<ActiveCaloriesAggregatedRecord> =
    aggregate(
        startTime = startTime,
        endTime = endTime,
        type = ActiveCalories
    ).mapCatching { it as ActiveCaloriesAggregatedRecord }

suspend fun HealthManager.readHeartRate(
    startTime: Instant,
    endTime: Instant,
): Result<List<HeartRateRecord>> =
    readData(
        startTime = startTime,
        endTime = endTime,
        type = HeartRate,
    ).map { it.filterIsInstance<HeartRateRecord>() }

suspend fun HealthManager.aggregateHeartRate(
    startTime: Instant,
    endTime: Instant,
): Result<HeartRateAggregatedRecord> =
    aggregate(
        startTime = startTime,
        endTime = endTime,
        type = HeartRate,
    ).mapCatching { it as HeartRateAggregatedRecord }

suspend fun HealthManager.readSleep(
    startTime: Instant,
    endTime: Instant,
): Result<List<SleepSessionRecord>> =
    readData(
        startTime = startTime,
        endTime = endTime,
        type = Sleep,
    ).map { it.filterIsInstance<SleepSessionRecord>() }

suspend fun HealthManager.aggregateSleep(
    startTime: Instant,
    endTime: Instant,
): Result<SleepAggregatedRecord> =
    aggregate(
        startTime = startTime,
        endTime = endTime,
        type = Sleep,
    ).mapCatching { it as SleepAggregatedRecord }

suspend fun HealthManager.readSteps(
    startTime: Instant,
    endTime: Instant,
): Result<List<StepsRecord>> =
    readData(
        startTime = startTime,
        endTime = endTime,
        type = Steps,
    ).map { it.filterIsInstance<StepsRecord>() }

suspend fun HealthManager.aggregateSteps(
    startTime: Instant,
    endTime: Instant,
): Result<StepsAggregatedRecord> =
    aggregate(
        startTime = startTime,
        endTime = endTime,
        type = Steps,
    ).mapCatching { it as StepsAggregatedRecord }

suspend fun HealthManager.readWeight(
    startTime: Instant,
    endTime: Instant,
): Result<List<WeightRecord>> =
    readData(
        startTime = startTime,
        endTime = endTime,
        type = Weight,
    ).map { it.filterIsInstance<WeightRecord>() }

suspend fun HealthManager.aggregateWeight(
    startTime: Instant,
    endTime: Instant,
): Result<WeightAggregatedRecord> =
    aggregate(
        startTime = startTime,
        endTime = endTime,
        type = Weight,
    ).mapCatching { it as WeightAggregatedRecord }

val IntervalRecord.duration: Duration
    get() = endTime - startTime
