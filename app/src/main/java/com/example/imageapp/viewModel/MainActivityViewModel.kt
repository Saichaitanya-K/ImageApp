package com.example.imageapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imageapp.extensions.toItemModel
import com.example.imageapp.model.ItemModel
import com.example.imageapp.network.NetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    var itemModelList = mutableStateListOf<ItemModel?>()
    var showDialog by mutableStateOf(false)
    var isRefreshing by mutableStateOf(false)
    var showErrorMessage by mutableStateOf(false)

    /**
     * Fetches the data when viewModel is initialised
     */
    fun fetchData(f: () -> Unit) {
        isRefreshing = true
        viewModelScope.launch(Dispatchers.IO) {
            // fetch the data
            NetworkManager.getInstance().getImageRepository().getImageInfo().collectLatest {
                isRefreshing = false
                if (it.isEmpty()) {
                    f.invoke()
                    showErrorMessage = true
                } else {
                    showErrorMessage = false
                    itemModelList.clear()
                    itemModelList.addAll(it.toItemModel())
                }
            }
        }
    }
}