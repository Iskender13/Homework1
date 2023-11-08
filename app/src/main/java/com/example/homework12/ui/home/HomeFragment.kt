package com.example.homework12.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.homework12.App
import com.example.homework12.Model.Task
import com.example.homework12.R
import com.example.homework12.databinding.FragmentHomeBinding
import com.example.homework12.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val adapter=TaskAdapter(this::onLongClick, this::onClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter=adapter
        setData()
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.taskFragment3)
        }
    }
    private fun onClick(task: Task){
        findNavController().navigate(R.id.taskFragment3, bundleOf(TASK_DATA to task))
    }
    private fun onLongClick(task: Task){
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder
            .setMessage("Do you want delete this item?")
            .setTitle("Delete")
            .setPositiveButton("Da") { dialog, which ->
                App.db.taskDao().delete(task)
                setData()
            }
            .setNegativeButton("Net") { dialog, which ->
                dialog.cancel()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun setData(){
        val data=App.db.taskDao().getAll()
        adapter.addTasks(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    companion object{
        const val TASK_DATA="task.data"
    }
}