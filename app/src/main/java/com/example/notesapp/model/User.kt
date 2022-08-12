package com.example.notesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "user_card")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val date: String
    ): Parcelable