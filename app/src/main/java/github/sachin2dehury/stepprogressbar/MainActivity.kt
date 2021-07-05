package github.sachin2dehury.stepprogressbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import github.sachin2dehury.stepprogressbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf<Int>(1, 2, 3, 4, 5, 6)

        binding.spb.viewPager = binding.viewPager2
        binding.viewPager2.adapter = PagerAdapter(items)

        binding.spb.segmentCount = items.size
    }
}
