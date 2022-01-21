package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

internal class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.main_recycler_view)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        val adapter = SampleAdapter {
            when(it) {
                MainViewModel.Project.DICE_ROLL -> {
                    startActivity(DiceRollActivity::class.java)
                }s
                MainViewModel.Project.COROUTINES_PLAYGROUND -> {
                    startActivity(CoroutinesPlaygroundActivity::class.java)
                }
                MainViewModel.Project.DUMMY -> {
                    showNotImplementedToast()
                }
            }
        }
        recyclerView.adapter = adapter
        viewModel.projects.observe(viewLifecycleOwner) {
            adapter.projects = it
        }
    }

    private fun startActivity(cls: Class<*>) {
        startActivity(Intent(requireActivity(), cls))
    }

    private fun showNotImplementedToast() {
        Toast.makeText(requireContext(), "not implemented", Toast.LENGTH_SHORT).show()
    }
}

private class SampleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(inflateItemView(parent)) {
    private val textView: TextView = itemView.findViewById(R.id.text)

    fun bind(text: String) {
        textView.text = text
    }

    companion object {
        private fun inflateItemView(parent: ViewGroup): View {
            val layoutInflater = LayoutInflater.from(parent.context)
            return layoutInflater.inflate(R.layout.project_list_item_sample, parent, false)
        }
    }
}

private class SampleAdapter(
    private val onItemClicked: (MainViewModel.Project) -> Unit
) : RecyclerView.Adapter<SampleViewHolder>() {
    var projects: List<MainViewModel.Project> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val viewHolder = SampleViewHolder(parent)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClicked(projects[position])
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(projects[position].activityName)
    }

    override fun getItemCount(): Int {
        return projects.size
    }
}