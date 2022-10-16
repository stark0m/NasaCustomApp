package com.example.nasacustomapp.view.maincontent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasacustomapp.databinding.FragmentConstraintBinding


class ConstraintFragment : Fragment() {
    private var _binding: FragmentConstraintBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConstraintBinding.inflate(inflater, container, false)
        return binding.root
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