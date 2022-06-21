package com.shivansh.officetask.views.fragments.drawerNavigationFragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivansh.officetask.R
import com.shivansh.officetask.adapters.MoviesAdapter
import com.shivansh.officetask.adapters.PersonAdapter
import com.shivansh.officetask.adapters.TvShowAdapter
import com.shivansh.officetask.databinding.FragmentEntertainmentBinding
import com.shivansh.officetask.viewModels.EntertainmentViewModel
import com.shivansh.officetask.views.activities.entertainment.AllCelebrityActivity
import com.shivansh.officetask.views.activities.entertainment.AllMoviesActivity
import com.shivansh.officetask.views.activities.entertainment.AllWebShowActivity

class EntertainmentFragment : Fragment() {
    private lateinit var binding : FragmentEntertainmentBinding
    private lateinit var viewModel : EntertainmentViewModel

    private lateinit var moviesRecyclerView : RecyclerView
    private lateinit var moviesAdapter : MoviesAdapter

    private lateinit var tvShowRecyclerView: RecyclerView
    private lateinit var tvShowAdapter : TvShowAdapter

    private lateinit var personRecyclerView: RecyclerView
    private lateinit var personAdapter : PersonAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_entertainment,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[EntertainmentViewModel::class.java]
        binding.viewModel = viewModel

        moviesAdapter = MoviesAdapter(context)
        moviesRecyclerView = binding.MoviesRecyclerView
        moviesRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        moviesRecyclerView.setHasFixedSize(true)
        moviesRecyclerView.adapter = moviesAdapter

        viewModel.movies.observe(viewLifecycleOwner, Observer {
            moviesAdapter.setList(it)
        })

        tvShowAdapter = TvShowAdapter(context)
        tvShowRecyclerView = binding.WebSeriesRecyclerView
        tvShowRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        tvShowRecyclerView.setHasFixedSize(true)
        tvShowRecyclerView.adapter = tvShowAdapter

        viewModel.webSeries.observe(viewLifecycleOwner, Observer {
            tvShowAdapter.setList(it)
        })

        personAdapter = PersonAdapter(context)
        personRecyclerView = binding.celebrityRecyclerView
        personRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        personRecyclerView.setHasFixedSize(true)
        personRecyclerView.adapter = personAdapter

        viewModel.persons.observe(viewLifecycleOwner, Observer {
            personAdapter.setList(it)
        })

        viewModel.AllMovieResponse.observe(viewLifecycleOwner, Observer {
            if(it){
                startActivity(Intent(activity,AllMoviesActivity::class.java))
            }
        })

        viewModel.AllWebShowsResponse.observe(viewLifecycleOwner, Observer {
            if(it){
                startActivity(Intent(activity,AllWebShowActivity::class.java))
            }
        })

        viewModel.AllCelebrityResponse.observe(viewLifecycleOwner, Observer {
            if (it){
                startActivity(Intent(activity,AllCelebrityActivity::class.java))
            }
        })
    }
}