package com.blblblbl.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.blblblbl.myapplication.R
import com.blblblbl.myapplication.viewModel.CollectionsFragmentViewModel
import com.example.example.PhotoCollection
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class CollectionsFragment : Fragment() {
    private val viewModel:CollectionsFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*lifecycleScope.launch{
            viewModel.getCollections(1)
        }*/
        return ComposeView(requireContext()).apply {
            setContent {
                PhotoCollectionsList(photoCollections = viewModel.pagedCollections)
            }
        }
    }
    @Composable
    fun PhotoCollectionsList(photoCollections: Flow<PagingData<PhotoCollection>>) {
        val lazyPhotosItems: LazyPagingItems<PhotoCollection> = photoCollections.collectAsLazyPagingItems()
        LazyColumn{
            items(lazyPhotosItems){item->
                if (item != null) {
                    Surface(modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable {
                            val bundle =  bundleOf()
                            bundle.putString(CollectionPhotoListFragment.COLLECTION_ID_KEY,item.id)
                            findNavController().navigate(R.id.action_collectionsFragment_to_collectionPhotoListFragment,bundle)
                        }) {
                        PhotoCollectionItem(photoCollection = item)
                    }
                }
            }
        }
        lazyPhotosItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    LoadingView(modifier = Modifier.fillMaxSize())
                }
                loadState.append is LoadState.Loading -> {
                    LoadingItem()
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyPhotosItems.loadState.refresh as LoadState.Error

                    ErrorItem(
                        message = e.error.localizedMessage!!,
                        modifier = Modifier.fillMaxSize(),
                        onClickRetry = { retry() }
                    )

                }
                loadState.append is LoadState.Error -> {
                    val e = lazyPhotosItems.loadState.append as LoadState.Error

                    ErrorItem(
                        message = e.error.localizedMessage!!,
                        onClickRetry = { retry() }
                    )

                }
            }
        }
    }
    @Preview
    @Composable
    fun ItemPreview(){
        var pc = PhotoCollection()
        pc.coverPhoto?.urls?.small = "https://images.unsplash.com/photo-1449614115178-cb924f730780?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&w=400&fit=max"
        pc.totalPhotos = 200
        pc.title = "Title"
        pc.user?.name = "firstname lastname"
        pc.user?.username = "username"
        pc.user?.profileImage?.large = "https://images.unsplash.com/profile-1450003783594-db47c765cea3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&fit=crop&h=128&w=128"
        PhotoCollectionItem(pc)
    }
    @Composable
    fun PhotoCollectionItem(photoCollection: PhotoCollection, modifier: Modifier = Modifier){
        val imageUrl:String? = photoCollection.coverPhoto?.urls?.small
        com.skydoves.landscapist.glide.GlideImage(imageModel = { imageUrl }, modifier = Modifier.fillMaxSize())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(start = 5.dp)) {
            val textColor = Color.White
            val textSizeTitle = 30.sp
            val textSizeTotalPhotos = 20.sp
            val textSizeName = 15.sp
            val textSizeUserName = 10.sp
            Text(text = "${photoCollection.totalPhotos} ${stringResource(id = R.string.collection_total_photos)}", color =textColor, fontSize = textSizeTotalPhotos)
            Text(text = "${photoCollection.title}", color = textColor, fontSize = textSizeTitle, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                val avatar:String? = photoCollection.user?.profileImage?.large
                GlideImage(imageModel = {avatar}, modifier = Modifier.clip(CircleShape))
                Column(Modifier.padding(start = 5.dp)) {
                    Text(text = "${photoCollection.user?.name}", color = textColor, fontSize = textSizeName)
                    Text(text = "@${photoCollection.user?.username}", color = textColor, fontSize = textSizeUserName)
                }
            }

        }

    }
    /*@Composable
    fun CustomStaggeredVerticalGrid(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit,
        numRows:Int
    ) {
        Layout(
            content = content,
            modifier = modifier
        ) { measurable, constraints ->
            // on below line we are creating a variable for our column width.
            val columnWidth = constraints.maxWidth
            val rowHeight = constraints.maxHeight/numRows

            // on the below line we are creating and initializing our items constraint widget.
            val itemConstraints = constraints.copy(maxWidth = columnWidth, maxHeight = rowHeight)

            // on below line we are creating and initializing our column height
            val columnHeights = IntArray(numColumns) { 0 }

            // on below line we are creating and initializing placebles
            val placeables = measurable.map { measurable ->
                // inside placeble we are creating
                // variables as column and placebles.
                val column = testColumn(columnHeights)
                val placeable = measurable.measure(itemConstraints)

                // on below line we are increasing our column height/
                columnHeights[column] += placeable.height
                placeable
            }

            // on below line we are creating a variable for
            // our height and specifying height for it.
            val height =
                columnHeights.maxOrNull()?.coerceIn(constraints.minHeight, constraints.maxHeight)
                    ?: constraints.minHeight

            // on below line we are specifying height and width for our layout.
            layout(
                width = constraints.maxWidth,
                height = height
            ) {
                // on below line we are creating a variable for column y pointer.
                val columnYPointers = IntArray(numColumns) { 0 }

                // on below line we are setting x and y for each placeable item
                placeables.forEach { placeable ->
                    // on below line we are calling test
                    // column method to get our column index
                    val column = testColumn(columnYPointers)

                    placeable.place(
                        x = columnWidth * column,
                        y = columnYPointers[column]
                    )

                    // on below line we are setting
                    // column y pointer and incrementing it.
                    columnYPointers[column] += placeable.height
                }
            }
        }
    }*/
    
    
    
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