package com.maxnovikov.calculator2.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.maxnovikov.calculator2.presentation.main.Operator.MINUS
import com.maxnovikov.calculator2.presentation.main.Operator.PLUS
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @Test
  fun testInitialState() {
    val viewModel = MainViewModel()

    Assert.assertEquals("", viewModel.resultState.value)
    Assert.assertEquals(ExpressionState("", 0), viewModel.expressionState.value)
  }

  @Test
  fun testPlus() {
    val viewModel = MainViewModel()

    viewModel.onNumberClicked(2, 0)
    viewModel.onOperatorClicked(PLUS, 1)
    viewModel.onNumberClicked(2, 2)

    Assert.assertEquals("4", viewModel.resultState.value)
  }

  @Test
  fun testMinus() {
    val viewModel = MainViewModel()

    viewModel.onNumberClicked(2, 0)
    viewModel.onOperatorClicked(MINUS, 1)
    viewModel.onNumberClicked(2, 2)

    Assert.assertEquals("0", viewModel.resultState.value)
  }

  @Test
  fun testLongExpression() {
    val viewModel = MainViewModel()

    viewModel.onNumberClicked(2, 0)
    viewModel.onOperatorClicked(PLUS, 1)
    viewModel.onNumberClicked(2, 2)
    viewModel.onOperatorClicked(Operator.MULTIPLICATION, 3)
    viewModel.onNumberClicked(3, 4)

    Assert.assertEquals("2+2*3", viewModel.expressionState.value?.expression)
    Assert.assertEquals("8", viewModel.resultState.value)
  }

}