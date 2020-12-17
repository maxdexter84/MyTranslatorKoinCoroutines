package ru.maxdexter.mytranslatorkoincoroutines.db

import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Database

import androidx.room.TypeConverters
import ru.maxdexter.mytranslatorkoincoroutines.model.DetailModel

@Database(entities = [DetailModel::class],version = 1)
abstract class Database : RoomDatabase() {

    abstract fun getDao(): Dao
}