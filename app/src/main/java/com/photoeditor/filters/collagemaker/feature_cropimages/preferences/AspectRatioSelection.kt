package com.photoeditor.filters.collagemaker.feature_cropimages.preferences

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.photoeditor.filters.collagemaker.animatedlist.AnimatedInfiniteLazyRow
import com.photoeditor.filters.collagemaker.animatedlist.model.AnimationProgress
import com.photoeditor.filters.collagemaker.cropimages.imagecropper.models.CropAspectRatio
import com.photoeditor.filters.collagemaker.cropimages.imagecropper.models.aspectRatios
import com.photoeditor.filters.collagemaker.cropimages.imagecropper.widget.AspectRatioSelectionCard


@Composable
internal fun AnimatedAspectRatioSelection(
    modifier: Modifier = Modifier,
    initialSelectedIndex: Int = 2,
    onAspectRatioChange: (CropAspectRatio) -> Unit
) {

    var currentIndex by remember { mutableStateOf(initialSelectedIndex) }

    AnimatedInfiniteLazyRow(
        modifier = modifier.padding(horizontal = 10.dp),
        items = aspectRatios,
        inactiveItemPercent = 80,
        initialFirstVisibleIndex = initialSelectedIndex - 2
    ) { animationProgress: AnimationProgress, index: Int, item: CropAspectRatio, width: Dp ->

        val scale = animationProgress.scale
        val color = animationProgress.color
        val selectedLocalIndex = animationProgress.itemIndex

        AspectRatioSelectionCard(modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .width(width),
            contentColor = MaterialTheme.colorScheme.surface,
            color = color,
            cropAspectRatio = item
        )

        if (currentIndex != selectedLocalIndex) {
            currentIndex = selectedLocalIndex
            onAspectRatioChange(aspectRatios[selectedLocalIndex])
        }
    }
}