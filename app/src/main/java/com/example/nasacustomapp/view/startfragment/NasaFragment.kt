package com.example.nasacustomapp.view.startfragment

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import coil.load
import com.example.nasacustomapp.R
import com.example.nasacustomapp.databinding.FragmentMainBinding
import com.example.nasacustomapp.model.theme.AppTheme
import com.example.nasacustomapp.model.viewmodel.AppState
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.example.nasacustomapp.utils.AppUtils
import com.example.nasacustomapp.utils.WIKI_PARSE_URL
import com.google.android.material.bottomsheet.BottomSheetDialog

class NasaFragment : Fragment() {


    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModelNasaFragment: NasaViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NasaViewModel::class.java)
    }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelNasaFragment.getObserver().observe(viewLifecycleOwner) { doAction(it) }
        viewModelNasaFragment.getData()

        setListeners()
    }

    private fun setListeners() {

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("$WIKI_PARSE_URL${binding.inputEditText.text.toString()}")
            })
        }


        binding.bottomAppBar.setNavigationOnClickListener {
            showDialogAndSetListeners()
        }

    }

    private fun showDialogAndSetListeners() {
        val dialog = BottomSheetDialog(requireContext())
        dialog.setContentView(R.layout.theme_choose_dialog)
        dialog.show()

        val baseButton = dialog.findViewById<Button>(R.id.base_theme_button)
        val customButton = dialog.findViewById<Button>(R.id.custom_theme_button)

        baseButton!!.setOnClickListener {
            viewModelNasaFragment.setApplicationTheme(AppTheme.LIGHT)
        }
        customButton!!.setOnClickListener {
            viewModelNasaFragment.setApplicationTheme(AppTheme.CUSTOM)
        }
    }




    private fun doAction(responce: AppState) {
        when (responce) {
            is AppState.Error -> {
                AppUtils.toast(requireContext(), responce.error.toString())
            }
            AppState.Loading -> TODO()
            is AppState.Success -> {
                val url = responce.serverResponce.url
                if (url.isNullOrEmpty()) {
                    AppUtils.toast(requireContext(), "Ссылка пустая")
                } else {
                    showPODinFragment(responce)

                }
            }
            else -> {
                //nothing TODO
            }
        }

    }

    private fun showPODinFragment(responce: AppState.Success) {
        val url = responce.serverResponce.url

        binding.imageView.load(url) {
            lifecycle(this@NasaFragment)
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
            crossfade(true)
        }

        with(responce.serverResponce) {
            val description: TextView = requireView().findViewById(R.id.bottomSheetDescription)
            val title: TextView = requireView().findViewById(R.id.title_text)

            description.text = this.explanation
            title.text = this.title

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}