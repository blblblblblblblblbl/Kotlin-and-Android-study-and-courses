package com.blblblbl.myapplication.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.blblblbl.myapplication.R
import com.blblblbl.myapplication.databinding.FragmentLocationsBinding
import com.blblblbl.myapplication.presentation.LocationsFragmentViewModel
import com.blblblbl.myapplication.presentation.MainViewModel
import com.example.example.ResultsLocation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class LocationsFragment : Fragment() {
    private val viewModel: LocationsFragmentViewModel by viewModels()
   lateinit var binding: FragmentLocationsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                LocationsList(locations = viewModel.pagedLocations)
            }
        }
    }
    @Composable
    fun LocationsList(locations: Flow<PagingData<ResultsLocation>>) {
        val lazyLocationsItems: LazyPagingItems<ResultsLocation> = locations.collectAsLazyPagingItems()
        LazyColumn{
            items(lazyLocationsItems){item->
                if (item != null) {
                    LocationItem(location = item)
                }
            }
        }
        lazyLocationsItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingView(modifier = Modifier.fillMaxSize())
                }
                loadState.append is LoadState.Loading -> {
                    LoadingItem()
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyLocationsItems.loadState.refresh as LoadState.Error

                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillMaxSize(),
                            onClickRetry = { retry() }
                        )

                }
                loadState.append is LoadState.Error -> {
                    val e = lazyLocationsItems.loadState.append as LoadState.Error

                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )

                }
            }
        }
    }
    @Composable
    fun LocationItem(location:ResultsLocation){
        Column {
            Card(modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight()) {
                Text(text = "name: ${location.name}")
                Text(text = "type: ${location.type}")
                Text(text = "dimension: ${location.dimension}")
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
            modifier = Modifier.fillMaxWidth()
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

