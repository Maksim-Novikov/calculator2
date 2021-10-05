package com.maxnovikov.calculator2.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.calculator2.R.layout
import com.maxnovikov.calculator2.databinding.MainActivityBinding
import com.maxnovikov.calculator2.presentation.common.BaseActivity
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

    viewBinding.mainEquals.setOnClickListener {

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
      textView.setOnClickListener { viewModel.onNumberClick(index) }
    }



    viewModel.expressionState.observe(this) { state ->
      viewBinding.mainInput.setText(state)
    }
    viewModel.resultState.observe(this) { state ->
      viewBinding.mainResult.text = state
    }
  }

  private fun openSettings() {
    getResult.launch(10)
  }
}



