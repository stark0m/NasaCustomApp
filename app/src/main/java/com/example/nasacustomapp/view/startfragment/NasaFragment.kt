package com.example.nasacustomapp.view.startfragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nasacustomapp.databinding.FragmentMainBinding
import com.example.nasacustomapp.model.viewmodel.AppState
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.example.nasacustomapp.utils.AppUtils

class NasaFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModelNasaFragment: NasaViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NasaViewModel::class.java)
    }

    companion object {
        fun newInstance() = NasaFragment()
    }

    private lateinit var viewModel: NasaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelNasaFragment.getObserver().observe(viewLifecycleOwner){doAction(it)}
    }

    private fun doAction(action: AppState) {
        when (action){
            is AppState.Error ->{
                AppUtils.toast(requireContext(),action.error.toString())
            }
            AppState.Loading -> TODO()
            is AppState.Success -> TODO()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}