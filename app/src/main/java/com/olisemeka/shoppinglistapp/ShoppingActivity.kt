package com.olisemeka.shoppinglistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.olisemeka.shoppinglistapp.database.ShoppingDatabase
import com.olisemeka.shoppinglistapp.database.ShoppingItem
import com.olisemeka.shoppinglistapp.databinding.ActivityShoppingBinding
import com.olisemeka.shoppinglistapp.repositories.ShoppingRepository
import com.olisemeka.shoppinglistapp.ui.shoppinglist.*

class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding
    val database by lazy {ShoppingDatabase(this)}
    val repository by lazy { ShoppingRepository(database) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val viewModel: ShoppingViewModel by viewModels {
            ShoppingViewModelFactory(repository)
        }

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        binding.rvShoppingItems.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingItems.adapter = adapter

        viewModel.allShoppingItems.observe(this, Observer{
            adapter.itemList = it
            adapter.notifyDataSetChanged()
        })

        binding.fab.setOnClickListener{
            AddShoppingItemDialog(this,
            object: AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }


    }
}