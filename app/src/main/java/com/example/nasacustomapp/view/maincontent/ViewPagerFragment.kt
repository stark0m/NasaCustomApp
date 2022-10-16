package com.example.nasacustomapp.view.maincontent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nasacustomapp.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerFragment : Fragment() {
    private var _binding: FragmentViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = ContentViewPagerAdapterV2(this)
        bindTabLayoutMediator()
    }

    private fun bindTabLayoutMediator() {
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager
        ) { tab, position ->
            tab.text = when (position) {
                0 -> {
                    "Photo"
                }
                1 -> {
                    "Constraint"
                }
                2 -> {
                    "Coordinator"
                }
                else -> "Onother"

            }
        }.attach()
    }

    companion object {
        fun newInstance() = ViewPagerFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
