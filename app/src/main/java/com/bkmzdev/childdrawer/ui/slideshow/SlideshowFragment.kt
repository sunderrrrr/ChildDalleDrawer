package com.bkmzdev.childdrawer.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bkmzdev.childdrawer.R
import com.bkmzdev.childdrawer.databinding.FragmentSlideshowBinding


class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val instructionSteps = ArrayList<String>()
        instructionSteps.add("1. Напиши в строку то, что ты хочешь увидеть")
        instructionSteps.add("2. Если не можешь придумать, то загляни в раздел с идеями")
        instructionSteps.add("3. Нажми на кнопку")
        instructionSteps.add("4. Немного подожди и сказка оживет на твоем экране")
        val list = binding.InstructListView
        val adapter = ArrayAdapter(requireActivity(), R.layout.ideas_list_item, R.id.idea, instructionSteps)
        list.adapter = adapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}