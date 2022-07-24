package com.example.testexamplecleanarch.presentation.mainFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testexamplecleanarch.R
import com.example.testexamplecleanarch.common.Response
import com.example.testexamplecleanarch.data.model.BikeStationsModel
import com.example.testexamplecleanarch.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding!!
    private val viewModel: MainFragmentViewModel by viewModels()
    var stationsArrayList: ArrayList<BikeStationsModel.Feature> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        init()
        viewModel.getStations("pub_transport", "stacje_rowerowe")
        return _binding?.root
    }

    private fun init() {
        lifecycleScope.launch {
            viewModel.resp.collect() { result ->
                when (result) {
                    is Response.Loading -> {

                    }
                    is Response.Success -> {
                        if (result.data != null) {
                            Log.e("TAG", "init:data is not null ${result.data} ", )
                            val response=result.data
                            for (f in response.features){
                                stationsArrayList.add(f)

                            }
                            Log.e("TAG", "size: ${stationsArrayList.size}", )
                            binding.recycler.layoutManager = LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.VERTICAL,
                                false
                            )


                            val adapter = StationsAdapter(stationsArrayList, requireContext())
                            binding.recycler.adapter = adapter
                        }
                    }
                    is Response.Error -> {
                        Log.e("TAG", "error ${result.message}")
                    }
                }
            }
        }
    }
}