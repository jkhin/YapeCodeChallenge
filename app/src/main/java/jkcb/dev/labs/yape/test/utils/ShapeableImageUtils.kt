package jkcb.dev.labs.yape.test.utils

import com.google.android.material.imageview.ShapeableImageView

fun ShapeableImageView.setCornerSizes(dimen: Int) {
    val radius = this.resources.getDimension(dimen)
    val shapeAppearanceModel = this.shapeAppearanceModel.toBuilder()
        .setAllCornerSizes(radius)
        .build()
    this.shapeAppearanceModel = shapeAppearanceModel
}