package mx.edu.utez.divisas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import mx.edu.utez.divisas.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val formatNum = DecimalFormat("#.00")
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val spinner1 : Spinner = binding.userCurrency
        val spinner2 : Spinner = binding.convertCurrency
        ArrayAdapter.createFromResource(
            this,
            R.array.currencys,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1.adapter = adapter
            spinner2.adapter = adapter
        }
        val btnConvert: View = binding.btnConvert
        btnConvert.setOnClickListener {
            val userCurrency = spinner1.selectedItem.toString()
            val convertCurrency = spinner2.selectedItem.toString()
            val userAmount = binding.txtCantidad.text.toString()
            var resultText = binding.result
            if (userAmount.isNotEmpty()) {
                val amount = userAmount.toDouble()
                var result = convert(userCurrency, convertCurrency, amount)
                result = formatNum.format(result).toDouble()
                resultText.text = "La cantidad en $convertCurrency es de: $result"
            }else{
                Toast.makeText(this@MainActivity,"Coloque primero la cantidad", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun convert(userCurrency: String, convertCurrency: String, amount: Double): Double {
        var result = 0.0
        if (userCurrency == "MXN" && convertCurrency == "USD") {
            result = amount / 19.5
        } else if (userCurrency == "MXN" && convertCurrency == "EUR") {
            result = amount / 22.5
        } else if (userCurrency == "MXN" && convertCurrency == "GBP") {
            result = amount / 26.5
        } else if (userCurrency == "USD" && convertCurrency == "MXN") {
            result = amount * 19.5
        } else if (userCurrency == "USD" && convertCurrency == "EUR") {
            result = amount * 0.85
        } else if (userCurrency == "USD" && convertCurrency == "GBP") {
            result = amount * 0.75
        } else if (userCurrency == "EUR" && convertCurrency == "MXN") {
            result = amount * 22.5
        } else if (userCurrency == "EUR" && convertCurrency == "USD") {
            result = amount * 1.18
        } else if (userCurrency == "EUR" && convertCurrency == "GBP") {
            result = amount * 0.89
        } else if (userCurrency == "GBP" && convertCurrency == "MXN") {
            result = amount * 26.5
        } else if (userCurrency == "GBP" && convertCurrency == "USD") {
            result = amount * 1.33
        } else if (userCurrency == "GBP" && convertCurrency == "EUR") {
            result = amount * 1.12
        }else{
            result = amount
        }
        return result
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}