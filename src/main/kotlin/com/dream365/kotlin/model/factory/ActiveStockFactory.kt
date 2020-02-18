package com.dream365.kotlin.model.factory

import com.dream365.kotlin.driver.DriverExecutor
import com.dream365.kotlin.model.ActiveStock
import com.dream365.kotlin.model.Change
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver

class ActiveStockFactory {
    companion object {
        private fun getByAttributeName(driver: ChromeDriver, attribute: String): List<String> =
            driver.findElementsByTagName("tr")
                .flatMap { it.findElements(By.tagName("td")) }
                .filter { it.getAttribute("aria-label") == attribute }
                .map { it.text }

        private fun getSymbols(driver: ChromeDriver): List<String> = getByAttributeName(driver, "Symbol")

        private fun getNames(driver: ChromeDriver): List<String> = getByAttributeName(driver, "Name")

        private fun getPrices(driver: ChromeDriver): List<Float> = getByAttributeName(driver, "Price (Intraday)")
            .map { it.toFloat() }

        private fun getChanges(driver: ChromeDriver): List<Change> = getByAttributeName(driver, "Change")
            .map { Change.getChangeFromString(it) }

        private fun getPercentChanges(driver: ChromeDriver): List<Change> = getByAttributeName(driver, "% Change")
            .map { Change.getChangeFromString(it) }

        fun createActiveStocks(driver: ChromeDriver): ArrayList<ActiveStock> {
            val symbols = getSymbols(driver)
            val names = getNames(driver)
            val prices = getPrices(driver)
            val changes = getChanges(driver)
            val percentChanges = getPercentChanges(driver)
            val len = symbols.size

            val activeStocks: ArrayList<ActiveStock> = arrayListOf()

            for(i in (0 until len))
                activeStocks.add(ActiveStock(symbols[i], names[i], prices[i], changes[i], percentChanges[i]))

            return activeStocks
        }
    }
}