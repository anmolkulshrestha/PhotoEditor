package com.photoeditor.filters.collagemaker.cropimages.imagecropper.utils

import android.util.LayoutDirection
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.Density
import com.photoeditor.filters.collagemaker.cropimages.imagecropper.models.AspectRatio
import kotlin.math.cos
import kotlin.math.sin


fun createPolygonPath(cx: Float, cy: Float, radius: Float, sides: Int): Path {
    val angel = (2 * Math.PI) / sides

    return Path().apply {
        moveTo(
            cx + (radius * cos(0.0f)).toFloat(),
            cx + (radius * sin(0.0f)).toFloat(),
        )

        for(i in 1 until sides){
            lineTo(cx+(radius*cos(i*angel)).toFloat(),
                   cy+(radius*sin(i*angel)).toFloat(),
            )
        }

        close()
    }

}

fun createPolygonShape(sides: Int, degrees: Float = 0f): GenericShape {
    return GenericShape { size: Size, _: androidx.compose.ui.unit.LayoutDirection ->

        val radius = size.width.coerceAtMost(size.height) / 2
        addPath(
            createPolygonPath(
                cx = size.width / 2,
                cy = size.height / 2,
                sides = sides,
                radius = radius
            )
        )
        val matrix = android.graphics.Matrix()
        matrix.postRotate(degrees, size.width / 2, size.height / 2)
        this.asAndroidPath().transform(matrix)
    }
}

fun createRectShape(aspectRatio: AspectRatio): GenericShape {
    return GenericShape { size: Size, _: androidx.compose.ui.unit.LayoutDirection ->
        val value = aspectRatio.value

        val width = size.width
        val height = size.height
        val shapeSize =
            if (aspectRatio == AspectRatio.Unspecified) Size(width, height)
            else if (value > 1) Size(width = width, height = width / value)
            else Size(width = height * value, height = height)

        addRect(Rect(offset = Offset.Zero, size = shapeSize))
    }
}

fun Path.scaleAndTranslatePath(width:Float,height:Float){
    val pathsize= getBounds().size
    val matrix=android.graphics.Matrix()
    matrix.postScale(width/pathsize.width,height/pathsize.height)
    this.asAndroidPath().transform(matrix)
    val left = getBounds().left
    val top = getBounds().top
    translate(Offset(-left, -top))
}


fun calculateSizeAndOffsetFromAspectRation(
    size:Size,
    aspectRatio: AspectRatio,
    coefficient:Float
):Pair<Offset,Size>{
    val width:Float = size.width
    val height:Float = size.height
    val value=aspectRatio.value
    val newSize:Size = if(aspectRatio==AspectRatio.Unspecified){
        Size(width*coefficient, height*coefficient)
    }
    else if(value>1){
        Size(width*coefficient, width*coefficient/value)
    }
    else{
        Size(height*value*coefficient,height*coefficient)
    }


    val left = (width - newSize.width) / 2
    val top = (height - newSize.height) / 2

    return Pair(Offset(left,top),newSize)
}

fun buildOutline(
    aspectRatio: AspectRatio,
    coefficient: Float,
    shape: Shape,
    size: Size,
    layoutDirection: androidx.compose.ui.unit.LayoutDirection,
    density: Density
): Pair<Offset, Outline> {

    val (offset, shapeSize) = calculateSizeAndOffsetFromAspectRation(size,aspectRatio,coefficient)

    val outline = shape.createOutline(
        size = shapeSize,
        layoutDirection = layoutDirection,
        density = density
    )
    return Pair(offset, outline)
}