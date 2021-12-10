package com.haman.codelab2nd

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.atLeast
import com.haman.codelab2nd.ui.theme.CodeLab2NdTheme

@Composable
fun ConstraintLayoutContent () {
    ConstraintLayout() {

        // Create references for the composables to constraint
        val (button, text) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            // Assign reference 'button' to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button")
        }

        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 16.dp)
            // Centers Text horizontally in the ConstraintLayout
            centerHorizontallyTo(parent)
        })
    }
}

@Composable
fun ConstraintLayoutContent_2 () {
    ConstraintLayout() {

        // Creates references for the three composables
        // in the ConstraintLayout's body
        val (button1, button2, text) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button1) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button 1")
        }

        Text("Text", Modifier.constrainAs(text) {
            top.linkTo(button1.bottom, margin = 16.dp)
            centerAround(button1.end)
        })

        // barriers can be created in the body of ConstraintLayout, but not in constrainAs
        val barrier = createEndBarrier(button1, text)
        Button(
            onClick = { /* TODO */},
            modifier = Modifier.constrainAs(button2) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(barrier)
            }
        ) {
            Text("Button 2")
        }

    }
}

@Composable
fun LargeConstraintLayout () {
    ConstraintLayout {
        val text = createRef()

        // ??코드랩과는 다르게 다음줄로 넘어가서 글이 다 보인다...
        val guideline = createGuidelineFromStart(fraction = 0.5f)
        Text (
            text = "This is a very very very very very very very very very very long text",
            Modifier.constrainAs(text) {
                linkTo(start = guideline, end = parent.end)
                // 만약 화면에 글이 다 잘려보일 경우
                // preferredWrapContent : the layout is wrap content,
                // subject to the constraints in that dimension.
                width = Dimension.preferredWrapContent.atLeast(100.dp)
                // + wrapContent
                // + fillToConstraints
                // + preferredValue : the layout is a fixed dp value.
                // + value
            }
        )
    }
}

@Composable
fun DecoupledConstraintLayout () {
    BoxWithConstraints() {
        val constraints = if (maxWidth < maxHeight) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 36.dp) // Landscape constraints
        }
        
        ConstraintLayout(constraints) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.layoutId("button")
            ) {
                Text(text = "Button")
            }
            Text(text = "Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints (margin : Dp) : ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin = margin)
            centerHorizontallyTo(parent)
        }
    }
}

@Preview(name = "Constarints_1", showBackground = true)
@Composable
fun ConstraintLayoutContentPreview () {
    CodeLab2NdTheme {
        ConstraintLayoutContent()
    }
}

@Preview(name = "Constraints_2", showBackground = true)
@Composable
fun ConstraintLayoutContentPreview_2 () {
    CodeLab2NdTheme {
        ConstraintLayoutContent_2()
    }
}

@Preview(name = "LargeText", showBackground = true)
@Composable
fun LargeConstraintLayoutPreview () {
    CodeLab2NdTheme {
        LargeConstraintLayout()
    }
}

@Preview(name = "decoupled", showBackground = true)
@Composable
fun DecoupledPreview () {
    CodeLab2NdTheme {
        DecoupledConstraintLayout()
    }
}