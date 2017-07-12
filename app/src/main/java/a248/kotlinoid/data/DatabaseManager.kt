package a248.kotlinoid.data

import android.arch.persistence.room.*
import android.content.Context

const val TABLE_NAME = "test_table"

@Entity(tableName = TABLE_NAME)
class ObjectEnity{
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
    @ColumnInfo(name = "title") var title: String = ""
    @ColumnInfo(name = "desc") var desc: String = ""
}

@Dao
interface ObjectDao {
    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<ObjectEnity>

    @Insert
    fun insertAll(vararg objects: ObjectEnity)

    @Delete
    fun delete(objectEntity: ObjectEnity)
}

@Database(entities = arrayOf(ObjectEnity::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun objectDao(): ObjectDao
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