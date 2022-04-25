package a114w6077dicoding.develops.moviecatalog.ui.favorite

import a114w6077dicoding.develops.moviecatalog.ViewModelFactory
import a114w6077dicoding.develops.moviecatalog.adapter.TvShowCatalogueAdapter
import a114w6077dicoding.develops.moviecatalog.databinding.FragmentTvShowBinding
import a114w6077dicoding.develops.moviecatalog.ui.tv.TvShowViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class FavoriteTvFragment : Fragment() {
    private var _binding : FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val factory = ViewModelFactory.getInstance(binding.root.context)
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowCatalogueAdapter()

            binding.apply {
                pbTvShows.visibility = View.GONE
                viewModel.getTvShowFavorite().observe(viewLifecycleOwner, {
                    if (it != null){
                        tvShowAdapter.submitList(it)
                    }
                })
            }

            binding.scSearch.visibility = View.GONE

            with(binding.rvTvShow){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
