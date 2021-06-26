package com.luthtan.cinemajetpack.util

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.luthtan.cinemajetpack.R
import com.luthtan.cinemajetpack.model.bean.local.GenresItemEntity
import com.luthtan.cinemajetpack.model.remote.ApiConstant
import com.luthtan.cinemajetpack.ui.MainActivity
import java.util.*

object Utils {

    fun convertPojoToMap(gson: Gson, model: Any): HashMap<String, String> {
        val json = gson.toJson(model)
        return gson.fromJson<HashMap<String, String>>(json, HashMap::class.java)
    }

    fun insertStringGenre(genres: List<GenresItemEntity>): StringBuilder {
        val genre = StringBuilder()
        for (i in genres.indices) {
            genre.append(genres[i].name)
            if (i != genres.size - 1) {
                genre.append(", ")
            }
        }
        return genre
    }

    fun snackBarErrorConnection(view: View, context: Context) {
        return Snackbar.make(
            view, context.getString(R.string.no_network_error),
            Snackbar.LENGTH_INDEFINITE
        )
            .setBackgroundTint(context.resources.getColor(R.color.red))
            .setAction("DISMISS") {}
            .setActionTextColor(context.resources.getColor(R.color.blue_dark))
            .show()
    }

    fun trailerCinema(activity: MainActivity, youtubeLink: String) {
        try {
            val uri = Uri.parse(ApiConstant.YOUTUBE_URL.plus(youtubeLink))
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.startActivity(intent)
        } catch (e: Exception) {
            //dialog alert
        }
    }

    fun showAlertDialog(context: Context, title: String, message: String) {
        val alertDialog = AlertDialog.Builder(context)

        alertDialog.setTitle(title)
            .setMessage(message)
            .setPositiveButton(context.getString(R.string.try_again)) { dialog, which -> dialog.dismiss() }

        val alert = alertDialog.create()
        alert.show()
    }

}