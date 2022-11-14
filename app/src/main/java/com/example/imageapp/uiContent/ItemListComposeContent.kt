package com.example.imageapp.uiContent

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.imageapp.R
import com.example.imageapp.imageLoading.loadImages
import com.example.imageapp.model.ItemModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Composable shows the list of items
 */
@Composable
fun ItemModelListContent(
    itemModelList: List<ItemModel?>,
    swipeRefreshState: SwipeRefreshState,
    application: Application,
    showErrorMessage: Boolean,
    onRefresh: () -> Unit,
    onItemClicked: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                onRefresh.invoke()
            }
        ) {
            // When the list is empty shows the loader
            if (itemModelList.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // shows the error message on network failure
                    if (showErrorMessage){
                        Text(
                            text = stringResource(id = R.string.error_message),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
            // Shows the list of models
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    ),
                state = listState
            ) {
                itemsIndexed(
                    itemModelList,
                    key = { _, item -> item?.id ?: 1 }
                ) { _, item ->
                    item?.let { model ->
                        if (item.bitmap.value == null)
                            coroutineScope.launch(Dispatchers.IO) {
                                model.bitmap.value =
                                    model.imageUrl?.let { loadImages(it, application) }
                            }
                        ItemContent(itemModel = model) {
                            onItemClicked.invoke(it)
                        }
                    }
                }
            }
        }
    }
}