package a248.kotlinoid.model

import android.arch.lifecycle.LiveData

object ItemRepository {

    var itemDao: ItemDao? = null

    fun checkDao(): ItemDao {
        return itemDao ?: throw NullPointerException("==Set Dao to ItemRepository")
    }

    fun getItems(): LiveData<List<ItemEntity>>? {
        return checkDao().getAllAsLiveData()
    }

    fun addItems(vararg items: ItemEntity) {
        val nonNullDao = checkDao()
        unitResultTask({}, {nonNullDao.insertAll(*items)}, {})
    }

    fun deleteItem(item: ItemEntity) {
        val nonNullDao = checkDao()
        unitResultTask({}, {nonNullDao.delete(item)}, {})
    }

}