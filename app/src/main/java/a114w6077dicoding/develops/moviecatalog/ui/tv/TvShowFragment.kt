package a114w6077dicoding.develops.moviecatalog.ui.tv

import a114w6077dicoding.develops.moviecatalog.ViewModelFactory
import a114w6077dicoding.develops.moviecatalog.adapter.TvShowCatalogueAdapter
import a114w6077dicoding.develops.moviecatalog.databinding.FragmentTvShowBinding
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

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val fragmentTvShowBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity!=null){
            val factory = ViewModelFactory.getInstance(fragmentTvShowBinding.root.context)
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            val tvShowAdapter = TvShowCatalogueAdapter()

            fragmentTvShowBinding.pbTvShows.visibility = View.VISIBLE

            viewModel.getTvShow().observe(viewLifecycleOwner, {
                fragmentTvShowBinding.apply {
                    if (it != null){
                        when(it.status){
                            Status.LOADING -> pbTvShows.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                pbTvShows.visibility = View.GONE
                                if (it.data != null)
                                    tvShowAdapter.submitList(it.data)
                            }
                            Status.ERROR -> {
                                pbTvShows.visibility = View.GONE
                                Toast.makeText(root.context, "Something Wrong", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            })

            fragmentTvShowBinding.scSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.searchTvShow(newText).observe(viewLifecycleOwner, {
                        tvShowAdapter.submitList(it)
                    })
                    return true
                }

            })

            with(fragmentTvShowBinding.rvTvShow){
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