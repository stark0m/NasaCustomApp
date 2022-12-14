package com.example.nasacustomapp.view.maincontent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.example.nasacustomapp.R
import com.example.nasacustomapp.databinding.FragmentPhotoOfTheDayBinding
import com.example.nasacustomapp.model.viewmodel.AppState
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.example.nasacustomapp.utils.AppUtils
import com.example.nasacustomapp.utils.ANIMATION_LENGTH

class PhotoOfTheDayFragment : Fragment() {
    private var _binding: FragmentPhotoOfTheDayBinding? = null
    private val binding get() = _binding!!
    private var isFlag = true
    private val viewModelNasaFragment: NasaViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NasaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoOfTheDayBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelNasaFragment.getObserver().observe(viewLifecycleOwner) { doAction(it) }
        viewModelNasaFragment.getData()
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
                    AppUtils.toast(requireContext(), "???????????? ????????????")
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
            lifecycle(this@PhotoOfTheDayFragment)
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

        addPODAnimation()
    }

    private fun addPODAnimation() {
        binding.imageView.setOnClickListener {
            isFlag = !isFlag
            val params = it.layoutParams as ConstraintLayout.LayoutParams
            val transitionSet = TransitionSet()
            val changeImageTransform = ChangeImageTransform()
            val changeBounds = ChangeBounds()
            changeBounds.duration = ANIMATION_LENGTH
            changeImageTransform.duration = ANIMATION_LENGTH
            transitionSet.ordering = TransitionSet.ORDERING_TOGETHER
            transitionSet.addTransition(changeBounds)
            transitionSet.addTransition(changeImageTransform)
            TransitionManager.beginDelayedTransition(binding.root, transitionSet)
            if (isFlag) {
                params.height = ConstraintLayout.LayoutParams.MATCH_PARENT
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            } else {
                params.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
                binding.imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
            }
            binding.imageView.layoutParams = params
        }
    }

    companion object {
        fun newInstance() =
            PhotoOfTheDayFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }
}