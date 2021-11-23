package com.example.kotlintipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlintipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateBtn.setOnClickListener { showTip() }
    }

    private fun showTip() {
        val formattedTip = NumberFormat.getCurrencyInstance().format(calculateTip()).toString()
        binding.result.text = getString(R.string.tip_amount, formattedTip)
    }

    private fun calculateTip(): Double {
        val percent = getTips()
        val stringInTextField = binding.plainTextInput.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.result.text = ""
            return 0.0
        }

        val roundUp = binding.roundUpSwitch.isChecked
        return if (roundUp) {
            val amount = cost + cost * percent
            ceil(amount)
        }else {
            cost + cost * percent
        }
    }

    private fun getTips() : Double {

        val percentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_amazing -> 0.20
            R.id.option_good -> 0.18
            else -> 0.15
        }
        return percentage
    }
}