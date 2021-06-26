package com.luthtan.cinemajetpack.listener

import com.luthtan.cinemajetpack.model.bean.local.DetailEntity

interface DeleteFavoriteListener {

    fun selectedDeleteFavorite(detailEntity: DetailEntity)
}