package a248.kotlinoid.viewmodel

import a248.kotlinoid.model.ItemDao
import a248.kotlinoid.model.ItemEntity
import a248.kotlinoid.model.ItemRepository
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log

class ItemViewModel : ViewModel() {

    var items: LiveData<List<ItemEntity>>? = null
    private var itemRepository = ItemRepository

    fun init(itemDao: ItemDao) {
        Log.d("==ViewModel", "ViewModel init")
        if (itemRepository.itemDao == null) {
            itemRepository.itemDao = itemDao
        }
        if (items == null) {
            items = itemRepository.getItems()
        }
    }

    fun addItem(item: ItemEntity) {
        itemRepository.addItems(item)
    }

    fun deleteItem(item: ItemEntity) {
        itemRepository.deleteItem(item)
    }

}