package com.olisemeka.shoppinglistapp.repositories

import com.olisemeka.shoppinglistapp.database.ShoppingDatabase
import com.olisemeka.shoppinglistapp.database.ShoppingItem

class ShoppingRepository(private val db: ShoppingDatabase){
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}