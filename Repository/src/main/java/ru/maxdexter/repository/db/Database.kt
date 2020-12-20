package ru.maxdexter.repository.db

import androidx.room.RoomDatabase
import androidx.room.Database

import ru.maxdexter.repository.model.DetailModel

@Database(entities = [DetailModel::class],version = 1)
abstract class Database : RoomDatabase() {

    abstract fun getDao(): Dao
}