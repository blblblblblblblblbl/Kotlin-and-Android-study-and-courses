package com.blblblbl.myapplication.data.persistent_storage.utils

import com.blblblbl.myapplication.data.data_classes.user_info.UserInfo
import com.blblblbl.myapplication.data.repository.database.utils.GsonParser
import com.blblblbl.myapplication.data.repository.database.utils.JsonParser
import com.google.gson.GsonBuilder

object StorageConverter {
    private val gson = GsonBuilder().setLenient().create()
    private val jsonParser: JsonParser = GsonParser(gson)
    fun userInfoToJson(userInfo: UserInfo) : String? {
        return jsonParser.toJson(
            userInfo,
            UserInfo::class.java
        )
    }
    fun userInfoFromJson(json: String): UserInfo? {
        return jsonParser.fromJson<UserInfo>(
            json,
            UserInfo::class.java
        )
    }
}