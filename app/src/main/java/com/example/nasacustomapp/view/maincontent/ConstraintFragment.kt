package com.example.nasacustomapp.view.maincontent

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasacustomapp.databinding.FragmentConstraintBinding
import com.example.nasacustomapp.utils.ANIMATION_LENGTH


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOne.setOnClickListener {
            ObjectAnimator.ofFloat(it,View.ROTATION,0f,360f).setDuration(ANIMATION_LENGTH).start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}