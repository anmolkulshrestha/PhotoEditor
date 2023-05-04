package com.photoeditor.filters.collagemaker.cropimages.imagecropper.models

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect

data class CropData(
    val Zoom: Float = 1f,
    val overlayRect: Rect,
    val cropRect: Rect,
    val pan: Offset = Offset.Zero,
    val rotation: Float = 0f
)