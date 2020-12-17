package ru.maxdexter.mytranslatorkoincoroutines.model

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
    val imageUrl: String = "") : Parcelable