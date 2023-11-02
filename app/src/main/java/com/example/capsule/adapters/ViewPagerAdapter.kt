package com.example.capsule.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.capsule.ui.notes.NotesFragment
import com.example.capsule.ui.quiz.QuizFragment
import com.example.capsule.ui.video.VideoFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    private val NUM_TABS = 3

    override fun getItemCount(): Int {
        return NUM_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> VideoFragment.newInstance()
            1 -> NotesFragment.newInstance()
            else -> QuizFragment.newInstance()
        }
    }
}