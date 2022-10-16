package com.example.nasacustomapp.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.*
import com.example.nasacustomapp.databinding.FragmentSettingsBinding
import com.example.nasacustomapp.model.theme.AppTheme
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.google.android.material.button.MaterialButton


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val viewModelNasaFragment: NasaViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NasaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        generateAndShowThemeButtons()
    }


    private fun generateAndShowThemeButtons() {

        for (theme in AppTheme.values()) {
            val button: MaterialButton = MaterialButton(requireContext()).apply {
                text = theme.name
                visibility = View.GONE
                layoutParams =
                    LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                setOnClickListener() {
                    viewModelNasaFragment.setApplicationTheme(theme)
                }
            }
            binding.linearLayoutButtons.addView(button)
            Thread() { // не до конца разобрался в чем причина, если не запускать поток - анимация не отображается, работает только в таком варианте, хотя понимаю - это костыль
                Handler(Looper.getMainLooper()).post {
                    addLayoutAnimation()
                    button.visibility = View.VISIBLE
                }
            }.start()
        }
    }

    private fun addLayoutAnimation() {
        val myAutoTransition = TransitionSet()
        myAutoTransition.ordering = TransitionSet.ORDERING_SEQUENTIAL
        val fade = Slide(Gravity.END)
        val changeBounds = ChangeBounds()
        myAutoTransition.addTransition(changeBounds)
        myAutoTransition.addTransition(fade)
        TransitionManager.beginDelayedTransition(binding.linearLayoutButtons, myAutoTransition)
    }

    companion object {
        fun newInstance() =
            SettingsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}