package com.maxnovikov.calculator2.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.calculator2.R.layout
import com.maxnovikov.calculator2.databinding.MainActivityBinding
import com.maxnovikov.calculator2.presentation.common.BaseActivity
import com.maxnovikov.calculator2.presentation.main.Operator.DIVIDE
import com.maxnovikov.calculator2.presentation.main.Operator.MINUS
import com.maxnovikov.calculator2.presentation.main.Operator.MULTIPLICATION
import com.maxnovikov.calculator2.presentation.main.Operator.PLUS
import com.maxnovikov.calculator2.presentation.settings.Result

class MainActivity : BaseActivity() {

  private val viewModel: MainViewModel by viewModels()
  private val viewBinding by viewBinding(MainActivityBinding::bind)

  private val getResult: ActivityResultLauncher<Int> =
    registerForActivityResult(Result()) { result ->
      Toast.makeText(this, "result $result", Toast.LENGTH_SHORT).show()
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.main_activity)

    viewBinding.mainInput.apply {
      showSoftInputOnFocus = false
    }
    viewBinding.mainActivitySettings.setOnClickListener {
      openSettings()
    }

    listOf(
      viewBinding.mainZero,
      viewBinding.mainOne,
      viewBinding.mainTwo,
      viewBinding.mainThree,
      viewBinding.mainFour,
      viewBinding.mainFive,
      viewBinding.mainSix,
      viewBinding.mainSeven,
      viewBinding.mainEight,
      viewBinding.mainNine,
    ).forEachIndexed { index, textView ->
      textView.setOnClickListener {
        viewModel.onNumberClicked(index, viewBinding.mainInput.selectionStart)
      }
    }

    viewModel.expressionState.observe(this) { state ->
      viewBinding.mainInput.setText(state.expression)
      viewBinding.mainInput.setSelection(state.selection)
    }
    viewModel.resultState.observe(this) { state ->
      viewBinding.mainResult.text = state
    }

    viewBinding.mainBack.setOnClickListener {
      viewModel.onBackSpaceClicked(viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainClear.setOnClickListener {
      viewModel.onClearClicked()
    }

    viewBinding.mainPoint.setOnClickListener {
      viewModel.onDotClicked(viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainMinus.setOnClickListener {
      viewModel.onOperatorClicked(MINUS, viewBinding.mainInput.selectionStart)
    }
    viewBinding.mainPlus.setOnClickListener {
      viewModel.onOperatorClicked(PLUS, viewBinding.mainInput.selectionStart)
    }
    viewBinding.mainDivider.setOnClickListener {
      viewModel.onOperatorClicked(DIVIDE, viewBinding.mainInput.selectionStart)
    }
    viewBinding.mainMultiple.setOnClickListener {
      viewModel.onOperatorClicked(MULTIPLICATION, viewBinding.mainInput.selectionStart)
    }

    viewBinding.mainEquals.setOnClickListener {
      viewModel.onEqualsClicked()
    }
  }

  private fun openSettings() {
    getResult.launch(10)
  }
}



