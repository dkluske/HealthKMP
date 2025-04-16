package com.viktormykhailiv.kmp.health

import kotlinx.datetime.Instant

class JvmHealthManager : HealthManager {
    override fun isAvailable(): Result<Boolean> {
        return Result.success(true)
    }

    override suspend fun isAuthorized(
        readTypes: List<HealthDataType>,
        writeTypes: List<HealthDataType>
    ): Result<Boolean> {
        return Result.success(true)
    }

    override suspend fun requestAuthorization(
        readTypes: List<HealthDataType>,
        writeTypes: List<HealthDataType>
    ): Result<Boolean> {
        return Result.success(true)
    }

    override suspend fun isRevokeAuthorizationSupported(): Result<Boolean> {
        return Result.success(true)
    }

    override suspend fun revokeAuthorization(): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun readData(
        startTime: Instant,
        endTime: Instant,
        type: HealthDataType
    ): Result<List<HealthRecord>> {
        return Result.success(emptyList())
    }

    override suspend fun writeData(records: List<HealthRecord>): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun aggregate(
        startTime: Instant,
        endTime: Instant,
        type: HealthDataType
    ): Result<HealthAggregatedRecord> {
        return Result.failure(NotImplementedError())
    }
}