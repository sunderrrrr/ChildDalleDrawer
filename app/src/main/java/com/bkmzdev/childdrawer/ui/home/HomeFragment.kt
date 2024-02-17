package com.bkmzdev.childdrawer.ui.home

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

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val prompt_input = binding.promptTE
        val textView: TextView = binding.textHome
        val prompt_text = prompt_input.text.toString()
        prompt_input.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                if(prompt_text.isNotEmpty()) {
                    Toast.makeText(context, prompt_text, Toast.LENGTH_SHORT).show()
                    Log.d("Bkmz7692", "prompt: $prompt_text")
                    println("PROMPT $prompt_text")
                }
                else{
                    Toast.makeText(context, "Empty prompt", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}