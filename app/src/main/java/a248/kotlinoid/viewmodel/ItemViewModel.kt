package a248.kotlinoid.viewmodel

import a248.kotlinoid.model.ItemDao
import a248.kotlinoid.model.ItemEntity
import a248.kotlinoid.model.ItemRepository
import a248.kotlinoid.model.getDb
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log

class ItemViewModel : ViewModel() {

    var items: LiveData<List<ItemEntity>>? = null
    private var itemRepository = ItemRepository

    fun init(context: Context) {
        if (itemRepository.itemDao == null) {
            itemRepository.itemDao = context.getDb().itemDao()
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