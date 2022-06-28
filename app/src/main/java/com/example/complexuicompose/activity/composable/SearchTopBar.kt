package com.example.complexuicompose.activity.composable

import android.util.Log
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SearchTopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    searchBarState: SearchBarState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    leadingIcon : ImageVector,
    onLeadingClick : ()->Unit
) {
    when(searchBarState){
        SearchBarState.OPEN ->{
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                elevation = AppBarDefaults.TopAppBarElevation,
                color = MaterialTheme.colors.primary
            ) {
                TextField(modifier = Modifier.fillMaxWidth(),
                    value = searchTextState,
                    onValueChange = {
                        onTextChange(it)
                    },
                    placeholder = {
                        Text(
                            modifier = Modifier
                                .alpha(ContentAlpha.medium),
                            text = "Search here...",
                            color = Color.White
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = MaterialTheme.typography.subtitle1.fontSize
                    ),
                    singleLine = true,
                    leadingIcon = {
                        IconButton(
                            modifier = Modifier
                                .alpha(ContentAlpha.medium),
                            onClick = {
                                onLeadingClick()
                            }
                        ) {
                            Icon(
                                imageVector = leadingIcon,
                                contentDescription = "Leading Icon",
                                tint = Color.White
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                if (searchTextState.isNotEmpty()) {
                                    onTextChange("")
                                } else {
                                    onCloseClicked()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close Icon",
                                tint = Color.White
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
                    ))
            }
        }
        SearchBarState.CLOSED->{
            TopAppBar(
                modifier = modifier,
                backgroundColor = backgroundColor,
                contentColor = contentColor,
                elevation = elevation,
                title = title,
                navigationIcon = navigationIcon,
                actions = actions
            )
        }
    }
}

enum class SearchBarState{
    OPEN,
    CLOSED
}

@Preview
@Composable
fun PreviewSearchBar() {

    val searchbarState = remember {
        mutableStateOf(SearchBarState.CLOSED)
    }
    val searchableText = remember {
        mutableStateOf("")
    }

    SearchTopBar(
        modifier = Modifier.fillMaxWidth(),
        title = { Text(text = "main") },
        searchBarState = searchbarState.value ,
        searchTextState = searchableText.value,
        onTextChange = {
            searchableText.value =  it
        },
        onCloseClicked = {
            searchbarState.value = SearchBarState.CLOSED
        },
        leadingIcon = Icons.Default.Search,
        onLeadingClick = {},
        actions = {
            IconButton(onClick = {
                searchbarState.value = SearchBarState.OPEN
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Leading Icon",
                    tint = Color.White
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Leading Icon",
                    tint = Color.White
                )
            }
        }
    )
}