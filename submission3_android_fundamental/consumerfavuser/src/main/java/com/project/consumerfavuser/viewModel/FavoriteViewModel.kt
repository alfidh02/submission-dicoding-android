package com.project.consumerfavuser.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.consumerfavuser.db.DBContract
import com.project.consumerfavuser.db.MapHelper
import com.project.consumerfavuser.model.User

class FavoriteViewModel(app: Application) : AndroidViewModel(app) {
    private var favList = MutableLiveData<ArrayList<User>>()

    fun setListFavUser(context: Context) {
        val dataCursor = context.contentResolver.query(
            DBContract.Column.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val cursorToArrayList = MapHelper.mapCursorToArrayList(dataCursor)
        favList.postValue(cursorToArrayList)
    }

    fun getListFavUser(): LiveData<ArrayList<User>> = favList
}