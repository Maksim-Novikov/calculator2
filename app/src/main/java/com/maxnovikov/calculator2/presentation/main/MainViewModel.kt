package com.maxnovikov.calculator2.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxnovikov.calculator2.domain.calculateExpression

class MainViewModel : ViewModel() {

  private var expression: String = ""

  private val _expressionState = MutableLiveData(ExpressionState(expression, 0))
  val expressionState: LiveData<ExpressionState> = _expressionState

  private val _resultState = MutableLiveData("")
  val resultState: LiveData<String> = _resultState

  fun onEqualsClicked() {
    val result = calculateExpression(expression)
    _resultState.value = result
    _expressionState.value = ExpressionState(result, result.length)
    expression = result
  }

  fun onNumberClicked(number: Int, selection: Int) {
    expression = putInSelection(number.toString(), selection)
    _expressionState.value = ExpressionState(expression, selection + 1)
    _resultState.value = calculateExpression(expression)
  }

  fun onBackSpaceClicked(selection: Int) {
    expression = expression.removeRange(selection - 1, selection)
    _expressionState.value = ExpressionState(expression, (selection - 1).coerceAtLeast(0))
    _resultState.value = calculateExpression(expression)
  }

  fun onClearClicked() {
    expression = ""
    _expressionState.value = ExpressionState("", 0)
    _resultState.value = expression
  }

  fun onDotClicked(selection: Int) {
    expression = putInSelection(".", selection)
    _expressionState.value = ExpressionState(expression, selection + 1)
    _resultState.value = calculateExpression(expression)
  }

  fun onOperatorClicked(operator: Operator, selection: Int) {
    expression = putInSelection(operator.symbol, selection)
    _expressionState.value = ExpressionState(expression, selection + 1)
    _resultState.value = calculateExpression(expression)
  }

  private fun putInSelection(put: String, selection: Int): String {
    return expression.substring(0, selection) +
        put +
        expression.substring(selection, expression.length)
  }

  override fun onCleared() {
    super.onCleared()
    Log.d("MainViewModel", "onCleared")
  }

}