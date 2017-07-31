package a248.kotlinoid.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.content.Context

const val TABLE_NAME = "test_table"

@Entity(tableName = TABLE_NAME)
class ItemEntity {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
    @ColumnInfo(name = "title") var title: String = ""
    @ColumnInfo(name = "desc") var desc: String = ""
}

@Dao
interface ItemDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAllAsLiveData(): LiveData<List<ItemEntity>>

    @Insert
    fun insertAll(vararg objects: ItemEntity)

    @Delete
    fun delete(objectEntity: ItemEntity)
}

@Database(entities = arrayOf(ItemEntity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
    companion object {
        private var instance: AppDatabase? = null
        fun getInstance(ctx: Context): AppDatabase? {
            synchronized(this) {
                if (instance == null) {
                    instance = Room.databaseBuilder(ctx, AppDatabase::class.java, "test-db").build()
                }
                return instance
            }
        }
    }
}

fun Context.getDb(): AppDatabase {
    return AppDatabase.getInstance(applicationContext)!!
}