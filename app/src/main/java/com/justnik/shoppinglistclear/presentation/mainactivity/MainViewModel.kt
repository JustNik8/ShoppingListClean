package com.justnik.shoppinglistclear.presentation.mainactivity

import androidx.lifecycle.ViewModel
import com.justnik.shoppinglistclear.data.ShopListRepositoryImpl
import com.justnik.shoppinglistclear.domain.ShopItem
import com.justnik.shoppinglistclear.domain.usecases.DeleteShopItemUseCase
import com.justnik.shoppinglistclear.domain.usecases.EditShopItemUseCase
import com.justnik.shoppinglistclear.domain.usecases.GetShopListUseCase

class MainViewModel : ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem){
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }


    fun changeEnableState(shopItem: ShopItem){
        val newShopItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newShopItem)
    }

}