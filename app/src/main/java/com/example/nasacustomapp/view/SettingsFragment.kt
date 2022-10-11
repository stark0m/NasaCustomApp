package com.example.nasacustomapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nasacustomapp.R
import com.example.nasacustomapp.model.theme.AppTheme
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.google.android.material.button.MaterialButton


class SettingsFragment : Fragment() {
    private val viewModelNasaFragment: NasaViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NasaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showDialogAndSetListeners(view)
    }

    private fun showDialogAndSetListeners(view: View) {
        val buttonsLayout: LinearLayoutCompat? =
            view.findViewById<LinearLayoutCompat>(R.id.linear_layout_buttons)

        for (theme in AppTheme.values()) {

            val button: MaterialButton = MaterialButton(requireContext())
            button.text = theme.name
            button.layoutParams =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            button.setOnClickListener() {
                viewModelNasaFragment.setApplicationTheme(theme)

            }
            buttonsLayout!!.addView(button)
        }
    }

    companion object {
        fun newInstance() =
            SettingsFragment()
    }
}