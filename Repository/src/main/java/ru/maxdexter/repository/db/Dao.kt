package ru.maxdexter.repository.db
import androidx.room.*
import androidx.room.Dao
import kotlinx.coroutines.flow.Flow
import ru.maxdexter.repository.model.DetailModel


@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHistory(detailModel: DetailModel)

    @Delete
    suspend fun deleteHistory(detailModel: DetailModel)

    @Query("SELECT * FROM detailmodel")
    fun getAll(): Flow<List<DetailModel>>
}