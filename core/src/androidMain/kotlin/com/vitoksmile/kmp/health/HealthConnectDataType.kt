package com.vitoksmile.kmp.health

import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.Record
import androidx.health.connect.client.records.StepsRecord
import androidx.health.connect.client.records.WeightRecord
import kotlin.reflect.KClass

internal fun HealthDataType.toRecordType(): KClass<out Record> = when (this) {
    HealthDataType.STEPS -> StepsRecord::class
    HealthDataType.WEIGHT -> WeightRecord::class
}

/**
 * Returns a permission defined in [HealthPermission] to read provided [HealthDataType].
 */
internal fun HealthDataType.toHealthPermission(
    isRead: Boolean = false,
    isWrite: Boolean = false,
): String {
    require(isRead != isWrite)

    return if (isRead) {
        HealthPermission.getReadPermission(recordType = toRecordType())
    } else {
        HealthPermission.getWritePermission(recordType = toRecordType())
    }
}