package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private var input: TextView? =null
    private var lastNum: Boolean=false
    private var decimal: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input=findViewById(R.id.calc_input)

    }

    fun onDigit(view: View){
        input?.append((view as Button).text)
        lastNum=true
        decimal=false

    }

    fun onClear(view: View){
        input?.text=" "
    }

    fun onDecimal(view: View){
        if(lastNum && !(decimal)){
            input?.append(".")
            lastNum=false
            decimal=true
        }
    }
    private fun isOperatorAdded(value: String):Boolean{
         return if(value.startsWith("-")){
            return false
        }
        else{
            value.contains("+")||value.contains("-")||value.contains("*")||value.contains("/")
        }
    }
    fun onOperator(view: View){
        input?.text?.let {
            if (lastNum && !isOperatorAdded(it.toString())){
                input?.append((view as Button).text)
                lastNum=false
                decimal=false
            }
        }
    }
    fun onEqual(view: View){
        if (lastNum){
            var number=input?.text.toString()
            var prefix=""

            try {
                if (number.startsWith("-")){
                    prefix="-"
                    number=number.substring(1)
                }
                if (number.contains("-")){
                    var split=number.split("-")
                    var num1=split[0]
                    var num2=split[1]
                    input?.text=(num1.toDouble()-num2.toDouble()).toString()

                    if (prefix.isNotEmpty()){
                        num1=prefix+num1
                    }
                }
                else if (number.contains("+")){
                    var split=number.split("+")
                    var num1=split[0]
                    var num2=split[1]
                    input?.text=(num1.toDouble()+num2.toDouble()).toString()

                    if (prefix.isNotEmpty()){
                        num1=prefix+num1
                    }
                }
                else if (number.contains("*")){
                    var split=number.split("*")
                    var num1=split[0]
                    var num2=split[1]
                    input?.text=(num1.toDouble()*num2.toDouble()).toString()

                    if (prefix.isNotEmpty()){
                        num1=prefix+num1
                    }
                }

                else if(number.contains("/")){
                        var split=number.split("/")
                        var num1=split[0]
                        var num2=split[1]
                        input?.text=(num1.toDouble()/num2.toDouble()).toString()

                        if (prefix.isNotEmpty()){
                            num1=prefix+num1
                        }
                    }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}