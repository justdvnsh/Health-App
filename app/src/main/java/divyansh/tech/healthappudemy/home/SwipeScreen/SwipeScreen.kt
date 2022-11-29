package divyansh.tech.healthappudemy.home.SwipeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import divyansh.tech.healthappudemy.R
import divyansh.tech.healthappudemy.utils.tinderLikeSwipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .fillMaxHeight(0.6f)
            .fillMaxWidth(0.85f)
            .padding(12.dp)) {
            SwipeCard(
                modifier = Modifier.fillMaxSize(),
                onSwiped = {}
            ) {
                Image(
                    painter = painterResource(id = R.drawable.dog), contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
            SwipeCard(
                modifier = Modifier.fillMaxSize(),
                onSwiped = {}
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cat), contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
            SwipeCard(
                modifier = Modifier.fillMaxSize(),
                onSwiped = {}
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lion), contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SwipeCard(
    modifier: Modifier = Modifier,
    onSwiped: (result: Boolean) -> Unit,
    content: @Composable (BoxScope.() -> Unit)
) {
    val swiped = remember { mutableStateOf(false) }
    BoxWithConstraints(modifier = modifier) {
        if (swiped.value.not()) {
            Card(
                modifier = Modifier
                    .tinderLikeSwipe(
                        maxWidth = constraints.maxWidth.toFloat(),
                        maxHeight = constraints.maxHeight.toFloat(),
                        onDragAccepted = {
                            swiped.value = true
                            onSwiped(true)
                        },
                        onDragRejected = {
                            swiped.value = true
                            onSwiped(false)
                        }
                    ),
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colors.primary
            ) {
                content()
            }
        }
    }
}