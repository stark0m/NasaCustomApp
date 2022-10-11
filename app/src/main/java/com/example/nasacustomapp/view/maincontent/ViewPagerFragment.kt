package com.example.nasacustomapp.view.maincontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nasacustomapp.R
import com.example.nasacustomapp.databinding.FragmentPhotoOfTheDayBinding
import com.example.nasacustomapp.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayout
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
        TabLayoutMediator(
            binding.tabLayout, binding.viewPager,
            object : TabLayoutMediator.TabConfigurationStrategy {
                override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {

                    tab.text = when (position) {
                        0 -> {
                            "Photo"
                        }
                        1 -> {
                            "Coordinator"
                        }
                        2 -> {
                            "Constraint"
                        }
                        else -> "Onother"

                    }
                }
            }).attach()
    }
    companion object{
        fun newInstance() = ViewPagerFragment()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
