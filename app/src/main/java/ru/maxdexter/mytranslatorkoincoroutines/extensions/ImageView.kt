package ru.maxdexter.mytranslatorkoincoroutines.extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import ru.maxdexter.translatorcoincoroutine.R
import ru.maxdexter.translatorcoincoroutine.databinding.FragmentDetailBinding

fun ImageView.loadImage(binding: FragmentDetailBinding, url: String?){
    Glide.with(this)
        .load("https:$url")
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .centerInside()
        )
        .into(this)
}

