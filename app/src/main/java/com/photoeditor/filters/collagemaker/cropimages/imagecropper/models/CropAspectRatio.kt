package com.photoeditor.filters.collagemaker.cropimages.imagecropper.models

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Immutable

@Immutable
data class AspectRatio(val value: Float) {
    companion object {
        val Unspecified = AspectRatio(-1f)
    }
}

@Immutable
data class CropAspectRatio(
    val shape: GenericShape,
    val title: String,
    val aspectRatio: AspectRatio = AspectRatio.Unspecified,
    val icons: List<Int> = listOf(),
)