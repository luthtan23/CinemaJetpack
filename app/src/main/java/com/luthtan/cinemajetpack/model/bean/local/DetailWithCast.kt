package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.Embedded
import androidx.room.Relation

data class DetailWithCast(
    @Embedded
    var detailEntity: DetailEntity,
    @Relation(parentColumn = "detailId", entityColumn = "detailId")
    var castItemEntity: List<CastItemEntity>
)
