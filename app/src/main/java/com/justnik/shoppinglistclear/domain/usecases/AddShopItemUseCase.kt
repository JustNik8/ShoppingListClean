package com.justnik.shoppinglistclear.domain.usecases

import com.justnik.shoppinglistclear.domain.ShopItem
import com.justnik.shoppinglistclear.domain.ShopListRepository

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopItem(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }
}