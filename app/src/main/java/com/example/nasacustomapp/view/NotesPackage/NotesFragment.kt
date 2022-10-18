package com.example.nasacustomapp.view.NotesPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nasacustomapp.databinding.FragmentNotesBinding
import com.example.nasacustomapp.model.viewmodel.AppState
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import java.net.CacheResponse

class NotesFragment : Fragment() {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val viewModelNasaFragment: NasaViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NasaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root

    }

    companion object {
        fun newInstance() =
            NotesFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelNasaFragment.getObserver().observe(viewLifecycleOwner) { doAction(it) }
        viewModelNasaFragment.getNotesFromServer()
    }

    private fun doAction(response: AppState) {
        when (response) {
            is AppState.NotesReceived -> {
                val noteList = response.noteList
                binding.recyclerViewId.adapter = rvAdapter(noteList)
            }
            else -> {
                //do nothing
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}