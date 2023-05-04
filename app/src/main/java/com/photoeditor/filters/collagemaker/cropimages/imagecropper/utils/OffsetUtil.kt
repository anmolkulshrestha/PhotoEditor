package com.photoeditor.filters.collagemaker.cropimages.imagecropper.utils

import androidx.compose.ui.geometry.Offset

fun Offset.coerceIn(
    horizontalRange: ClosedRange<Float>,
    verticalRange: ClosedRange<Float>
) = Offset(this.x.coerceIn(horizontalRange), this.y.coerceIn(verticalRange))