package com.blblblbl.myapplication.data.data_classes.responses.posts.comments

import com.blblblbl.myapplication.data.data_classes.responses.posts.SubredditPostsResponse
import com.google.gson.annotations.SerializedName

class PostCommentsResponse(
    val subredditPostsResponse: SubredditPostsResponse,
    val commentsResponse: CommentsResponse
)