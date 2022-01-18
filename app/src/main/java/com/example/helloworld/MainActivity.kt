package com.example.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var leftDiceImage: ImageView
    lateinit var rightDiceImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }
        leftDiceImage = findViewById(R.id.left_dice_image)
        rightDiceImage = findViewById(R.id.right_dice_image)
    }

    private fun rollDice() {
        leftDiceImage.setImageResource(getRandomDiceImage())
        rightDiceImage.setImageResource(getRandomDiceImage())
    }

    private fun getRandomDiceImage(): Int {
        return when ((1..6).random()) {
            1    -> R.drawable.dice_1
            2    -> R.drawable.dice_2
            3    -> R.drawable.dice_3
            4    -> R.drawable.dice_4
            5    -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}