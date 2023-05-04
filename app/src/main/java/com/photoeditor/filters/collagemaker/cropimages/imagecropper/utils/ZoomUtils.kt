package com.photoeditor.filters.collagemaker.cropimages.imagecropper.utils



internal fun calculateZoom(
    zoomLevel: ZoomLevel,
    initial: Float,
    min: Float,
    max: Float
): Pair<ZoomLevel, Float> {

    val newZoomLevel: ZoomLevel
    val newZoom: Float

    when (zoomLevel) {
        ZoomLevel.Mid -> {
            newZoomLevel = ZoomLevel.Max
            newZoom = max.coerceAtMost(3f)
        }
        ZoomLevel.Max -> {
            newZoomLevel = ZoomLevel.Min
            newZoom = if (min == initial) initial else min
        }
        else -> {
            newZoomLevel = ZoomLevel.Mid
            newZoom = if (min == initial) (min + max.coerceAtMost(3f)) / 2 else initial
        }
    }
    return Pair(newZoomLevel, newZoom)
}

internal fun getNextZoomLevel(zoomLevel: ZoomLevel): ZoomLevel = when (zoomLevel) {
    ZoomLevel.Mid -> {
        ZoomLevel.Max
    }
    ZoomLevel.Max -> {
        ZoomLevel.Min
    }
    else -> {
        ZoomLevel.Mid
    }
}
