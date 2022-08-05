package com.tiorisnanto.githubuser2tio.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.tiorisnanto.githubuser2tio.database.FavoriteDao
import com.tiorisnanto.githubuser2tio.database.FavoriteRoomDatabase
import com.tiorisnanto.githubuser2tio.model.Favorite

class FavoriteRepository (application: Application) {
    private val mFavoriteDao: FavoriteDao

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteDao.getAllFavorites()

    fun check(username: String) = mFavoriteDao.check(username)

    fun insert(favorite: Favorite) = mFavoriteDao.insert(favorite)

    fun delete(username: String) = mFavoriteDao.delete(username)
}