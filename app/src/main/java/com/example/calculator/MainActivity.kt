package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.button_0).setOnClickListener { setTextFields("0") }
        findViewById<TextView>(R.id.button_1).setOnClickListener { setTextFields("1") }
        findViewById<TextView>(R.id.button_2).setOnClickListener { setTextFields("2") }
        findViewById<TextView>(R.id.button_3).setOnClickListener { setTextFields("3") }
        findViewById<TextView>(R.id.button_4).setOnClickListener { setTextFields("4") }
        findViewById<TextView>(R.id.button_5).setOnClickListener { setTextFields("5") }
        findViewById<TextView>(R.id.button_6).setOnClickListener { setTextFields("6") }
        findViewById<TextView>(R.id.button_7).setOnClickListener { setTextFields("7") }
        findViewById<TextView>(R.id.button_8).setOnClickListener { setTextFields("8") }
        findViewById<TextView>(R.id.button_9).setOnClickListener { setTextFields("9") }
        findViewById<TextView>(R.id.button_dot).setOnClickListener { setTextFields(".") }

        findViewById<TextView>(R.id.subtraction_sign).setOnClickListener { setTextFields("-") }
        findViewById<TextView>(R.id.addition_sign).setOnClickListener { setTextFields("+") }
        findViewById<TextView>(R.id.multiplication_sign).setOnClickListener { setTextFields("*") }
        findViewById<TextView>(R.id.division_sign).setOnClickListener { setTextFields("/") }
        findViewById<TextView>(R.id.module_division).setOnClickListener { setTextFields("%") }

        val math_operation = findViewById<TextView>(R.id.math_operation)
        val result_text = findViewById<TextView>(R.id.result_text)

        findViewById<TextView>(R.id.ac_button).setOnClickListener {
            math_operation.text = ""
            result_text.text = ""
        }

        findViewById<TextView>(R.id.back).setOnClickListener {
            val str = math_operation.text.toString()
            if (str.isNotEmpty())
                math_operation.text = str.substring(0, str.length - 1)
            result_text.text = ""
        }

        findViewById<TextView>(R.id.equal_sign).setOnClickListener {
            try {
                val ex = ExpressionBuilder(math_operation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if(result == longRes.toDouble())
                    result_text.text = longRes.toString()
                else
                    result_text.text = result.toString()

            } catch (e:Exception) {
                Log.d("Ошибка", "сообщение: ${e.message}")
            }
        }



    }

    fun setTextFields(str: String) {
        val math_operation = findViewById<TextView>(R.id.math_operation)
        val result_text = findViewById<TextView>(R.id.result_text)
        if (findViewById<TextView>(R.id.result_text).text != "") {
            math_operation.text = result_text.text
            result_text.text = ""
        }
       math_operation.append(str)
    }
}