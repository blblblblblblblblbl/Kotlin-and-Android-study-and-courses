package com.blblblbl.myapplication.view.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.blblblbl.myapplication.R
import com.blblblbl.myapplication.data.data_classes.responses.posts.Post
import com.blblblbl.myapplication.data.data_classes.responses.saved.comments.SavedComment
import com.blblblbl.myapplication.data.data_classes.responses.saved.link.SavedLink
import com.blblblbl.myapplication.viewModel.FavouritesFragmentViewModel
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private val viewModel:FavouritesFragmentViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.loadPosts()
        return ComposeView(requireContext()).apply {
            setContent {
                val mCheckedState = remember{ mutableStateOf(false) }
                Scaffold(
                    topBar = {
                        SearchTopBar(
                            onSearchClicked = {
                                findNavController().navigate(
                                    R.id.action_subredditsFragment_to_searchFragment
                                )
                            }
                        )
                    },
                    content = {
                        Surface(modifier = Modifier.padding(top = it.calculateTopPadding()).fillMaxWidth()) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Row() {
                                    Text(text = "posts")
                                    Switch(checked = mCheckedState.value, onCheckedChange = {
                                        mCheckedState.value = it
                                        if(it){viewModel.loadComments()}
                                        else viewModel.loadPosts()
                                    })
                                    Text(text = "comments")
                                }
                                if(!mCheckedState.value){
                                    Log.d("MyLog","saved posts")
                                    PostsList(posts = viewModel.pagedPosts)
                                }
                                else {
                                    CommentsList(comments = viewModel.pagedComments)
                                }

                            }
                        }
                    }
                )
            }
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SearchTopBar(
        onSearchClicked: () -> Unit
    ){
        TopAppBar(
            title = {
                Text(
                    text = "search photo",
                    color = Color.White
                )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            actions = {
                IconButton(onClick = onSearchClicked) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
            }
        )
    }

    @Composable
    fun PostsList(posts: StateFlow<Flow<PagingData<SavedLink>>?>){
        val postsState = posts.collectAsState().value
        postsState?.let { postsState->
            val lazyPostItems: LazyPagingItems<SavedLink> = postsState.collectAsLazyPagingItems()
            LazyColumn{
                items(lazyPostItems){item->
                    item?.let {
                        PostScreen(post = it)
                    }
                }
            }
        }
    }
    @Composable
    fun PostScreen(post: SavedLink) {
        val textSizeCommon = 20.sp
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Row(modifier = Modifier) {
                    post?.linkData?.title?.let {title->
                        Text(text = title, fontSize = textSizeCommon,modifier = Modifier.weight(1f))
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_follow_24),
                            contentDescription = stringResource(id = R.string.follow_post_description),
                            modifier = Modifier
                                .size(36.dp)
                                .height(36.dp)
                                .width(36.dp)
                        )
                    }
                }
                post?.linkData?.url?.let {imgUrl->
                    GlideImage(imageModel = {imgUrl}, Modifier.requiredHeightIn(0.dp,200.dp),)
                }
                Row() {
                    post?.linkData?.author?.let { author->
                        Text(text = author, fontSize = textSizeCommon,modifier = Modifier.weight(1f))
                    }
                    post?.linkData?.numComments?.let { numComments->
                        Text(text = numComments.toString(), fontSize = textSizeCommon)
                    }
                    Icon(
                        Icons.Default.Comment,
                        contentDescription = stringResource(id = R.string.comments_number),
                    )
                }
            }
        }
    }
    @Composable
    fun CommentsList(comments: StateFlow<Flow<PagingData<SavedComment>>?>){
        val commentsState = comments.collectAsState().value
        commentsState?.let { commentsState->
            val lazyPostItems: LazyPagingItems<SavedComment> = commentsState.collectAsLazyPagingItems()
            LazyColumn{
                items(lazyPostItems){item->
                    item?.let {
                        CommentScreen(comment = it)
                    }
                }
            }
        }
    }
    @Composable
    fun CommentScreen(comment: SavedComment) {
        val textSizeCommon = 20.sp
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Row(modifier = Modifier) {
                    comment?.commentData?.body?.let {body->
                        Text(text = body, fontSize = textSizeCommon,modifier = Modifier.weight(1f))
                    }

                }
                Row() {
                    comment?.commentData?.author?.let { author->
                        Text(text = author, fontSize = textSizeCommon,modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }

}