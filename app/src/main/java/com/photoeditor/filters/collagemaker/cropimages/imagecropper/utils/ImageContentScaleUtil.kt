package com.photoeditor.filters.collagemaker.cropimages.imagecropper.utils

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize

internal fun BoxWithConstraintsScope.getParentSize(
    bitmapWidth: Int,
    bitmapHeight: Int
): IntSize {
    // Check if Composable has fixed size dimensions
    val hasBoundedDimens = constraints.hasBoundedWidth && constraints.hasBoundedHeight
    // Check if Composable has infinite dimensions
    val hasFixedDimens = constraints.hasFixedWidth && constraints.hasFixedHeight

    // Box is the parent(BoxWithConstraints) that contains Canvas under the hood
    // Canvas aspect ratio or size might not match parent but it's upper bounds are
    // what are passed from parent. Canvas cannot be bigger or taller than BoxWithConstraints
    val boxWidth: Int = if (hasBoundedDimens || hasFixedDimens) {
        constraints.maxWidth
    } else {
        constraints.minWidth.coerceAtLeast(bitmapWidth)
    }
    val boxHeight: Int = if (hasBoundedDimens || hasFixedDimens) {
        constraints.maxHeight
    } else {
        constraints.minHeight.coerceAtLeast(bitmapHeight)
    }
    return IntSize(boxWidth, boxHeight)
}


internal fun getScaledBitmapRect(
    boxWidth: Int,
    boxHeight: Int,
    imageWidth: Float,
    imageHeight: Float,
    bitmapWidth: Int,
    bitmapHeight: Int
): IntRect {
    // Get scale of box to width of the image
    // We need a rect that contains Bitmap bounds to pass if any child requires it
    // For a image with 100x100 px with 300x400 px container and image with crop 400x400px
    // So we need to pass top left as 0,50 and size
    val scaledBitmapX = boxWidth / imageWidth
    val scaledBitmapY = boxHeight / imageHeight

    val topLeft = IntOffset(
        x = (bitmapWidth * (imageWidth - boxWidth) / imageWidth / 2)
            .coerceAtLeast(0f).toInt(),
        y = (bitmapHeight * (imageHeight - boxHeight) / imageHeight / 2)
            .coerceAtLeast(0f).toInt()
    )

    val size = IntSize(
        width = (bitmapWidth * scaledBitmapX).toInt().coerceAtMost(bitmapWidth),
        height = (bitmapHeight * scaledBitmapY).toInt().coerceAtMost(bitmapHeight)
    )

    return IntRect(offset = topLeft, size = size)
}