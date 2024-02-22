package com.bkmzdev.childdrawer.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bkmzdev.childdrawer.R
import com.bkmzdev.childdrawer.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {


    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ideas = ArrayList<String>()
        ideas.add("Синяя сова с большим носом на дереве")
        ideas.add("Цветные кролики с оленьими рогами")
        ideas.add("Лошадь с крыльями в солнцезашитных очках в небе")
        ideas.add("Синяя сова с большим носом на дереве")
        val list = binding.List
        val adapter = ArrayAdapter(requireActivity(), R.layout.ideas_list_item,R.id.idea, ideas)
        list.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}