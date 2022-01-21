package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

internal class MainFragment : Fragment(R.layout.fragment_main) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.main_recycler_view)
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        recyclerView.adapter = SampleAdapter {
            when(it) {
                "DiceRoll" -> {
                    startActivity(DiceRollActivity::class.java)
                }
                "CoroutinesPlayground" -> {
                    startActivity(CoroutinesPlaygroundActivity::class.java)
                }
                else -> {
                    showNotImplementedToast()
                }
            }
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
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<SampleViewHolder>() {
    private val activityNames = listOf(
        "DiceRoll",
        "CoroutinesPlayground",
        "dummy",
        "dummy",
        "dummy",
        "dummy",
        "dummy",
        "dummy",
        "dummy"
    )
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val viewHolder = SampleViewHolder(parent)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClicked(activityNames[position])
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(activityNames[position])
    }

    override fun getItemCount(): Int {
        return activityNames.size
    }
}