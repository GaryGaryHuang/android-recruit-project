package com.hahow.view.ui.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hahow.android_recruit_project.databinding.FragmentOverviewBinding
import com.hahow.view.adapter.CourseAdapter

class OverviewFragment : Fragment() {
    private lateinit var binding: FragmentOverviewBinding
    private lateinit var overviewViewModel: OverviewViewModel
    private lateinit var mCourseAdapter: CourseAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        overviewViewModel = ViewModelProvider(this)[OverviewViewModel::class.java]
        setListeners()
        initValues()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        overviewViewModel.isLoading.observe(viewLifecycleOwner, {
            binding.swipeLayout.isRefreshing = it
        })

        overviewViewModel.message.observe(viewLifecycleOwner, {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
        })

        overviewViewModel.courseList.observe(viewLifecycleOwner, {
            mCourseAdapter.submitList(it)
        })
    }

    private fun setListeners() {
        binding.swipeLayout.setOnRefreshListener {
            overviewViewModel.getCourse()
        }
    }

    private fun initValues() {
        mCourseAdapter = CourseAdapter()
        binding.rv.also {
            it.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            it.adapter = mCourseAdapter
        }
    }
}