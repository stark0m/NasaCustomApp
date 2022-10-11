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
import com.example.nasacustomapp.databinding.FragmentCoordinatorBinding
import com.example.nasacustomapp.databinding.FragmentPhotoOfTheDayBinding
import com.example.nasacustomapp.model.viewmodel.AppState
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.example.nasacustomapp.utils.AppUtils


class CoordinatorFragment : Fragment() {
    private var _binding: FragmentCoordinatorBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoordinatorBinding.inflate(inflater, container, false)
        return binding.root
    }


    companion object {
        fun newInstance() =
            CoordinatorFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}