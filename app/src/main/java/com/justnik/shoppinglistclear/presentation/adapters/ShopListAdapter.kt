package com.justnik.shoppinglistclear.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.justnik.shoppinglistclear.R
import com.justnik.shoppinglistclear.domain.ShopItem
import com.justnik.shoppinglistclear.presentation.ShopItemDiffCallback

class ShopListAdapter :
    ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    companion object{
        const val VIEW_TYPE_ENABLED = 0
        const val VIEW_TYPE_DISABLED = 1
        const val MAX_POOL_SIZE = 15
    }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val layoutId = when(viewType){
            VIEW_TYPE_ENABLED-> R.layout.item_shop_enabled
            VIEW_TYPE_DISABLED->R.layout.item_shop_disabled
            else -> throw RuntimeException("Unknown viewType $viewType")
        }

        val view =
            LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = getItem(position)
        val status = if (shopItem.enabled){
            "Active"
        }else{
            "Inactive"
        }
        holder.tvName.text = "${shopItem.name} is $status"
        holder.tvCount.text = shopItem.count.toString()

        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }

        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).enabled){ VIEW_TYPE_ENABLED }
        else{ VIEW_TYPE_DISABLED }
    }

}