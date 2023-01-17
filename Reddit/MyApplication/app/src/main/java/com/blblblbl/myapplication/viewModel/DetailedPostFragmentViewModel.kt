package com.blblblbl.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blblblbl.myapplication.data.data_classes.responses.friends.Friend
import com.blblblbl.myapplication.data.data_classes.responses.me.MeResponse
import com.blblblbl.myapplication.data.data_classes.responses.posts.Post
import com.blblblbl.myapplication.data.data_classes.responses.posts.PostData
import com.blblblbl.myapplication.data.data_classes.responses.posts.comments.Comment
import com.blblblbl.myapplication.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedPostFragmentViewModel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    private val _postInfo = MutableStateFlow<Post?>(null)
    val postInfo = _postInfo.asStateFlow()
    private val _comments = MutableStateFlow<List<Comment>?>(null)
    val comments = _comments.asStateFlow()
    fun getUserInfo(post:String){
        viewModelScope.launch {
            val info = repository.getPostComments(post)
            _postInfo.value = info.subredditPostsResponse.data?.posts?.first()
            val comments = info.commentsResponse.data?.children
            var allComments = mutableListOf<Comment>()
            var temp = mutableListOf<Comment>()
            comments?.let { comments ->
                temp.addAll(comments)
                //allComments.addAll(comments)
            }
            temp.forEach {
                allComments.addAll(getReplies(it))
            }
            _comments.value = allComments
        }

    }
    fun getReplies(comment:Comment):MutableList<Comment>{
        var allComments = mutableListOf<Comment>()
        allComments.add(comment)
        comment.data?.replies?.data?.children?.let { replies->
            if (replies.isNotEmpty())
            {
                replies.forEach { comment ->
                    allComments.addAll(getReplies(comment))
                }
            }
        }
        return allComments
    }
}