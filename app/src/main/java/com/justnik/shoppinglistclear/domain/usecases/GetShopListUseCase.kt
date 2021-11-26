package com.justnik.shoppinglistclear.domain.usecases

import com.justnik.shoppinglistclear.domain.ShopItem
import com.justnik.shoppinglistclear.domain.ShopListRepository

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): List<ShopItem>{
        return shopListRepository.getShopList()
    }
}