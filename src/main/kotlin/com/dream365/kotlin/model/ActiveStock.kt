package com.dream365.kotlin.model

data class Change(
    val positive: Boolean,
    val value: Long
) {
    companion object {
        fun getChangeFromString(str: String): Change {
            val isPositive = str.contains('+')
            val valueStr: String = if(isPositive) str.replace("+", "") else str.replace("-", "")
            val value: Long = valueStr.replace("%", "").trim().toLong()

            return Change(isPositive, value)
        }
    }
}

data class ActiveStock(
    val symbol: String,
    val name: String,
    val price: Long,
    val change: Change,
    val percentChange: Change
)