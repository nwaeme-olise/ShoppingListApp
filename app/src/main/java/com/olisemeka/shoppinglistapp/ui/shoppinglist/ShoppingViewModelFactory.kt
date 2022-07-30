package com.olisemeka.shoppinglistapp.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.olisemeka.shoppinglistapp.repositories.ShoppingRepository

class ShoppingViewModelFactory(private val repository: ShoppingRepository):
    ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repository) as T
    }
}