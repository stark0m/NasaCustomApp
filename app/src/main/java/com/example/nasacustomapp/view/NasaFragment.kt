package com.example.nasacustomapp.view

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import coil.load
import com.example.nasacustomapp.R
import com.example.nasacustomapp.databinding.FragmentMainBinding
import com.example.nasacustomapp.model.theme.AppTheme
import com.example.nasacustomapp.model.viewmodel.AppState
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.example.nasacustomapp.utils.AppUtils
import com.example.nasacustomapp.utils.WIKI_PARSE_URL
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton

class NasaFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!



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

    private fun showFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNavigationMenuListener()
        if (savedInstanceState==null){
            showFragment(PhotoOfTheDayFragment.newInstance())
        }


    }

    private fun setNavigationMenuListener() {
        binding.bottomNavigationView.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.action_view_earth -> {
                    showFragment(PhotoOfTheDayFragment.newInstance()); true
                }
                R.id.action_view_settings -> {

                    showFragment(SettingsFragment.newInstance()); true
                }
                R.id.action_view_wiki -> {

                    showFragment(WikiFragment.newInstance()); true
                }
                else -> true

            }


        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}