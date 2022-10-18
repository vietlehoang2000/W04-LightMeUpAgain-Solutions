package au.edu.swin.sdmd.w03_lightmeup_answers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainActivity : AppCompatActivity() {
    val imageViewModel: ImageViewModel by viewModels()

    companion object {
        private const val KEY_IMAGE = "IMAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        imageViewModel.image.observe(this, Observer { image ->
            imageView.setImageDrawable(getDrawable(image))
        })
        /*savedInstanceState?.let {
            state = it.getInt(KEY_IMAGE)
            imageView.setImageDrawable(AppCompatResources.getDrawable(this, state))
        }*/

        imageView.setOnClickListener {
            // update the image state
            imageViewModel.nextImage()
            // then show it on screen
            //imageView.setImageDrawable(AppCompatResources.getDrawable(this, state))

        }

        /*override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_IMAGE, state)
    }*/
    }
}

class ImageViewModel: ViewModel() {
    var image = MutableLiveData<Int>()

    init {
        image.value = R.drawable.ic_assignment_turned_in_24px
    }
    // this keeps track of the current image
//    var state = R.drawable.ic_assignment_turned_in_24px

    fun nextImage() {
        image.value = when (image.value) {
            R.drawable.ic_assignment_turned_in_24px -> R.drawable.ic_assignment_late_24px
            R.drawable.ic_assignment_late_24px -> R.drawable.ic_assignment_returned_black_24dp
            R.drawable.ic_assignment_returned_black_24dp -> R.drawable.ic_assignment_turned_in_24px
            else -> R.drawable.ic_assignment_turned_in_24px
        }
    }

}