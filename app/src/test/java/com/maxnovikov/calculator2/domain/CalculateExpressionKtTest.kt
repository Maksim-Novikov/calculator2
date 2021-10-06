package com.maxnovikov.calculator2.domain

import org.junit.Assert
import org.junit.Test

class CalculateExpressionKtTest {

  @Test
  fun testSum() {
    val expression = "2+2"
    val result = "4"

    Assert.assertEquals(result, calculateExpression(expression))
  }

  @Test
  fun testSubtraction() {
    val expression = "4-2"
    val result = "2"

    Assert.assertEquals(result, calculateExpression(expression))
  }

  @Test
  fun testUserInput() {
    testCalculator("2", "2")
    testCalculator("2+", "2")
    testCalculator("2+2", "4")
    testCalculator("2+2-", "4")
  }

  private fun testCalculator(
    expression: String,
    result: String
  ) {
    Assert.assertEquals(result, calculateExpression(expression))
  }

}