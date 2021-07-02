package github.sachin2dehury.stepprogressbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_pager.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = listOf<Int>(1, 2, 3, 4, 5, 6)

        val spb = findViewById<SegmentedProgressBar>(R.id.spb)
        spb.viewPager = viewPager2
        viewPager2.adapter = PagerAdapter(items)

        spb.segmentCount = items.size
    }
}
