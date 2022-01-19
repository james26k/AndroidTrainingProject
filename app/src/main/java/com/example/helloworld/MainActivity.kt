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
// RecyclerView.ViewHolder のコンストラクタには表示するViewのオブジェクトが必要
private class SampleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(inflateItemView(parent)) {
    // itemView は RecyclerView.ViewHolder に定義されているパブリックな変数で、コンストラクタに渡した View オブジェクトが設定される
    private val textView: TextView = itemView.findViewById(R.id.text)
    // データを紐づけるためのメソッド
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
    // ViewHolder を作成する必要がある時に呼ばれる
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val viewHolder = SampleViewHolder(parent)
        viewHolder.itemView.setOnClickListener {
            // ViewHolder#bindingAdapterPosition() で ViewHolder が Adapter のどの位置にいるか取得できる
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClicked(activityNames[position])
            }
        }
        return viewHolder
    }
    // ViewHolder とデータを関連付ける時に呼ばれる
    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(activityNames[position])
    }
    // 表示する要素の数を返す
    override fun getItemCount(): Int {
        return activityNames.size
    }
}
