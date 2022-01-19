package com.example.helloworld

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
        recyclerView.adapter = SampleAdapter()
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

private class SampleAdapter : RecyclerView.Adapter<SampleViewHolder>() {
    private val activityNames = listOf(
        "DiceRoll",
        "CoroutinesPlayground",
        "hoge",
        "hoge",
        "hoge",
        "hoge",
        "hoge",
        "hoge",
        "hoge"
    )
    // ViewHolder を作成する必要がある時に呼ばれる
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(parent)
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
