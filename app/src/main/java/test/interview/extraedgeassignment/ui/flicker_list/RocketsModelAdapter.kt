package test.interview.extraedgeassignment.ui.flicker_list

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import test.interview.extraedgeassignment.data.model.RocketsItem
import test.interview.extraedgeassignment.databinding.ItemEntryModelBinding
import test.interview.extraedgeassignment.utils.RocketListListener
import test.interview.extraedgeassignment.utils.setFlickerImage

class RocketsModelAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val rocketsList = ArrayList<RocketsItem>()
    private lateinit var context: Context
    private lateinit var rocketListListener: RocketListListener

    inner class RocketItemViewModel(val binding: ItemEntryModelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                rocketListListener.onRocketClicked(rocketsList[absoluteAdapterPosition].id)
            }
        }

        fun bind(rocketsItem: RocketsItem) {
            with(rocketsItem) {
                Log.i("TAG", "bind: $this")
                binding.event.text = String.format("Name: %s", name)
                binding.countryTextView.text = String.format("Country: %s", country)
                binding.enginesCount.text = String.format("Engines Count: %d", engines.number)

                if (flickr_images.isNotEmpty()) {
                    binding.flickerImage.visibility = View.VISIBLE
                    binding.flickerImage.setFlickerImage(flickr_images[0])
                } else {
                    binding.flickerImage.visibility = View.GONE
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RocketItemViewModel(
            ItemEntryModelBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RocketItemViewModel).bind(rocketsList[holder.absoluteAdapterPosition])
    }

    override fun getItemCount() = rocketsList.size

    fun insertItemsToList(list: ArrayList<RocketsItem>) {
        rocketsList.clear()
        rocketsList.addAll(list)
        notifyItemRangeRemoved(0, itemCount)
        notifyItemRangeInserted(0, rocketsList.size)
    }

    fun createContext(context: Context) {
        this.context = context
    }

    fun setListener(rocketListListener: RocketListListener) {
        this.rocketListListener = rocketListListener
    }

}