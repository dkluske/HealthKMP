package com.vitoksmile.kmm.health

actual class HealthManagerFactory {

    actual fun createManager(): HealthManager =
        HealthConnectManager(ApplicationContextHolder.applicationContext)
}