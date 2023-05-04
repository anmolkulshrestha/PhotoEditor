package com.photoeditor.filters.collagemaker.feature_pickimages.presentation.pickimagesscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Paid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.photoeditor.filters.collagemaker.R
import com.photoeditor.filters.collagemaker.cropimages.imagecropper.utils.createPolygonShape
import com.photoeditor.filters.collagemaker.utils.AutoResizedText
import androidx.compose.ui.graphics.Color as Color

@Composable
fun PickImagesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(color = 0xFF1c1d1f))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Text(
                "Polish",
                style = MaterialTheme.typography.h4.copy(color = Color.White),
                modifier = Modifier,
            )
            IconButton(onClick = { }, modifier = Modifier) {
                Icon(
                    imageVector = Icons.Default.Paid,
                    contentDescription = "premimum option",
                    tint = Color.White,

                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .shadow(
                        shape = RoundedCornerShape(16.dp),
                        elevation = 10.dp,
                    )
                    .background(Color(0xFF2490ff)),
            ) {
                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth().align(Alignment.Center)) {
                    Icon(painter = painterResource(id = R.drawable.photo_album_fill0_wght400_grad0_opsz48__1_), contentDescription = "", modifier = Modifier.size(30.dp), tint = Color.White)
                    Text("Photo", style = MaterialTheme.typography.h4.copy(color = Color.White, fontSize = 25.sp))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier

                        .size(100.dp)
                        .shadow(elevation = 10.dp, shape = createPolygonShape(6,30f)
                        )
                        .background(Color(0xFF2e2f33)),
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize().align(Alignment.Center),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.collage_svgrepo_com), contentDescription = "", modifier = Modifier.size(30.dp), tint = Color.White)
                        //Text("Collage", style = MaterialTheme.typography.h4.copy(color = Color.White, fontSize = 20.sp))
                        AutoResizedText(text = "Collage", style = MaterialTheme.typography.h4.copy(color = Color.White, fontSize = 15.sp))
                    }
                }
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(16.dp))
                        .background(Color(0xFF2e2f33))
                        .padding(10.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize().align(Alignment.Center),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.template), contentDescription = "", modifier = Modifier.size(30.dp), tint = Color.White)
                        AutoResizedText(text = "Templates", style = MaterialTheme.typography.h4.copy(color = Color.White, fontSize = 15.sp))
//
                    }
                }
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .shadow(elevation = 10.dp, shape = RoundedCornerShape(16.dp))
                        .background(Color(0xFF2e2f33))
                        .padding(10.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize().align(Alignment.Center),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.camera_photo_heart_svgrepo_com), contentDescription = "", modifier = Modifier.size(35.dp), tint = Color.White)
                        AutoResizedText(text = "Camera", style = MaterialTheme.typography.h4.copy(color = Color.White, fontSize = 15.sp))
                    }
                }
            }
        }

        Spacer(Modifier.weight(1f))
    }
}
