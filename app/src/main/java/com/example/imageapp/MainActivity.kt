package com.example.imageapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import com.example.imageapp.dialogBox.DialogContent
import com.example.imageapp.uiContent.ItemModelListContent
import com.example.imageapp.ui.theme.ImageAppTheme
import com.example.imageapp.viewModel.MainActivityViewModel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

class MainActivity : ComponentActivity() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var textToShowOnDialog: String
    lateinit var context:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        viewModel.fetchData(::showToastMessage)
        setContent {
            context = LocalContext.current
            val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = viewModel.isRefreshing)
            // Shows the dialog when the condition is true
            if (viewModel.showDialog)
                ShowDialog()
            ImageAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ItemModelListContent(
                        itemModelList = viewModel.itemModelList,
                        swipeRefreshState = swipeRefreshState,
                        application = this.application,
                        showErrorMessage = viewModel.showErrorMessage,
                        onRefresh = { viewModel.fetchData(::showToastMessage) }
                    ){
                        textToShowOnDialog = it
                        viewModel.showDialog = true
                    }
                }
            }
        }
    }

    /**
     * Shows the dialog
     */
    @Composable
    fun ShowDialog(){
        DialogContent(
            textMessage = textToShowOnDialog,
            onButtonClicked = { viewModel.showDialog = false },
            onDismissRequest = { viewModel.showDialog = false }
        )
    }

    /**
     * Shows the toast message when invoked
     */
    private fun showToastMessage(){
        runOnUiThread {
            val toast = Toast.makeText(context, "Network Error. Please Try Again", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}