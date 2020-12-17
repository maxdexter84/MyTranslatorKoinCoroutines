package ru.maxdexter.mytranslatorkoincoroutines.db
import androidx.room.*
import androidx.room.Dao
import com.google.android.material.circularreveal.CircularRevealHelper
import kotlinx.coroutines.flow.Flow
import ru.maxdexter.mytranslatorkoincoroutines.model.DetailModel


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistory(detailModel: DetailModel)

    @Delete
    suspend fun deleteHistory(detailModel: DetailModel)

    @Query("SELECT * FROM detailmodel")
    fun getAll(): Flow<List<DetailModel>>
}