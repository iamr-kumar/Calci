package com.example.calci

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false;
    var lastDot: Boolean = false;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View) {
        val inputText = findViewById<TextView>(R.id.input);
        inputText.append((view as Button).text);
        lastNumeric = true;
    }

    fun onClear(view: View) {
        val inputText = findViewById<TextView>(R.id.input);
        inputText.text = "";
        lastDot = false;
        lastNumeric = false;
    }

    fun onDecimalPoint(view: View) {
        val inputText = findViewById<TextView>(R.id.input);
        if(lastNumeric && !lastDot) {
            inputText.append(".");
            lastDot = true;
        }
    }

    fun onOperator(view: View) {
        val input = findViewById<TextView>(R.id.input);
        if(lastNumeric && !isOperatorAdded(input.text.toString())) {
            input.append((view as Button).text);
            lastNumeric = false;
            lastDot = false;
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if(value.startsWith("-")) {
            false;
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

    fun onEqual(view: View) {
        if(lastNumeric) {
            val tvInput = findViewById<TextView>(R.id.input);
            var tvValue = tvInput.text.toString();
            var prefix = "";
            try {
                if(tvValue.startsWith("-")) {
                    prefix = "-";
                    tvValue = tvValue.substring(1);
                }
                if(tvValue.contains("-")) {
//                    Subtraction
                    val splitValue = tvValue.split("-");
                    var first = splitValue[0];
                    var two = splitValue[1];

                    if(!prefix.isEmpty()) {
                        first = prefix + first;
                    }

                    val result = first.toDouble() - two.toDouble();

                    tvInput.text = removeZeroAfterDecimal(result.toString());
                 }
                else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("+");
                    var first = splitValue[0];
                    var two = splitValue[1];

                    if(!prefix.isEmpty()) {
                        first = prefix + first;
                    }

                    val result = first.toDouble() + two.toDouble();

                    tvInput.text = removeZeroAfterDecimal(result.toString());
                }
                else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("*");
                    var first = splitValue[0];
                    var two = splitValue[1];

                    if(!prefix.isEmpty()) {
                        first = prefix + first;
                    }

                    val result = first.toDouble() * two.toDouble();

                    tvInput.text = removeZeroAfterDecimal(result.toString());
                }
                else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("/");
                    var first = splitValue[0];
                    var two = splitValue[1];

                    if(!prefix.isEmpty()) {
                        first = prefix + first;
                    }

                    val result = first.toDouble() / two.toDouble();

                    tvInput.text = removeZeroAfterDecimal(result.toString());
                }
            } catch (err: ArithmeticException) {

            }
        }
    }

    private fun removeZeroAfterDecimal(result: String): String {
        val size = result.length;
        if(result[size - 1].equals('0') && result[size - 2].equals('.') ) {
            return result.substring(0, size - 2);
        }
        else {
            return result;
        }
    }

}