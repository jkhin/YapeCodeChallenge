package jkcb.dev.labs.yape.test.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jkcb.dev.labs.yape.test.databinding.FragmentRecipesBinding
import jkcb.dev.labs.yape.test.ui.adapters.RecipeRecyclerViewAdapter
import jkcb.dev.labs.yape.test.ui.models.RecipeModel
import jkcb.dev.labs.yape.test.ui.viewmodels.RecipesViewModel
import jkcb.dev.labs.yape.test.ui.viewmodels.states.RecipesViewState
import jkcb.dev.labs.yape.test.utils.RecipeItemDecoration
import jkcb.dev.labs.yape.test.utils.handleVisibility
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding

    private val recipesViewModel: RecipesViewModel by viewModel()

    private val recipeRecyclerViewAdapter: RecipeRecyclerViewAdapter by lazy {
        RecipeRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupRecycler()
        setupEvents()
        recipesViewModel.getRecipes()
    }

    private fun setupRecycler() = with(binding.rvRecipes) {
        adapter = recipeRecyclerViewAdapter
        addItemDecoration(RecipeItemDecoration())
        layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        setHasFixedSize(true)
    }

    private fun setupEvents() {
        recipesViewModel.recipesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is RecipesViewState.Loading -> doOnLoading(it.isLoading)
                is RecipesViewState.Success -> doOnSuccess(it.recipes)
                is RecipesViewState.Error -> doOnError(it.message)
            }
        }

        binding.tilSearchRecipes.editText?.doOnTextChanged { keyword, _, _, _ ->
            recipesViewModel.filterRecipes(keyword.toString())
        }
    }

    private fun doOnLoading(isLoading: Boolean) {
        binding.gData.handleVisibility(!isLoading)
        binding.cpCircularProgress.handleVisibility(isLoading)
    }

    private fun doOnSuccess(recipes: List<RecipeModel>) {
        recipeRecyclerViewAdapter.submitList(recipes)
    }

    private fun doOnError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

}