package com.hahow.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.hahow.android_recruit_project.R
import com.hahow.android_recruit_project.databinding.ActivityNavigationBinding
import com.hahow.database.Database
import com.hahow.network.HahowApi
import com.hahow.network.data.ApiCourseResponse
import com.hahow.network.service.FakeHahowServiceImplement
import com.hahow.repository.course.CourseRepository
import com.hahow.repository.course.CourseRepositoryImplement
import com.hahow.util.loadJsonFromAsset
import com.squareup.moshi.Moshi

class NavigationActivity : AppCompatActivity(), OnActivityEvent {
    private lateinit var binding: ActivityNavigationBinding
    private lateinit var mCourseRepository: CourseRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initValues()

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_overview)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun initValues() {
        val api = HahowApi.getInstance().also { api ->
            val fakeService = FakeHahowServiceImplement().also {
                val dataString = loadJsonFromAsset("course.json") ?: "{}"
                val data: ApiCourseResponse = Moshi.Builder().build().adapter(ApiCourseResponse::class.java).fromJson(dataString) ?: ApiCourseResponse()
                it.setCourse(data)
            }
            api.setFakeHahowService(fakeService)
        }
        val networkService = api.hahowService
        val table = Database.getInstance(this).courseTable()
        mCourseRepository = CourseRepositoryImplement(networkService, table)
    }

    override fun getCourseRepository(): CourseRepository = mCourseRepository
}