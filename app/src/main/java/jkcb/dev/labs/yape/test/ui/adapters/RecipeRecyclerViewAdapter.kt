package jkcb.dev.labs.yape.test.ui.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat.setTextAppearance
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import jkcb.dev.labs.yape.test.R
import jkcb.dev.labs.yape.test.databinding.ItemChipTagBinding
import jkcb.dev.labs.yape.test.databinding.ItemListRecipeBinding
import jkcb.dev.labs.yape.test.databinding.ItemRecipeChipTagBinding
import jkcb.dev.labs.yape.test.ui.fragments.RecipesFragmentDirections
import jkcb.dev.labs.yape.test.ui.models.RecipeModel
import jkcb.dev.labs.yape.test.utils.setCornerSizes
import com.google.android.material.R as materialR


class RecipeRecyclerViewAdapter :
    ListAdapter
    <RecipeModel, RecipeRecyclerViewAdapter.RecipeViewHolder>(
        RecipeDiffUtilCallback()
    ) {

/*    fun filterRecipes(keyword: String): List<RecipeModel> {
        return currentList.filter {
            it.title.contains(keyword) or it.ingredients.filterByIngredient(keyword)
        }
    }

    private fun List<String>.filterByIngredient(
        keyword: String
    ) = any { ingredient -> ingredient.contains(keyword) }*/

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeViewHolder = RecipeViewHolder(
        ItemListRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: RecipeViewHolder,
        position: Int
    ) = holder.bind(getItem(position))

    inner class RecipeViewHolder(
        private val binding: ItemListRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ivItemRecipeImage.setCornerSizes(R.dimen.dp_16)
        }

        fun bind(model: RecipeModel) = with(binding) {
            Glide.with(itemView.context)
                .load(model.img)
                .centerCrop()
                .into(ivItemRecipeImage)

            tvItemRecipeName.text = model.title
            setupTags(model.tags)

            itemView.setOnClickListener {
                it.findNavController().navigate(
                    RecipesFragmentDirections.actionRecipesFragmentToDetailRecipeFragment(model)
                )
            }
        }

        private fun setupTags(tags: List<String>) = with(binding) {
            tags.forEach {
                cgItemRecipeTags.addView(createAndGetChip(it))
            }
        }
        private fun createAndGetChip(label: String): Chip =
            ItemRecipeChipTagBinding.inflate(LayoutInflater.from(itemView.context)).root.apply {
                val tag = "#${label}"
                text = tag
                isClickable = false
                isFocusable = false
                isCheckable = false
                setTextAppearance(R.style.ChipTextAppearanceSubtitle2)
            }
    }

}
