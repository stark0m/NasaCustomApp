package com.example.nasacustomapp.view.maincontent

import android.graphics.Color.blue
import android.graphics.Color.red
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.text.clearSpans
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import coil.load
import com.example.nasacustomapp.R
import com.example.nasacustomapp.databinding.BottomSheetLayoutBinding
import com.example.nasacustomapp.databinding.FragmentPhotoOfTheDayBinding
import com.example.nasacustomapp.model.viewmodel.AppState
import com.example.nasacustomapp.model.viewmodel.NasaViewModel
import com.example.nasacustomapp.utils.AppUtils
import com.example.nasacustomapp.utils.ANIMATION_LENGTH

class PhotoOfTheDayFragment : Fragment() {
    private var _binding: FragmentPhotoOfTheDayBinding? = null
    private val binding get() = _binding!!
    private var isFlag = true
    private lateinit var spannableRainbow: SpannableString
    private lateinit var description: TextView
    private val viewModelNasaFragment: NasaViewModel by lazy {
        ViewModelProvider(requireActivity()).get(NasaViewModel::class.java)
    }
    private val map: Map<Int, Int> by lazy {
        mapOf(
            0 to ContextCompat.getColor(requireContext(), R.color.red),
            1 to ContextCompat.getColor(requireContext(), R.color.orange),
            2 to ContextCompat.getColor(requireContext(), R.color.yellow),
            3 to ContextCompat.getColor(requireContext(), R.color.green),
            4 to ContextCompat.getColor(requireContext(), R.color.blue),
            5 to ContextCompat.getColor(requireContext(), R.color.purple_700)
        )
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
            lifecycle(this@PhotoOfTheDayFragment)
            error(R.drawable.ic_load_error_vector)
            placeholder(R.drawable.ic_no_photo_vector)
            crossfade(true)
        }

        with(responce.serverResponce) {
            description = requireView().findViewById(R.id.bottomSheetDescription)
            description.typeface = Typeface.createFromAsset(requireActivity().assets,"fonts/azret.ttf")
            val title: TextView = requireView().findViewById(R.id.title_text)
            title.text = this.title

            description.text = this.explanation
            spannableRainbow = SpannableString(description.text)
            rainbowText()
        }
        addPODAnimation()
    }

    private fun rainbowText() {
        Thread() {
            var currentCount = 1
            while (true) {
                Thread.sleep(500L)
                Handler(Looper.getMainLooper()).post() {
                    applyRainbowToText(currentCount)
                }
                currentCount = if (++currentCount > 5) 1 else currentCount
            }
        }.start()
    }

    private fun applyRainbowToText(currentCount: Int) {
        description.setText(spannableRainbow, TextView.BufferType.SPANNABLE)
        spannableRainbow = description.text as SpannableString
        clearSpans()
        var colorNumber = currentCount
        for (i in 0 until description.text.length) {
            if (colorNumber == 5) colorNumber = 0 else colorNumber += 1

            if(description.text[i]!='a'){
                spannableRainbow.setSpan(
                    ForegroundColorSpan(map.getValue(colorNumber)),
                    i, i + 1,
                    Spannable.SPAN_EXCLUSIVE_INCLUSIVE
                )
            } else
            {
                val span = spannableRainbow.getSpans(
                    i,i+1,
                    RelativeSizeSpan::class.java
                )
                span.forEach {
                    spannableRainbow.removeSpan(it)
                }

                spannableRainbow.setSpan(
                    RelativeSizeSpan(2f),
                    i, i + 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )

            }

        }
    }

    private fun clearSpans() {
        val spans = spannableRainbow.getSpans(
            0, spannableRainbow.length,
            ForegroundColorSpan::class.java
        )
        for (span in spans) {
            spannableRainbow.removeSpan(span)
        }
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