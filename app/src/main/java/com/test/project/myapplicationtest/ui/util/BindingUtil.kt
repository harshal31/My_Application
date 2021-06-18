package com.test.project.myapplicationtest.ui.util

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.like.LikeButton
import com.like.OnLikeListener
import com.test.project.myapplicationtest.R

/**
 * Created by Harshal Chaudhari on 17/06/21.
 */
object BindingUtil {

    @BindingAdapter(value = ["imageUrl", "gender"])
    @JvmStatic
    fun ImageView.setImageUrl(url: String?, gender: String) {
        val genderPlaceholder = if (gender == "male") R.drawable.male else R.drawable.female
        val options = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC).placeholder(genderPlaceholder)
        Glide.with(context)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .override(width, height)
            .apply(options)
            .into(this)
    }

    @BindingAdapter("likedOrNot")
    @JvmStatic
    fun LikeButton.handleLiked(isLike: Boolean) {
        isLiked = isLike
    }

    @BindingAdapter("isViewVisible")
    @JvmStatic
    fun View.handleVisibility(visible: Boolean) {
        visibility = if (visible) View.VISIBLE else View.GONE
    }

}