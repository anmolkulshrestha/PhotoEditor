package com.photoeditor.filters.collagemaker.cropimages.imagecropper.utils


import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope

import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.photoeditor.filters.collagemaker.cropimages.imagecropper.models.AspectRatio
import com.photoeditor.filters.collagemaker.cropimages.imagecropper.state.TransformState

fun DrawScope.DrawGrid(rect: Rect, color: androidx.compose.ui.graphics.Color, strokeWidth: Float) {
    val gridwidth=rect.width/3
    val gridheight=rect.height/3

    for(i in 1..2){
        drawLine(color = color,
            start = Offset(rect.left,rect.top+gridheight*i),
            end = Offset(rect.right,rect.top+gridheight*i),
        strokeWidth = strokeWidth)
    }

    for(i in 1..2){
        drawLine(color = color,
            start = Offset(rect.left+i*gridwidth,rect.top),
            end = Offset(rect.left+i*gridwidth,rect.bottom),
            strokeWidth = strokeWidth)
    }

}

fun DrawScope.drawChecker(){

    val width = this.size.width
    val height = this.size.height
    val checkerWidth = 10.dp.toPx()
    val checkerHeight = 10.dp.toPx()

    val horizontalSteps = (width/checkerWidth).toInt()
    val verticalSteps = (height/checkerHeight).toInt()

    for(i in 0..horizontalSteps){
        for(j in 0..verticalSteps){
            val isGrayTile = ((i+j)%2==1)
            drawRect(  color = if (isGrayTile) androidx.compose.ui.graphics.Color.LightGray else androidx.compose.ui.graphics.Color.White,
                topLeft = Offset(i * checkerWidth, j * checkerHeight),
                size = Size(checkerWidth, checkerHeight)
            )
        }
    }

}

fun DrawScope.DrawWithLayer(block: DrawScope.() -> Unit){
    with(drawContext.canvas.nativeCanvas){
        val checkpoint = saveLayer(null,null)
        block()
        restoreToCount(checkpoint)
    }
}

fun Modifier.drawWithLayer(block: DrawScope.() -> Unit) = this.then(
    Modifier.drawWithContent {
        DrawWithLayer {
            block()
        }
    }
)


fun DrawScope.drawBlockWithCheckerAndLayer(
    dstBitmap: ImageBitmap,
    block: DrawScope.() -> Unit,
) {
    drawChecker()
    DrawWithLayer {

        val canvasWidth = size.width.toInt()
        val canvasHeight = size.height.toInt()

        // Destination
        block()

        // Source
        drawImage(
            image = dstBitmap,
            dstSize = IntSize(canvasWidth, canvasHeight),
            blendMode = BlendMode.SrcIn
        )
    }
}



fun Modifier.drawOutlineWithBlendModeAndChecker(
    aspectRatio: AspectRatio,
    shape: Shape,
    density: Density,
    dstBitmap: ImageBitmap,
    coefficient: Float = .9f,
    color: Color = Color.Red,
) = this.then(
    Modifier.drawWithCache {

        val (offset, outline) = buildOutline(
            aspectRatio,
            coefficient,
            shape,
            size,
            layoutDirection,
            density
        )

        onDrawWithContent {
            drawBlockWithCheckerAndLayer(dstBitmap) {
                translate(left = offset.x, top = offset.y) {
                    drawOutline(
                        outline = outline,
                        color = color,
                    )
                }
            }
        }
    }
)

internal fun GraphicsLayerScope.update(transformState: TransformState) {

    // Set zoom
    val zoom = transformState.zoom
    this.scaleX = zoom
    this.scaleY = zoom

    // Set pan
    val pan = transformState.pan
    val translationX = pan.x
    val translationY = pan.y
    this.translationX = translationX
    this.translationY = translationY

    // Set rotation
    this.rotationZ = transformState.rotation
}
