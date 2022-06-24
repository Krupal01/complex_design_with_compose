package com.example.complexuicompose.activity.composable

import androidx.compose.runtime.Composable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Velocity
import androidx.constraintlayout.compose.MotionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi

@ExperimentalMaterialApi
@Composable
fun CollapsableToolbar(
    scrollableBody: @Composable () -> Unit,
    toolbarBody: @Composable () -> Unit,
    title: @Composable () -> Unit,
) {
    val swipingState = rememberSwipeableState(initialValue = SwipingStates.EXPANDED)

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val heightInPx = with(LocalDensity.current) { maxHeight.toPx() } // Get height of screen
        val connection = remember {
            object : NestedScrollConnection {

                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return if (delta < 0) {
                        swipingState.performDrag(delta).toOffset()
                    } else {
                        Offset.Zero
                    }
                }

                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return swipingState.performDrag(delta).toOffset()
                }

                override suspend fun onPostFling(
                    consumed: Velocity,
                    available: Velocity
                ): Velocity {
                    swipingState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                private fun Float.toOffset() = Offset(0f, this)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .swipeable(
                    state = swipingState,
                    thresholds = { _, _ -> FractionalThreshold(0.5f) },
                    orientation = Orientation.Vertical,
                    anchors = mapOf(
                        0f to SwipingStates.COLLAPSED,
                        heightInPx to SwipingStates.EXPANDED,
                    )
                )
                .nestedScroll(connection)
        ) {
            Column {
                MotionLayoutHeader(
                    progress =  if (swipingState.progress.to == SwipingStates.COLLAPSED) swipingState.progress.fraction else 1f - swipingState.progress.fraction,
                    body = scrollableBody,
                    title = title,
                    toolbarBody = toolbarBody
                )
            }
        }
    }
}

enum class SwipingStates {
    EXPANDED,
    COLLAPSED
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun MotionLayoutHeader(
    progress: Float,
    toolbarBody : @Composable ()->Unit,
    title : @Composable () -> Unit,
    body: @Composable () -> Unit
) {
    MotionLayout(
        start = jsonConstraintSetStart(),
        end = jsonConstraintSetEnd(),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .layoutId("poster")
                .background(MaterialTheme.colors.primary).fillMaxWidth(),
        ){
            toolbarBody()
        }
        Box(
            modifier = Modifier
                .layoutId("title")
                .wrapContentHeight()
        ){
            title()
        }
        Box(
            Modifier
                .layoutId("content")
        ) {
            body()
        }
    }
}

@Composable
private fun jsonConstraintSetStart() = ConstraintSet (""" {
	poster: { 
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	title: {
		bottom: ['poster', 'bottom', 16],
		start: ['parent', 'start', 16],
	},
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['title', 'bottom', 16],
	}
} """ )

@Composable
private fun jsonConstraintSetEnd() = ConstraintSet (""" {
	poster: { 
		width: "spread",
		height: 56,
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['parent', 'top', 0],
	},
	title: {
		top: ['parent', 'top', 0],
		start: ['parent', 'start', 16],
		bottom: ['poster', 'bottom', 0],
	},
	content: {
		width: "spread",
		start: ['parent', 'start', 0],
		end: ['parent', 'end', 0],
		top: ['poster', 'bottom', 0],
	}
                  
} """)

