package com.example.nasacustomapp.view.maincontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.nasacustomapp.R
import com.example.nasacustomapp.databinding.FragmentPhotoOfTheDayBinding
import com.example.nasacustomapp.model.viewmodel.AppState
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.example.nasacustomapp.utils.AppUtils


class ConstraintFragment : Fragment() {
    private var _binding: FragmentPhotoOfTheDayBinding? = null
    private val binding get() = _binding!!
    private val viewModelNasaFragment: NasaViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NasaViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoOfTheDayBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelNasaFragment.getObserver().observe(viewLifecycleOwner) { doAction(it) }
        viewModelNasaFragment.getData()


    }





    private fun doAction(responce: AppState) {
        when (responce) {
            is AppState.Error -> {
                AppUtils.toast(requireContext(), responce.error.toString())
            }
            AppState.Loading -> TODO()
            is AppState.Success -> {
                val url = responce.serverResponce.url
                if (url.isNullOrEmpty()) {
                    AppUtils.toast(requireContext(), "Ссылка пустая")
                } else {
                    showPODinFragment(responce)

                }
            }
            else -> {
                //nothing TODO
            }
        }

    }

    private fun showPODinFragment(responce: AppState.Success) {
      val url = responce.serverResponce.url

        binding.imageView.load(url) {
            lifecycle(this@ConstraintFragment)
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
            crossfade(true)
        }

        with(responce.serverResponce) {
            val description: TextView = requireView().findViewById(R.id.bottomSheetDescription)
            val title: TextView = requireView().findViewById(R.id.title_text)

            description.text = this.explanation
            title.text = this.title

        }
    }
    companion object {

        fun newInstance() =
            ConstraintFragment()

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}