package otus.gbp.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import otus.gbp.view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var likes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        initLikes()
    }

    private fun initLikes() = with(binding) {
        numLikes.text = likes.toString()
        likeButton.setOnClickListener {
            numLikes.text = (++likes).toString()
        }
        dislikeButton.setOnClickListener {
            numLikes.text = (--likes).coerceAtLeast(0).toString()
        }
    }
}