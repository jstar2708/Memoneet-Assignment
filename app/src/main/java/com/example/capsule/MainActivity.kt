package com.example.capsule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.capsule.adapters.ViewPagerAdapter
import com.example.capsule.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val tabNames = arrayOf("Video", "Notes", "Quiz")
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[MainViewModel::class.java]

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            tab.text = tabNames[position]
        }.attach()

        val countDownTimer = object : CountDownTimer(viewModel.currentTime, 1000L){
            override fun onTick(p0: Long) {
                viewModel.currentTime = p0
                binding.time.text = "${p0/(1000*60)}:${(p0 / 1000) % 60}"
            }

            override fun onFinish() {
                Toast.makeText(this@MainActivity, "Time up", Toast.LENGTH_SHORT).show()
            }

        }.start()
    }
}