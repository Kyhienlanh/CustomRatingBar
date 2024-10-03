package com.example.customratingbar
import kotlin.random.Random
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color
import android.widget.Button
import android.widget.LinearLayout // Nhập LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var viewContainer: LinearLayout // Khai báo viewContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Tìm kiếm viewContainer từ layout
        viewContainer = findViewById(R.id.main)

        ViewCompat.setOnApplyWindowInsetsListener(viewContainer) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val customRatingBar = findViewById<CustomRatingBar>(R.id.customRatingBar)
        customRatingBar.setRating(3f) // Đặt xếp hạng ban đầu
        val button=findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            val rating = customRatingBar.getRating()

            val feedback = when (rating.toInt()) {
                1 -> "Rất tệ"
                2 -> "Tệ"
                3 -> "Bình thường"
                4 -> "Tốt"
                5 -> "Rất tốt"
                else -> "Không hợp lệ"
            }

            Toast.makeText(this, "Bạn đánh giá: $feedback", Toast.LENGTH_SHORT).show()
        }
    }
}