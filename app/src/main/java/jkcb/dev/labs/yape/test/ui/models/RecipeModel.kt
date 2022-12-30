package jkcb.dev.labs.yape.test.ui.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipeModel(
    val title: String,
    val img: String,
    val description: String,
    val preparation: String,
    val ingredients: List<String>,
    val tags: List<String>,
    val lat: Double,
    val lng: Double
) : Parcelable


