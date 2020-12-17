package ru.maxdexter.mytranslatorkoincoroutines.db
import androidx.room.*
import androidx.room.Dao
import com.google.android.material.circularreveal.CircularRevealHelper
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistory(historyModel: HistoryModel)

    @Delete
    suspend fun deleteHistory(historyModel: HistoryModel)

    @Query("SELECT * FROM history")
    fun getAll(): Flow<List<HistoryModel>>
}