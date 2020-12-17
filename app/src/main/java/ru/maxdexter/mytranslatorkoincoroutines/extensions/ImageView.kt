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
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                stopRefreshAnimationIfNeeded(binding)
                setImageResource(R.drawable.ic_brocken_img)
                return false
            }
            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                stopRefreshAnimationIfNeeded(binding)
                return false
            }
        })
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .centerInside()
        )
        .into(this)
}

private fun stopRefreshAnimationIfNeeded(binding: FragmentDetailBinding) {
    if (binding.descriptionScreenSwipeRefreshLayout.isRefreshing) {
        binding.descriptionScreenSwipeRefreshLayout.isRefreshing = false
    }
}