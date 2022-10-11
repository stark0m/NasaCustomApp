package com.example.nasacustomapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nasacustomapp.R
import com.example.nasacustomapp.databinding.FragmentMainBinding
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.example.nasacustomapp.view.maincontent.PhotoOfTheDayFragment

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