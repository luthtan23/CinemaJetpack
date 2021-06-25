package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.Embedded
import androidx.room.Relation

data class DetailWithTrailer(
    @Embedded
    var detailEntity: DetailEntity,
    @Relation(parentColumn = "detailId", entityColumn = "detailId")
    var trailerItemsEntity: List<TrailerItemsEntity>
)
