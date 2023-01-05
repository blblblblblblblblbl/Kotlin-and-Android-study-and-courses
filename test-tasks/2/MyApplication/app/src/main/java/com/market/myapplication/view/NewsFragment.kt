package com.market.myapplication.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.market.myapplication.R
import com.market.myapplication.data.data_classes.api.Articles
import com.market.myapplication.viewModel.NewsFragmentViewModel
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow


@AndroidEntryPoint
class NewsFragment : Fragment() {
    private val viewModel:NewsFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                NewsList(news = viewModel.pagedNews)
            }
        }
    }
    @Composable
    fun NewsList(news: Flow<PagingData<Articles>>) {
        val lazyNewsItems: LazyPagingItems<Articles> = news.collectAsLazyPagingItems()
        LazyColumn{
            items(lazyNewsItems){item->
                if (item != null) {
                    NewsItem(articles = item)
                }
            }
        }
        lazyNewsItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingView(modifier = Modifier.fillMaxSize())
                }
                loadState.append is LoadState.Loading -> {
                    LoadingItem()
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyNewsItems.loadState.refresh as LoadState.Error

                    ErrorItem(
                        message = e.error.localizedMessage!!,
                        modifier = Modifier.fillMaxSize(),
                        onClickRetry = { retry() }
                    )

                }
                loadState.append is LoadState.Error -> {
                    val e = lazyNewsItems.loadState.append as LoadState.Error

                    ErrorItem(
                        message = e.error.localizedMessage!!,
                        onClickRetry = { retry() }
                    )

                }
            }
        }
    }
    @Composable
    fun NewsItem(articles: Articles){
        val textSizeTitle = 20.sp
        val textSizeDescription = 15.sp
        Surface(modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black)
            //.height(IntrinsicSize.Max)
            .padding(10.dp)
            .clickable {
               /* val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(articles.url))
                startActivity(browserIntent)*/
                val bundle = bundleOf()
                bundle.putParcelable(ArticleDetailedFragment.ARTICLE_KEY,articles)
                findNavController().navigate(R.id.action_newsFragment_to_articleDetailedFragment,bundle)
            })
        {
            Column() {
                Text(text = articles.title.toString(), fontSize = textSizeTitle)
                GlideImage(imageModel = { articles.urlToImage },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                Text(text = articles.description.toString(), fontSize = textSizeDescription)
            }
        }
    }
    @Composable
    fun LoadingView(
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun LoadingItem() {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }

    @Composable
    fun ErrorItem(
        message: String,
        modifier: Modifier = Modifier,
        onClickRetry: () -> Unit
    ) {
        Row(
            modifier = modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message,
                maxLines = 1,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Red
            )
            OutlinedButton(onClick = onClickRetry) {
                Text(text = "Try again")
            }
        }
    }
}