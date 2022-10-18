package com.example.nasacustomapp.view.NotesPackage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nasacustomapp.databinding.FragmentNotesBinding
import com.example.nasacustomapp.model.viewmodel.AppState
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.example.nasacustomapp.utils.Note

class NotesFragment : Fragment() {
    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val noteActionCallback = object : NoteAction {
        override fun addNote(note: Note, position: Int) {
            viewModelNasaFragment.addNote(position, Note())
        }

        override fun removeNote(position: Int) {
            TODO()//  viewModelNasaFragment.removeNote(position)
        }

        override fun moveUpNote(position: Int) {
            TODO()// viewModelNasaFragment.moveUpNote(position)
        }

        override fun moveDownNote(position: Int) {
            TODO("Not yet implemented")
        }
    }

    private val viewModelNasaFragment: NasaViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NasaViewModel::class.java)
    }
    private lateinit var adapter: rvAdapter
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
                adapter = rvAdapter(noteList, noteActionCallback)
                binding.recyclerViewId.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerViewId.adapter = adapter
            }
            is AppState.NoteAddedSuccess -> {
                adapter.setNoteList(viewModelNasaFragment.getNotesFromVM())
                adapter.notifyItemInserted(response.position)

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