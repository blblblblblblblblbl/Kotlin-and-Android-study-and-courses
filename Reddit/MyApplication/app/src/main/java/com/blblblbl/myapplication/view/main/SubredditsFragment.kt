package com.blblblbl.myapplication.view.main

import android.os.Bundle
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
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.LightGray
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
import com.blblblbl.myapplication.viewModel.SubredditsFragmentViewModel
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class SubredditsFragment : Fragment() {
    private val viewModel:SubredditsFragmentViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.loadNew()
        return ComposeView(requireContext()).apply {
            setContent {
                val mCheckedState = remember{ mutableStateOf(false)}
                Scaffold(
                    topBar = {
                        SearchTopBar(
                            onSearchClicked = {
                                /*findNavController().navigate(
                                    R.id.action_subredditsFragment_to_searchFragment
                                )*/
                            }
                        )
                    },
                    content = {
                        Surface(modifier = Modifier
                            .padding(top = it.calculateTopPadding())
                            .fillMaxWidth()) {
                            Column(horizontalAlignment = CenterHorizontally) {
                                Row() {
                                    Text(text = "new")
                                    Switch(checked = mCheckedState.value, onCheckedChange = {
                                        mCheckedState.value = it
                                        if(it){viewModel.loadPopular()}
                                        else viewModel.loadNew()
                                    })
                                    Text(text = "popular")
                                }
                                PostsList(posts = viewModel.pagedPosts)
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
                    text = "search posts",
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
    fun PostsList(posts: StateFlow<Flow<PagingData<Post>>?>){
        val postsState = posts.collectAsState().value
        postsState?.let { postsState->
            val lazyPostItems: LazyPagingItems<Post> = postsState.collectAsLazyPagingItems()
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
    fun PostScreen(post: Post) {
        val textSizeCommon = 20.sp
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Row(modifier = Modifier) {
                    post?.data?.title?.let {title->
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
                post?.data?.url?.let {imgUrl->
                    GlideImage(imageModel = {imgUrl}, Modifier.requiredHeightIn(0.dp,200.dp),)
                }
                Row() {
                    post?.data?.author?.let { author->
                        Text(text = author, fontSize = textSizeCommon,modifier = Modifier.weight(1f))
                    }
                    post?.data?.numComments?.let { numComments->
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
}