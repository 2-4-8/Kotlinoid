package a248.kotlinoid.viewmodel

import a248.kotlinoid.model.ItemDao
import a248.kotlinoid.model.ItemEntity
import a248.kotlinoid.model.ItemRepository
import a248.kotlinoid.model.getDb
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Single

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

    fun getItem(uuid: Int): Flowable<ItemEntity> {
        var result: ItemEntity? = null
        items?.value?.forEach { if (it.uuid == uuid) result = it }
        if (result != null) return Flowable.just(result)
        return itemRepository.getItemById(uuid)
    }

    fun addItem(item: ItemEntity) {
        itemRepository.addItems(item)
    }

    fun deleteItem(item: ItemEntity) {
        itemRepository.deleteItem(item)
    }

}