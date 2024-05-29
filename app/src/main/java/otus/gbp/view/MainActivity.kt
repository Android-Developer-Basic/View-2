package otus.gbp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import otus.gbp.view.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)

        Log.i(TAG, "onCreate. Current likes in second component: ${binding.likeDislike2.likes}")
        Log.i(TAG, "onCreate. Setting likes in second component...")
        binding.likeDislike2.likes = 100
        Log.i(TAG, "onCreate. New likes in second component: ${binding.likeDislike2.likes}")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}