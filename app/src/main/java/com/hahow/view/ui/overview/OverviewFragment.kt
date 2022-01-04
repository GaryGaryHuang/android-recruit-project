package com.hahow.view.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.hahow.android_recruit_project.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment() {
    private lateinit var binding: FragmentOverviewBinding
    private lateinit var overviewViewModel: OverviewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        overviewViewModel = ViewModelProvider(this)[OverviewViewModel::class.java]
        return binding.root
    }
}