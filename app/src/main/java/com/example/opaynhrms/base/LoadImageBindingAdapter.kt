package com.example.opaynhrms.base

import androidx.appcompat.widget.AppCompatImageView
import com.example.opaynhrms.R
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView


class LoadImageBindingAdapter {
    companion object{
        @JvmStatic
         fun loadImage(view: AppCompatImageView, profileImage: String?, error: Int) {
            if (!profileImage.isNullOrEmpty()) {
                Picasso.get().load(profileImage).placeholder(R.drawable.sliderplaceholder).into(view)

            }
        }
        @JvmStatic
         fun roundedloadImage(view: CircleImageView, profileImage: String?, error: Int) {
            if (!profileImage.isNullOrEmpty()) {
                Picasso.get().load(profileImage).placeholder(R.drawable.sliderplaceholder).into(view)
            }

        }
    }
}