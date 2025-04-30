package com.yourdomain.tasklist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView                    // ← import Lottie
import com.yourdomain.tasklist.databinding.ActivityMainBinding
import com.yourdomain.tasklist.ui.communicator.FragmentCommunicator

class MainActivity : AppCompatActivity(), FragmentCommunicator {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun showLoader() {
        binding.loaderLayout.visibility = View.VISIBLE
        binding.loaderAnimation.playAnimation()
    }

    override fun hideLoader() {
        binding.loaderAnimation.pauseAnimation()
        binding.loaderLayout.visibility = View.GONE
    }
}

