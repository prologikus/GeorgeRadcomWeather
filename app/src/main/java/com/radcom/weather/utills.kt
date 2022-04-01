package com.radcom.weather

import kotlin.math.pow
import kotlin.math.roundToInt


//round doubles to n' decimal
fun Double.roundTo(numFractionDigits: Int): Double {
    val factor = 10.0.pow(numFractionDigits.toDouble())
    return (this * factor).roundToInt() / factor
}