package com.agatakobusinska.countriesfacts.main.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "currency_table")
data class Currency(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "country_id")
    val countryId: Int?,

    @ColumnInfo(name = "code")
    val currencyCode: String?,

    @ColumnInfo(name = "name")
    val currencyName: String?,

    @ColumnInfo(name = "symbol")
    val currencySymbol: String?
) : Serializable