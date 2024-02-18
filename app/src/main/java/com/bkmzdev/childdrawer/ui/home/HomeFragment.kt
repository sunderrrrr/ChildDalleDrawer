package com.bkmzdev.childdrawer.ui.home

import HomeViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bkmzdev.childdrawer.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    lateinit var homeViewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = binding.button
        val promptTE = binding.promptTE
        val imageView = binding.imageView
        button.setOnClickListener(){
            if(promptTE.text.toString().isNotEmpty()){
                homeViewModel.fetchImageFromDALL_E_3(promptTE.text.toString()) { imageUrl ->
                    if (!imageUrl.isNullOrBlank()) {
                        activity?.runOnUiThread {
                            Glide.with(this)
                                .load(imageUrl)
                                .into(imageView)
                        }
                    }
                }
            }
        }
    }

        override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}