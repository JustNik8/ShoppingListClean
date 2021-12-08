package com.justnik.shoppinglistclear.presentation.shopitemactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.justnik.shoppinglistclear.data.ShopListRepositoryImpl
import com.justnik.shoppinglistclear.domain.ShopItem
import com.justnik.shoppinglistclear.domain.usecases.AddShopItemUseCase
import com.justnik.shoppinglistclear.domain.usecases.EditShopItemUseCase
import com.justnik.shoppinglistclear.domain.usecases.GetShopItemUseCase

class ShopItemViewModel : ViewModel() {

    companion object {
        const val UNDEFINED_COUNT = -1
    }

    private val repository = ShopListRepositoryImpl
    private val getShopItemUseCase = GetShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val addShopItemUseCase = AddShopItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>(false)
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>(false)
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreen: LiveData<Unit>
        get() = _closeScreen

    fun getShopItem(shopItemId: Int) {
        val item = getShopItemUseCase.getShopItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsAreCorrect = validateInput(name, count)

        if (fieldsAreCorrect) {
            val shopItem = ShopItem(name, count, true)
            addShopItemUseCase.addShopItem(shopItem)

            finishWork()
        }

    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsAreCorrect = validateInput(name, count)

        if (fieldsAreCorrect) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopItemUseCase.editShopItem(item)

                finishWork()
            }
        }

    }

    private fun parseName(name: String?): String {
        return name?.trim() ?: ""
    }


    private fun parseCount(count: String?): Int {
        val num = try {
            count?.trim()?.toInt()
        } catch (numberFormatException: NumberFormatException) {
            UNDEFINED_COUNT
        }
        return num ?: UNDEFINED_COUNT
    }

    private fun validateInput(inputName: String, inputCount: Int): Boolean {
        var result = true
        if (inputName.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (inputCount == UNDEFINED_COUNT) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork(){
        _closeScreen.value = Unit
    }

}