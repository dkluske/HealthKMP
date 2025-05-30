package com.viktormykhailiv.kmp.health

sealed interface HealthDataType {

    data object HeartRate : HealthDataType

    data object Sleep : HealthDataType

    data object Steps : HealthDataType

    data object Weight : HealthDataType

    data object ActiveCaloriesBurned : HealthDataType
}