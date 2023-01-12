package com.blblblbl.myapplication.data.data_classes.responses.subreddit

import com.google.gson.annotations.SerializedName


data class CommentContributionSettings (
    @SerializedName("allowed_media_types") var allowedMediaTypes               : ArrayList<String>            = arrayListOf()
)