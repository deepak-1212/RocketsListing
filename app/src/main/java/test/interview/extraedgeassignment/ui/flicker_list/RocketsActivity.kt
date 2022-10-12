package test.interview.extraedgeassignment.ui.flicker_list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import test.interview.extraedgeassignment.R
import test.interview.extraedgeassignment.data.model.RocketsItem
import test.interview.extraedgeassignment.data.network.CategoriesApi
import test.interview.extraedgeassignment.data.repository.CategoriesRepository
import test.interview.extraedgeassignment.databinding.ActivityMainBinding
import test.interview.extraedgeassignment.ui.single_rocket.SingleRocketActivity
import test.interview.extraedgeassignment.utils.RocketListListener

class RocketsActivity : AppCompatActivity() {

    private lateinit var viewModel: RocketsViewModel
    private lateinit var rocketsViewModelFactory: RocketsViewModelFactory
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var rocketsModelAdapter: RocketsModelAdapter

    private val rocketListListener = object : RocketListListener {
        override fun onRocketClicked(rocketId: String) {
            callSingleRocketScreen(rocketId)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        rocketsModelAdapter = RocketsModelAdapter()
        rocketsModelAdapter.createContext(this)
        rocketsModelAdapter.setListener(rocketListListener)

        activityMainBinding.listView.also {
            it.adapter = rocketsModelAdapter
            it.layoutManager = GridLayoutManager(this, 2)
            it.setHasFixedSize(true)
        }

        val api = CategoriesApi()
        val repository = CategoriesRepository(api)
        rocketsViewModelFactory = RocketsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, rocketsViewModelFactory)[RocketsViewModel::class.java]

        viewModel.getEntryModels()

        viewModel.entryModels.observe(this) {
            rocketsModelAdapter.insertItemsToList(it as ArrayList<RocketsItem>)
        }
    }

    private fun callSingleRocketScreen(rocketId: String) {
        val intent = Intent(this, SingleRocketActivity::class.java)
        intent.putExtra("rocket_id", rocketId)
        startActivity(intent)
    }
}