package com.shivansh.officetask.views.fragments.drawerNavigationFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivansh.officetask.adapters.MoviesAdapter
import com.shivansh.officetask.databinding.FragmentMoviesBinding
import com.shivansh.officetask.viewModels.MoviesViewModel

class MoviesFragment : Fragment() {
    private lateinit var binding : FragmentMoviesBinding
    private lateinit var viewModel : MoviesViewModel

    private lateinit var topRatedMoviesRecyclerView : RecyclerView
    private lateinit var topRatedMoviesAdapter : MoviesAdapter

    private lateinit var upcomingMoviesRecyclerView : RecyclerView
    private lateinit var upcomingMoviesAdapter: MoviesAdapter

    private lateinit var popularMoviesRecyclerView: RecyclerView
    private lateinit var popularMoviesAdapter: MoviesAdapter

    private lateinit var nowPlayingMoviesAdapter: MoviesAdapter
    private lateinit var nowPlayingMoviesRecyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = FragmentMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        topRatedMoviesAdapter = MoviesAdapter(context)
        topRatedMoviesRecyclerView = binding.topRatedMoviesRecyclerView
        topRatedMoviesRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        topRatedMoviesRecyclerView.setHasFixedSize(true)
        topRatedMoviesRecyclerView.adapter = topRatedMoviesAdapter

        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer {
            topRatedMoviesAdapter.setList(it)
        })

        upcomingMoviesAdapter = MoviesAdapter(context)
        upcomingMoviesRecyclerView = binding.UpcomingMoviesRecyclerView
        upcomingMoviesRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        upcomingMoviesRecyclerView.setHasFixedSize(true)
        upcomingMoviesRecyclerView.adapter = upcomingMoviesAdapter

        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer {
            upcomingMoviesAdapter.setList(it)
        })

        popularMoviesAdapter = MoviesAdapter(context)
        popularMoviesRecyclerView = binding.PopularMoviesRecyclerView
        popularMoviesRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        popularMoviesRecyclerView.setHasFixedSize(true)
        popularMoviesRecyclerView.adapter = popularMoviesAdapter

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer {
            popularMoviesAdapter.setList(it)
        })

        nowPlayingMoviesAdapter = MoviesAdapter(context)
        nowPlayingMoviesRecyclerView = binding.NowPlayingMoviesRecyclerView
        nowPlayingMoviesRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        nowPlayingMoviesRecyclerView.setHasFixedSize(true)
        nowPlayingMoviesRecyclerView.adapter = nowPlayingMoviesAdapter

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner, Observer {
            nowPlayingMoviesAdapter.setList(it)
        })
    }
}