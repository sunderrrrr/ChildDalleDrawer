package com.bkmzdev.childdrawer.ui.home

import HomeViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bkmzdev.childdrawer.AdSDK
import com.bkmzdev.childdrawer.R
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
        val AdInst = AdSDK()
        val bannerId = getString(R.string.bannerId)
        val button = binding.button
        val promptTE = binding.promptTE
        val imageView = binding.imageView
        val banner = binding.banner
        println("BANNER ID: $bannerId")
        AdInst.load_banner_ad(requireActivity(), banner, bannerId.toString())
        button.setOnClickListener(){
            if(promptTE.text.toString().isNotEmpty()) {
                Toast.makeText(requireActivity(), "Начинаю генерить", Toast.LENGTH_SHORT).show()
                homeViewModel.fetchImageFromDALL_E_3(promptTE.text.toString()) { imageUrl ->
                    if (imageUrl != "conn_err" && imageUrl != "serv_err" && imageUrl.isNullOrBlank()) {
                        activity?.runOnUiThread {
                            Glide.with(this)
                                .load(imageUrl)
                                .into(imageView)
                        }
                    }
                    if (imageUrl == "conn_err"){
                        activity?.runOnUiThread { Toast.makeText(requireActivity(), "Нет соединения с интернетом", Toast.LENGTH_SHORT).show() }
                    }
                    if (imageUrl == "serv_err"){
                        activity?.runOnUiThread { Toast.makeText(requireActivity(), "Ошибка на сервере", Toast.LENGTH_SHORT).show() }
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