package com.olisemeka.shoppinglistapp.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.olisemeka.shoppinglistapp.database.ShoppingItem
import com.olisemeka.shoppinglistapp.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository): ViewModel() {
    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch{
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    val allShoppingItems = repository.getAllShoppingItems()
}