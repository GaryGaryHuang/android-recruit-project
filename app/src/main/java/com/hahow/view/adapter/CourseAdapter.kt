package com.hahow.view.adapter

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hahow.android_recruit_project.R
import com.hahow.android_recruit_project.databinding.ItemCourseBinding
import com.hahow.domain.Course
import com.hahow.domain.CourseStatus
import com.hahow.util.calculateHourDifference
import java.util.*
import kotlin.math.roundToInt

class CourseAdapter : ListAdapter<Course, CourseAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemCourseBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Course) {
            val context = itemView.context

            binding.tvCourse.text = data.name

            val displayStatus: String = context.getString(when(data.status) {
                CourseStatus.Fundraising -> R.string.fundraising
                CourseStatus.Success -> R.string.fundraising_success
                CourseStatus.Published -> R.string.course_started
                else -> R.string.empty
            })
            binding.tvCourseStatus.text = displayStatus

            binding.tvTarget.text = "${data.goalPercentage.roundToInt()} %"
            binding.pbTarget.progress = data.goalPercentage.roundToInt()
            when(data.status) {
                CourseStatus.Fundraising -> {
                    binding.pbTarget.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.progress_fundraising))

                    binding.tvStudent.visibility = View.GONE
                    val hours: Int = data.proposalDueTime.run {
                        val today = Calendar.getInstance(this!!.timeZone)
                        val value = calculateHourDifference(today)
                        when {
                            before(today) -> 0
                            value > 0 -> value
                            else -> 1
                        }
                    }
                    binding.tvCountdown.text = if (hours < 24) {
                        "${context.getString(R.string.countdown)} $hours ${context.getString(R.string.hour)}"
                    } else {
                        "${context.getString(R.string.countdown)} ${hours / 24} ${context.getString(R.string.day)}"
                    }
                    binding.groupCountdown.visibility = View.VISIBLE
                    binding.groupFundraising.visibility = View.VISIBLE
                }
                CourseStatus.Success -> {
                    binding.pbTarget.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.progress_completed))

                    binding.tvStudent.visibility = View.GONE
                    binding.groupCountdown.visibility = View.GONE
                    binding.groupFundraising.visibility = View.VISIBLE
                }
                CourseStatus.Published -> {
                    binding.groupCountdown.visibility = View.GONE
                    binding.groupFundraising.visibility = View.GONE

                    binding.tvStudent.text = "${context.getString(R.string.student)} ${data.student} ${context.getString(R.string.person)}"
                    binding.tvStudent.visibility = View.VISIBLE
                }
                CourseStatus.Unknown -> {
                    binding.groupCountdown.visibility = View.GONE
                    binding.groupFundraising.visibility = View.GONE
                    binding.tvStudent.visibility = View.GONE
                }
            }

            Glide.with(itemView)
                .load(data.coverUrl)
                .into(binding.ivCourse)
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Course>() {
    override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean = oldItem.name == newItem.name
    override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean = oldItem == newItem
}
