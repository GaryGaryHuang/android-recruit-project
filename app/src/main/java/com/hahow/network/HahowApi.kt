package com.hahow.network

import com.hahow.network.service.FakeHahowServiceImplement
import com.hahow.network.service.HahowService

class HahowApi private constructor(){
    private var _hahowService: HahowService = FakeHahowServiceImplement()
    val hahowService: HahowService
        get() = _hahowService

    companion object {
        private var instance: HahowApi? = null
        fun getInstance(): HahowApi = instance ?: HahowApi().also {
            instance = it
        }
    }

    fun setFakeHahowService(service: HahowService) {
        this._hahowService = service
    }
}
