package ru.maxdexter.mytranslatorkoincoroutines.db

import android.app.DownloadManager
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.intellij.lang.annotations.PrintFormat
import ru.maxdexter.mytranslatorkoincoroutines.model.Meanings
@Entity(tableName = "History")
data class HistoryModel (
    @PrimaryKey
    val query: String,
    val translate: String,
    val meanings: String)  {
}