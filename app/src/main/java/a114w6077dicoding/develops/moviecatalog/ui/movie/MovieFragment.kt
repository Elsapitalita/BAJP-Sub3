package a114w6077dicoding.develops.moviecatalog.ui.movie

import a114w6077dicoding.develops.moviecatalog.ViewModelFactory
import a114w6077dicoding.develops.moviecatalog.adapter.MovieCatalogueAdapter
import a114w6077dicoding.develops.moviecatalog.databinding.FragmentMovieBinding
import a114w6077dicoding.develops.moviecatalog.vo.Status
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val fragmentMovieBinding get()= _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity!=null){
            val factory = ViewModelFactory.getInstance(fragmentMovieBinding.root.context)
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

            val movieAdapter = MovieCatalogueAdapter()

            fragmentMovieBinding.apply {
                pbMovie.visibility = View.VISIBLE

                viewModel.getMovie().observe(viewLifecycleOwner, { movie ->
                    when(movie.status){
                        Status.LOADING -> pbMovie.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            pbMovie.visibility = View.GONE
                            if (movie.data != null)
                                movieAdapter.submitList(movie.data)
                        }
                        Status.ERROR -> {
                            pbMovie.visibility = View.GONE
                            Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
            }

            fragmentMovieBinding.scSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String): Boolean {
                    return true
                }

                override fun onQueryTextChange(movie: String): Boolean {
                    viewModel.searchMovie(movie).observe(viewLifecycleOwner, {
                        movieAdapter.submitList(it)
                    })
                    return true
                }
            })

            with(fragmentMovieBinding.rvMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}