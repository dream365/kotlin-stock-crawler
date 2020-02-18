package com.dream365.kotlin.driver

import org.openqa.selenium.chrome.ChromeDriver

interface DriverExecutor {
    companion object {
        fun getFromPath(url: String): ChromeDriver {
            val driver = ChromeDriver()
            driver.get(url)
            return driver
        }
    }
}