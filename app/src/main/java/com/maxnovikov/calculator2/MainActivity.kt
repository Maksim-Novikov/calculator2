package com.maxnovikov.calculator2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import com.maxnovikov.calculator2.SettingsActivity.Companion.SETTINGS_RESULT_REQUEST_CODE
import com.maxnovikov.calculator2.databinding.MainActivityBinding

class MainActivity : BaseActivity() {

  private val viewBinding by viewBinding(MainActivityBinding::bind)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)

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
      textView.setOnClickListener { onNumberClick(index) }
    }

    viewBinding.mainBack.setOnClickListener {
      removeLast()
    }
  }

  @SuppressLint("SetTextI18n")
  private fun onNumberClick(number: Int) {
    with(viewBinding.mainSecondArgument) {
      text = text.toString() + number.toString()
    }
  }

  private fun removeLast() {
    with(viewBinding.mainSecondArgument) {
      text = text.dropLast(1)
    }
  }

  private fun openSettings() {
    Toast.makeText(this, "Открытие настроек", Toast.LENGTH_LONG).show()
    val intent = Intent(this, SettingsActivity::class.java)
    intent.putExtra(SettingsActivity.SETTINGS_RESULT_KEY, 10)
    startActivityForResult(intent, SETTINGS_RESULT_REQUEST_CODE)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    data?.getStringExtra(SettingsActivity.SETTINGS_RESULT_KEY)?.let {
      Toast.makeText(this, "result $it", Toast.LENGTH_SHORT).show()
    }
  }
}



