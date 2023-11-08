package com.example.homework12.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.homework12.R
import com.example.homework12.data.local.Pref
import com.example.homework12.databinding.FragmentProfileBinding
import com.example.homework12.utils.loadImage

class ProfileFragment : Fragment() {
    private val pref by lazy {
        Pref(requireContext())
    }

    private val getCommentMedia = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedFileUri = result.data?.data
            pref.saveImage(selectedFileUri.toString())
            binding.profileImage.loadImage(selectedFileUri.toString())
        }
    }


    private lateinit var binding: FragmentProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.etName.setText(pref.getName())
        binding.profileImage.loadImage(pref.getImage())
        binding.btnSave.setOnClickListener{
            pref.saveName(binding.etName.text.toString())
        }
        binding.profileImage.setOnClickListener{
            val intent=Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            getCommentMedia.launch(intent)
        }
    }
}
