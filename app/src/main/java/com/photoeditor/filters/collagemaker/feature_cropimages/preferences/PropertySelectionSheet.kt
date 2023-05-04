package com.photoeditor.filters.collagemaker.feature_cropimages.preferences

import androidx.compose.runtime.Composable
import com.photoeditor.filters.collagemaker.cropimages.imagecropper.settings.CropFrameFactory
import com.photoeditor.filters.collagemaker.cropimages.imagecropper.settings.CropProperties


@Composable
internal fun PropertySelectionSheet(
    cropFrameFactory:CropFrameFactory,
    cropProperties: CropProperties,
    onCropPropertiesChange: (CropProperties) -> Unit
) {
    BaseSheet {
        CropPropertySelectionMenu(
            cropFrameFactory =cropFrameFactory,
            cropProperties = cropProperties,
            onCropPropertiesChange = onCropPropertiesChange
        )

        CropGestureSelectionMenu(
            cropProperties = cropProperties,
            onCropPropertiesChange = onCropPropertiesChange
        )
    }
}
