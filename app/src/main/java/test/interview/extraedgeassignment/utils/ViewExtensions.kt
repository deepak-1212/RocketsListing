package test.interview.extraedgeassignment.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import test.interview.extraedgeassignment.helper.ExtraEdgeApplication

fun ImageView.setFlickerImage(url: String) {

    Glide.with(ExtraEdgeApplication.getInstance())
        .load(url)
        .into(this)
        .clearOnDetach()

}