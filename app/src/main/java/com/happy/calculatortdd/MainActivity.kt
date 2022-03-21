package com.happy.calculatortdd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.happy.calculatortdd.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId: Int = R.layout.activity_main

    private val calculator = Calculator()

    override fun initUi() {
        binding.calculator = calculator
    }
}