package com.example.imageapp.uiContent

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.imageapp.model.ItemModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * Composable showing single item
 */
@Composable
fun ItemContent(
    itemModel: ItemModel,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .clickable { itemModel.description?.let { onClick.invoke(it) } },
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Name of the item
        Text(
            text = itemModel.title ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
        )

        //Shows image
        // if image is null shows loader
        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.4f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (itemModel.bitmap.value != null) {
                Image(
                    bitmap = itemModel.bitmap.value!!.asImageBitmap(),
                    contentDescription = itemModel.title,
                    contentScale = ContentScale.Fit
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color.Blue,
                    strokeWidth = 3.dp
                )
            }
        }
    }
}