package com.shivansh.officetask.views.fragments.moviesBottomNavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivansh.officetask.R
import com.shivansh.officetask.adapters.MovieFullDetailAdapter
import com.shivansh.officetask.adapters.MoviesAdapter
import com.shivansh.officetask.databinding.FragmentTopRatedMoviesBinding
import com.shivansh.officetask.viewModels.MoviesViewModel

class TopRatedMoviesFragment : Fragment() {
    private lateinit var binding: FragmentTopRatedMoviesBinding
    private lateinit var viewModel : MoviesViewModel

    private lateinit var recyclerView : RecyclerView
    private lateinit var moviesAdapter: MovieFullDetailAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_top_rated_movies, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        moviesAdapter = MovieFullDetailAdapter(context)
        recyclerView = binding.MoviesRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = moviesAdapter

        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer {
                    moviesAdapter.setList(it)
        })

    }
}