package ru.maxdexter.repository.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DetailModel (
    @PrimaryKey
    val word: String = "",
    val translate: String = "",
    var bookmark: Boolean = false,
    val imageUrl: String = "") : Parcelable