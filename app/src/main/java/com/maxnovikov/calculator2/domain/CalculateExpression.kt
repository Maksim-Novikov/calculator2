package com.maxnovikov.calculator2.domain

import com.fathzer.soft.javaluator.DoubleEvaluator
import kotlin.math.floor

/**
 * Вычисляет значение выражения [expression] и возвращает результат в формате строки
 * */
fun calculateExpression(expression: String): String {

  if (expression.isBlank()) return ""

  var formattedExpression = expression
  while (!formattedExpression.last().isDigit()) {
    formattedExpression = formattedExpression.dropLast(1)
  }
  val value = DoubleEvaluator().evaluate(formattedExpression)
  return if (floor(value) == value) {
    value.toInt().toString()
  } else {
    value.toString()
  }
}