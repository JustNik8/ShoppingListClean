package com.justnik.shoppinglistclear.domain.usecases

import com.justnik.shoppinglistclear.domain.ShopItem
import com.justnik.shoppinglistclear.domain.ShopListRepository

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopItem(id: Int): ShopItem {
        return shopListRepository.getShopItem(id)
    }
}