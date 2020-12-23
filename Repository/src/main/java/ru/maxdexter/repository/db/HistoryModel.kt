package ru.maxdexter.repository.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class HistoryModel (
    @PrimaryKey
    val query: String,
    val translate: String)  {
}