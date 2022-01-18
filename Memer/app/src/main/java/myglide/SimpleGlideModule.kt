package myglide

import android.content.Context
import com.bumptech.glide.GlideBuilder

import androidx.annotation.NonNull
import com.bumptech.glide.annotation.GlideModule

import com.bumptech.glide.module.AppGlideModule


@GlideModule
class SampleGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
    }
}