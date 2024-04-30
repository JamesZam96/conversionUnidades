package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora.databinding.ActivityMainBinding
import java.lang.StringBuilder
import kotlin.math.pow

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var convertNumber:Double = 0.0
    private var unit: String = " "

    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        /*setContentView(R.layout.activity_main)*/
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.numberOne.setOnClickListener(this)
        binding.numberTwo.setOnClickListener(this)
        binding.numberThree.setOnClickListener(this)
        binding.numberFour.setOnClickListener(this)
        binding.numberFive.setOnClickListener(this)
        binding.numberSix.setOnClickListener(this)
        binding.numberSeven.setOnClickListener(this)
        binding.numberEight.setOnClickListener(this)
        binding.numberNine.setOnClickListener(this)
        binding.numberZero.setOnClickListener(this)
        binding.kilometer.setOnClickListener(this)
        binding.hectometer.setOnClickListener(this)
        binding.decameter.setOnClickListener(this)
        binding.decimeter.setOnClickListener(this)
        binding.centimeter.setOnClickListener(this)
        binding.millimeter.setOnClickListener(this)
        binding.deleteAll.setOnClickListener(this)
    }

    override fun onClick(view:View) {
        when (view.id) {
            R.id.numberOne -> assignNumber("1")
            R.id.numberTwo -> assignNumber("2")
            R.id.numberThree -> assignNumber("3")
            R.id.numberFour -> assignNumber("4")
            R.id.numberFive -> assignNumber("5")
            R.id.numberSix -> assignNumber("6")
            R.id.numberSeven -> assignNumber("7")
            R.id.numberEight -> assignNumber("8")
            R.id.numberNine -> assignNumber("9")
            R.id.numberZero -> assignNumber("0")
            R.id.kilometer -> assignUnit("km")
            R.id.hectometer -> assignUnit("hm")
            R.id.decameter -> assignUnit("dam")
            R.id.decimeter -> assignUnit("dm")
            R.id.centimeter -> assignUnit("cm")
            R.id.millimeter -> assignUnit("mm")
            R.id.deleteAll -> deleteAll()
        }
    }
    private fun assignNumber(number: String) {
        var numberTwo = binding.editTextResult.text.toString()
        var newNumber = numberTwo + number
        binding.editTextResult.setText(newNumber)
    }

    private fun assignUnit(u: String) {
        if (binding.editTextResult.text.toString().isNotEmpty()) {
            convertNumber = binding.editTextResult.text.toString().toDouble()
            unit = u
            /*binding.editTextResult.setText("")*/
            deleteAll()
            var result: Double = when (unit) {
                "km" -> convertNumber/1000
                "hm" -> convertNumber/100
                "dam" -> convertNumber/10
                "dm" -> convertNumber*10
                "cm" -> convertNumber*100
                "mm" -> convertNumber*1000
                else -> 0.0
            }
            binding.editTextResult.setText(result.toString())
            convertNumber = 0.0
        }else{
            unit = " "
            var errorMessage = "Por favor ingrese un número antes de seleccionar la unidad de conversion"
            Toast.makeText(this,errorMessage,Toast.LENGTH_SHORT).show()
        }

    }

    private fun deleteAll(){
        binding.editTextResult.setText("")
    }
}

