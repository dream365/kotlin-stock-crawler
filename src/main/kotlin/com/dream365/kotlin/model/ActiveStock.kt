package com.dream365.kotlin.model

data class Change(
    val positive: Boolean,
    val value: Float
) {
    companion object {
        fun getChangeFromString(str: String): Change {
            val isPositive = str.contains('+')
            val valueStr: String = if(isPositive) str.replace("+", "") else str.replace("-", "")
            val value: Float = valueStr.replace("%", "").trim().toFloat()

            return Change(isPositive, value)
        }
    }
}

data class ActiveStock(
    val symbol: String,
    val name: String,
    val price: Float,
    val change: Change,
    val percentChange: Change
)