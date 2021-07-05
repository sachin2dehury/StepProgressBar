package github.sachin2dehury.stepprogressbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.sachin2dehury.stepprogressbar.databinding.FragmentPageBinding

class PagerAdapter(private val items: List<Int>) :
    RecyclerView.Adapter<PagerAdapter.PageViewHolder>() {

    class PageViewHolder(view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return PageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_page, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val binding = FragmentPageBinding.bind(holder.itemView)
        binding.textView.text = items[position].toString()
    }

    override fun getItemCount(): Int = items.size
}