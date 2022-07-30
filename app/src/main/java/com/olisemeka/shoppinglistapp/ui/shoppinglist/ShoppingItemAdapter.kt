package com.olisemeka.shoppinglistapp.ui.shoppinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.olisemeka.shoppinglistapp.R
import com.olisemeka.shoppinglistapp.database.ShoppingItem

class ShoppingItemAdapter(var itemList: List<ShoppingItem>, val viewModel: ShoppingViewModel):
    RecyclerView.Adapter<ShoppingItemAdapter.ItemHolder>() {
    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val ivDelete: ImageView = itemView.findViewById(R.id.ivDelete)
        val ivMinus: ImageView = itemView.findViewById(R.id.ivMinus)
        val ivPlus: ImageView = itemView.findViewById(R.id.ivPlus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.shopping_item, parent, false)
        return ItemHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = itemList[position]
        holder.tvName.text = item.name
        holder.tvAmount.text = item.amount.toString()
        holder.ivDelete.setOnClickListener { viewModel.delete(item) }
        holder.ivPlus.setOnClickListener {
            item.amount++
            viewModel.upsert(item) }
        holder.ivMinus.setOnClickListener {
            if (item.amount > 0)
                item.amount--
                viewModel.upsert(item)
        }
    }

    override fun getItemCount() = itemList.size
}