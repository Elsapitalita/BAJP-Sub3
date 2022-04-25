package a114w6077dicoding.develops.moviecatalog.ui.favorite

import a114w6077dicoding.develops.moviecatalog.ViewModelFactory
import a114w6077dicoding.develops.moviecatalog.adapter.MovieCatalogueAdapter
import a114w6077dicoding.develops.moviecatalog.databinding.FragmentMovieBinding
import a114w6077dicoding.develops.moviecatalog.ui.movie.MovieViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class FavoriteMovieFragment : Fragment() {
    private var _binding : FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val factory = ViewModelFactory.getInstance(binding.root.context)
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieCatalogueAdapter = MovieCatalogueAdapter()

            binding.apply {
                pbMovie.visibility = View.GONE


                viewModel.getMovieFavorite().observe(viewLifecycleOwner, {
                    if (it != null){
                        movieCatalogueAdapter.submitList(it)
                    }
                })
            }
            binding.scSearch.visibility = View.GONE

            with(binding.rvMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieCatalogueAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
