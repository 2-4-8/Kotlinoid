package a248.kotlinoid.model

import android.arch.lifecycle.LiveData
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object ItemRepository {

    var itemDao: ItemDao? = null

    fun checkDao(): ItemDao {
        return itemDao ?: throw NullPointerException("==Set Dao to ItemRepository")
    }

    fun getItems(): LiveData<List<ItemEntity>>? = checkDao().getAllAsLiveData()

    fun getItemById(uuid: Int): Flowable<ItemEntity> = checkDao().getItemById(uuid)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())

    fun addItems(vararg items: ItemEntity) {
        val nonNullDao = checkDao()
        unitResultTask({}, {nonNullDao.insertAll(*items)}, {})
    }

    fun deleteItem(item: ItemEntity) {
        val nonNullDao = checkDao()
        unitResultTask({}, {nonNullDao.delete(item)}, {})
    }

}