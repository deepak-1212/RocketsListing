package test.interview.extraedgeassignment.ui.single_rocket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import test.interview.extraedgeassignment.R
import test.interview.extraedgeassignment.data.model.RocketsItem
import test.interview.extraedgeassignment.data.network.CategoriesApi
import test.interview.extraedgeassignment.data.repository.CategoriesRepository
import test.interview.extraedgeassignment.databinding.ActivitySingleRocketBinding

class SingleRocketActivity : AppCompatActivity() {

    private lateinit var viewModel: SingleRocketViewModel
    private lateinit var binding: ActivitySingleRocketBinding
    private var rocketId: String = ""
    private lateinit var singleRocketViewModelFactory: SingleRocketViewModelFactory

    private lateinit var singleRocketFlickerAdapter: SingleRocketFlickerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_rocket)

        rocketId = intent.getStringExtra("rocket_id").toString()

        singleRocketFlickerAdapter = SingleRocketFlickerAdapter()

        val api = CategoriesApi()
        val repository = CategoriesRepository(api)
        singleRocketViewModelFactory = SingleRocketViewModelFactory(repository)
        viewModel = ViewModelProvider(this, singleRocketViewModelFactory)[SingleRocketViewModel::class.java]

        viewModel.getRocketItem(rocketId)

        viewModel.rocketItem.observe(this) {
            setView(it!!)
        }


    }

    private fun setView(rocketItem: RocketsItem) {
        with(rocketItem) {

            singleRocketFlickerAdapter.setFlickerImages(flickr_images)

            binding.flickerImages.also {
                it.adapter = singleRocketFlickerAdapter
                it.layoutManager = LinearLayoutManager(this@SingleRocketActivity, LinearLayoutManager.HORIZONTAL, false)
                it.setHasFixedSize(true)
            }

            binding.rocketName.text = name
            binding.activeStatus.text = String.format("Active Status: %s", if (active) "Yes" else "No")
            binding.costPerLaunch.text = String.format("Cost per launch: %d", cost_per_launch)
            binding.successRatePercent.text = String.format("Success rate percent: %d", success_rate_pct)
            binding.description.text = String.format("Description: %s", description)
            binding.wikipediaLink.text = String.format("Wikipedia Link: %s", wikipedia)
            binding.wikipediaLink.text = String.format("Height: %.2f/%.2f\nDiameter: %.2f/%.2f", height.feet, (height.meters*39.3701), diameter.feet, (diameter.meters*39.3701))

        }

    }
}