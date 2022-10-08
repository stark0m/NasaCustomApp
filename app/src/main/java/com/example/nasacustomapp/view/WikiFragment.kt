package com.example.nasacustomapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nasacustomapp.R
import com.example.nasacustomapp.databinding.FragmentPhotoOfTheDayBinding
import com.example.nasacustomapp.databinding.FragmentWikiBinding
import com.example.nasacustomapp.utils.WIKI_PARSE_URL


class WikiFragment : Fragment() {

    private var _binding: FragmentWikiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWikiBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        fun newInstance() =
            WikiFragment().apply {

            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnWikiSearchClickListener()
    }
    private fun setOnWikiSearchClickListener() {

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("$WIKI_PARSE_URL${binding.inputEditText.text.toString()}")
            })
        }




    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}