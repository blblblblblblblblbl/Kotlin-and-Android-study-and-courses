package com.market.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.market.myapplication.R
import com.market.myapplication.data.data_classes.api.Articles
import com.skydoves.landscapist.glide.GlideImage


class ArticleDetailedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var article:Articles? = null
        arguments?.let { article = it.getParcelable(ARTICLE_KEY)}
        return ComposeView(requireContext()).apply {
            setContent {
                article?.let {article ->
                    NewsItem(articles = article)
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
            .height(IntrinsicSize.Max)
            .padding(10.dp)
            )
        {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Text(text = articles.title.toString(), fontSize = textSizeTitle)
                //mock
                var resourceId = resources.getIdentifier(articles.urlToImage , "drawable", requireContext().packageName);
                GlideImage(imageModel = { resourceId },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp))
                Text(text = articles.content.toString(), fontSize = textSizeDescription)
            }
        }
    }

    companion object{
        const val ARTICLE_KEY :String = "articleKey"
    }
}