package test.interview.extraedgeassignment.ui.single_rocket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import test.interview.extraedgeassignment.databinding.ItemSingleRocketModelBinding
import test.interview.extraedgeassignment.utils.setFlickerImage

class SingleRocketFlickerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val flickerImages = ArrayList<String>()

    inner class RocketItemViewModel(val binding: ItemSingleRocketModelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flickerUrl: String) {
            binding.flickerImage.setFlickerImage(flickerUrl)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RocketItemViewModel(
            ItemSingleRocketModelBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RocketItemViewModel).bind(flickerImages[holder.absoluteAdapterPosition])
    }

    override fun getItemCount() = flickerImages.size

    fun setFlickerImages(images: List<String>) {
        notifyItemRangeRemoved(0, itemCount)
        flickerImages.clear()
        flickerImages.addAll(images)
        notifyItemRangeInserted(0, flickerImages.size)
    }
}