package com.samapp.getswipeinternshipassigment.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samapp.getswipeinternshipassigment.data.local.dao.ProductDao
import com.samapp.getswipeinternshipassigment.data.local.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}