package jkcb.dev.labs.yape.test.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import jkcb.dev.labs.yape.test.R
import jkcb.dev.labs.yape.test.databinding.FragmentDetailRecipeBinding
import jkcb.dev.labs.yape.test.databinding.ItemChipTagBinding
import jkcb.dev.labs.yape.test.utils.setCornerSizes

class DetailRecipeFragment : Fragment() {

    private lateinit var binding: FragmentDetailRecipeBinding

    private val args: DetailRecipeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailRecipeBinding
            .inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupDataOnView()
        setupViewEvents()
    }

    private fun setupDataOnView() = with(binding) {
        setupRecipeImage()
        tvRecipeDetailTitle.text = args.model.title
        tvRecipeDetailDesc.text = args.model.description
        tvRecipeDetailPreparation.text = args.model.preparation
        setupTags()
    }

    private fun setupViewEvents() = with(binding) {
        bGoToMap.setOnClickListener {
            val action = DetailRecipeFragmentDirections
                .actionDetailRecipeFragmentToCuriousMapFragment(
                    args.model.lat.toFloat(),
                    args.model.lng.toFloat()
                )
            findNavController().navigate(action)
        }
    }

    private fun setupRecipeImage() = with(binding) {
        Glide.with(requireContext())
            .load(args.model.img)
            .centerCrop()
            .into(ivRecipeDetailImage)
        ivRecipeDetailImage.setCornerSizes(R.dimen.dp_32)
    }

    private fun setupTags() = with(binding) {
        args.model.ingredients.forEach {
            cgTags.addView(createAndGetChip(it))
        }
    }

    private fun createAndGetChip(label: String): Chip =
        ItemChipTagBinding.inflate(layoutInflater).root.apply {
            text = label
            setTextAppearance(R.style.ChipTextAppearanceSubtitle2)
        }
}
