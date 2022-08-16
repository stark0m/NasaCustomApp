package com.example.nasacustomapp.view.startfragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.nasacustomapp.R
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
        viewModelNasaFragment.getData()
    }

    private fun doAction(responce: AppState) {
        when (responce){
            is AppState.Error ->{
                AppUtils.toast(requireContext(),responce.error.toString())
            }
            AppState.Loading -> TODO()
            is AppState.Success -> {
                val url = responce.serverResponce.url
                if (url.isNullOrEmpty()){
                    AppUtils.toast(requireContext(),"Ссылка пустая")
                } else {
                    binding.imageView.load(url){
                        lifecycle(this@NasaFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.ic_no_photo_vector)
                        crossfade(true)
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}