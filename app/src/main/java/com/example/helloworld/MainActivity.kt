package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // UI
        val recyclerView: RecyclerView = findViewById(R.id.project_list)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        )
        recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))
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
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    private fun showNotImplementedToast() {
        Toast.makeText(this, "not implemented", Toast.LENGTH_SHORT).show()
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
