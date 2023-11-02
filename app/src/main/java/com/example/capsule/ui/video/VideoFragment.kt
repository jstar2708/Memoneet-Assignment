package com.example.capsule.ui.video

import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.capsule.R
import com.example.capsule.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    private lateinit var binding: FragmentVideoBinding
    private lateinit var viewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[VideoViewModel::class.java]

        val path =
            Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.video)

        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(binding.videoView)
        mediaController.setMediaPlayer(binding.videoView)
        binding.videoView.setMediaController(mediaController)
        binding.videoView.setVideoURI(path)
        binding.videoView.seekTo(viewModel.currentProgress)
        binding.videoView.start()

        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                viewModel.currentProgress = binding.videoView.currentPosition
                handler.postDelayed(this, 1000)
            }
        }
        )
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = VideoFragment()
    }
}