package com.market.myapplication.data.persistent_storage.utils

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.market.myapplication.data.data_classes.custom_json.CustomJson

object StorageConverter {
    private val gson = GsonBuilder().setLenient().create()
    private val jsonParser: GsonParser = GsonParser(gson)
    fun userInfoToJson(userInfo: CustomJson) : String? {
        return jsonParser.toJson(
            userInfo,
            CustomJson::class.java
        )
    }
    fun userInfoFromJson(json: String): CustomJson? {
        return jsonParser.fromJson<CustomJson>(
            json,
            CustomJson::class.java
        )
    }
}