package com.justnik.shoppinglistclear.domain

data class ShopItem(
    var name: String,
    var count: String,
    val id: Int,
    var enabled: Boolean
)
