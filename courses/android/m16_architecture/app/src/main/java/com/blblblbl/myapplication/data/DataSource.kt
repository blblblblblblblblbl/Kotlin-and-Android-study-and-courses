package com.blblblbl.myapplication.data

import com.blblblbl.myapplication.entity.UsefulActivity

interface DataSource {
    suspend fun getActivity(): UsefulActivity
}