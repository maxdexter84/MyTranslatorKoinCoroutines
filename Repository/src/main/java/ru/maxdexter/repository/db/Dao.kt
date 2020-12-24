package ru.maxdexter.repository.db
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistory(detailModel: DetailModel)

    @Delete
    suspend fun deleteHistory(detailModel: DetailModel)

    @Query("SELECT * FROM detailmodel")
    fun getAll(): Flow<List<DetailModel>>


    @Query("SELECT * FROM detailmodel WHERE bookmark=:flag")
    fun getAllBookmarks(flag: Boolean = true): LiveData<List<DetailModel>>
}