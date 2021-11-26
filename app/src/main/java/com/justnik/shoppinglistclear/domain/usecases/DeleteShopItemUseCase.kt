package com.justnik.shoppinglistclear.domain.usecases

import com.justnik.shoppinglistclear.domain.ShopItem
import com.justnik.shoppinglistclear.domain.ShopListRepository

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopItem(shopItem: ShopItem){
        shopListRepository.deleteShopItem(shopItem)
    }
}