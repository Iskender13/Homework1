package com.example.homework12.onBoarding

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.homework12.adapter.OnBoardingAdapter
import com.example.homework12.data.local.Pref
import com.example.homework12.databinding.FragmentOnBoardingBinding
import me.relex.circleindicator.CircleIndicator3


class onBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private val adapter =OnBoardingAdapter(this::onClick)


    private val pref by lazy {
        Pref(requireContext())
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewpager.adapter=adapter



    }
    private fun onClick(){
        pref.onShowed()
        findNavController().navigateUp()
    }

}

