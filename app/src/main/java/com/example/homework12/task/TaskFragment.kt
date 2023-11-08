package com.example.homework12.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.homework12.App
import com.example.homework12.Model.Task
import com.example.homework12.R
import com.example.homework12.databinding.FragmentTaskBinding
import com.example.homework12.ui.home.HomeFragment


class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentTaskBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)= with(binding) {
        super.onViewCreated(view, savedInstanceState)
        val task=arguments?.getSerializable(HomeFragment.TASK_DATA) as Task?
        task?.let {
            etTitle.setText(it.title)
            etDesc.setText(it.desc)
            btnSave.text=getString(R.string.update)

        }
        btnSave.setOnClickListener{
            if (task==null){
                save()
            }else{
                App.db.taskDao().update(task.copy(title=etTitle.text.toString(), desc =etDesc.text.toString()))
            }
            findNavController().navigateUp()
        }
    }

    private fun save() {
        val data = Task(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()

        )
        App.db.taskDao().insert(data)
    }

}
