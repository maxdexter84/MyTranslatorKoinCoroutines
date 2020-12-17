package ru.maxdexter.mytranslatorkoincoroutines.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailModel (val word: String? = "", val translate: String? = "", val imageUrl: String? = "") : Parcelable