package com.justnik.shoppinglistclear.domain

data class ShopItem(
    var name: String,
    var count: String,
    var enabled: Boolean,
    var id: Int = UNDEFINED_ID,
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
