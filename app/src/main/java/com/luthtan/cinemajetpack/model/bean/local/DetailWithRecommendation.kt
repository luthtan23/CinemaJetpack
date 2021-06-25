package com.luthtan.cinemajetpack.model.bean.local

import androidx.room.Embedded
import androidx.room.Relation

data class DetailWithRecommendation(
    @Embedded
    var detailEntity: DetailEntity,
    @Relation(parentColumn = "detailId", entityColumn = "detailId")
    var recommendationItemsEntity: List<RecommendationItemsEntity>
)
