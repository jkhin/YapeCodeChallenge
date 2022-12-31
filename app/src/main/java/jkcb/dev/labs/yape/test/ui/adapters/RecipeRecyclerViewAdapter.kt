package jkcb.dev.labs.yape.test.ui.adapters

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import jkcb.dev.labs.yape.test.R
import jkcb.dev.labs.yape.test.databinding.ItemListRecipeBinding
import jkcb.dev.labs.yape.test.databinding.ItemRecipeChipTagBinding
import jkcb.dev.labs.yape.test.ui.fragments.RecipesFragmentDirections
import jkcb.dev.labs.yape.test.ui.models.RecipeModel
import jkcb.dev.labs.yape.test.utils.setCornerSizes


class RecipeRecyclerViewAdapter :
    ListAdapter
    <RecipeModel, RecipeRecyclerViewAdapter.RecipeViewHolder>(
        RecipeDiffUtilCallback()
    ) {


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
            }
    }

}
