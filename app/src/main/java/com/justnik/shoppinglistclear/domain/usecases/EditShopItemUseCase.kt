package com.justnik.shoppinglistclear.domain.usecases

import com.justnik.shoppinglistclear.domain.ShopItem
import com.justnik.shoppinglistclear.domain.ShopListRepository

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopItem(shopItem: ShopItem){
        shopListRepository.editShopItem(shopItem)
    }
}