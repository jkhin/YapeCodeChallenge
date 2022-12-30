package jkcb.dev.labs.yape.test.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import jkcb.dev.labs.yape.test.ui.models.RecipeModel

class RecipeDiffUtilCallback : DiffUtil.ItemCallback<RecipeModel>() {
    override fun areItemsTheSame(
        oldItem: RecipeModel,
        newItem: RecipeModel
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: RecipeModel,
        newItem: RecipeModel
    ): Boolean = oldItem == newItem
}