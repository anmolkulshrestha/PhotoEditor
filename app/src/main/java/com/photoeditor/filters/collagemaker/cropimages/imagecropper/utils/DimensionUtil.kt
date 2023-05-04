package com.photoeditor.filters.collagemaker.cropimages.imagecropper.utils

fun calculateFraction(start: Float, end: Float, pos: Float): Float {
    return if ((start - end) == 0f) 0f else (pos - start) / (end - start).coerceIn(0f, 1f)
}

fun lerp(start: Float, end: Float, fraction: Float): Float {
    return start + (end - start) * fraction.toFloat()

}

fun scale(start1: Float, end1: Float, start2: Float, end2: Float, pos: Float): Float {
    return lerp(start2, end2, calculateFraction(start1, end1, pos))
}