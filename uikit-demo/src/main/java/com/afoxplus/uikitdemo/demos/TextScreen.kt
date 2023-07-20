package com.afoxplus.uikitdemo.demos

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.afoxplus.uikitcompose.ui.theme.Header01
import com.afoxplus.uikitcompose.ui.theme.Header01Bold
import com.afoxplus.uikitcompose.ui.theme.Header01SemiBold
import com.afoxplus.uikitcompose.ui.theme.Header02
import com.afoxplus.uikitcompose.ui.theme.Header02Bold
import com.afoxplus.uikitcompose.ui.theme.Header02SemiBold
import com.afoxplus.uikitcompose.ui.theme.Header03
import com.afoxplus.uikitcompose.ui.theme.Header03Bold
import com.afoxplus.uikitcompose.ui.theme.Header03SemiBold
import com.afoxplus.uikitcompose.ui.theme.Header04
import com.afoxplus.uikitcompose.ui.theme.Header04SemiBold
import com.afoxplus.uikitcompose.ui.theme.Header05
import com.afoxplus.uikitcompose.ui.theme.Header05Bold
import com.afoxplus.uikitcompose.ui.theme.Header05SemiBold
import com.afoxplus.uikitcompose.ui.theme.Paragraph01
import com.afoxplus.uikitcompose.ui.theme.Paragraph01Bold
import com.afoxplus.uikitcompose.ui.theme.Paragraph01SemiBold
import com.afoxplus.uikitcompose.ui.theme.Paragraph02
import com.afoxplus.uikitcompose.ui.theme.Paragraph02Bold
import com.afoxplus.uikitcompose.ui.theme.Paragraph02SemiBold
import com.afoxplus.uikitcompose.ui.theme.Title01
import com.afoxplus.uikitcompose.ui.theme.Title02
import com.afoxplus.uikitcompose.ui.theme.Title03
import com.afoxplus.uikitcompose.ui.theme.Title04

@Composable
fun TextScreen() {
    Column {
        Text(text = "Header01", style = Header01)
        Text(text = "Header01SemiBold", style = Header01SemiBold)
        Text(text = "Header01Bold", style = Header01Bold)

        Text(text = "Header02", style = Header02)
        Text(text = "Header02SemiBold", style = Header02SemiBold)
        Text(text = "Header02Bold", style = Header02Bold)

        Text(text = "Header03", style = Header03)
        Text(text = "Header03SemiBold", style = Header03SemiBold)
        Text(text = "Header03Bold", style = Header03Bold)

        Text(text = "Header04", style = Header04)
        Text(text = "Header04SemiBold", style = Header04SemiBold)
        Text(text = "Header04Bold", style = Header04SemiBold)

        Text(text = "Header05", style = Header05)
        Text(text = "Header05SemiBold", style = Header05SemiBold)
        Text(text = "Header05Bold", style = Header05Bold)

        Text(text = "Paragraph01", style = Paragraph01)
        Text(text = "Paragraph01SemiBold", style = Paragraph01SemiBold)
        Text(text = "Paragraph01Bold", style = Paragraph01Bold)

        Text(text = "Paragraph02", style = Paragraph02)
        Text(text = "Paragraph02SemiBold", style = Paragraph02SemiBold)
        Text(text = "Paragraph02Bold", style = Paragraph02Bold)

        Text(text = "Title01", style = Title01)
        Text(text = "Title02", style = Title02)
        Text(text = "Title03", style = Title03)
        Text(text = "Title04", style = Title04)
    }
}

@Preview
@Composable
fun TextScreenPreview() {
    TextScreen()
}
