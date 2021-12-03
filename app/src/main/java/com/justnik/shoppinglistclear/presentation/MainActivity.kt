package com.justnik.shoppinglistclear.presentation

import android.media.MediaRouter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.justnik.shoppinglistclear.R
import com.justnik.shoppinglistclear.domain.ShopItem
import com.justnik.shoppinglistclear.presentation.adapters.ShopListAdapter
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivityTest"
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var rvShopList: RecyclerView
    private lateinit var rvAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.shopList.observe(this){
            rvAdapter.submitList(it)
        }

    }

    private fun setUpRecyclerView(){
        rvShopList = findViewById(R.id.rv_shop_list)
        rvShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_ENABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        rvShopList.recycledViewPool.setMaxRecycledViews(
            ShopListAdapter.VIEW_TYPE_DISABLED,
            ShopListAdapter.MAX_POOL_SIZE
        )
        rvAdapter = ShopListAdapter()
        rvShopList.adapter = rvAdapter

        setUpLongClickListener()

        setUpClickListener()

        setUpSwipeListener()
    }

    private fun setUpSwipeListener() {
        val shopItemDeleteCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = rvAdapter.currentList[viewHolder.adapterPosition]
                mainViewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(shopItemDeleteCallback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    private fun setUpClickListener() {
        rvAdapter.onShopItemClickListener = { shopItem ->
            Log.d(TAG, "onCreate: $shopItem")
        }
    }

    private fun setUpLongClickListener() {
        rvAdapter.onShopItemLongClickListener = { shopItem ->
            mainViewModel.changeEnableState(shopItem)
        }
    }

}