package com.cevdetkilickeser.composeformkeyboardmanagemenet

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cevdetkilickeser.composeformkeyboardmanagemenet.ui.theme.ComposeFormKeyboardManagemenetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeFormKeyboardManagemenetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        var firstName by remember {
                            mutableStateOf("")
                        }
                        var lastName by remember {
                            mutableStateOf("")
                        }
                        var email by remember {
                            mutableStateOf("")
                        }
                        var password by remember {
                            mutableStateOf("")
                        }
                        val focusRequester = remember {
                            FocusRequester()
                        }
                        val focusManager = LocalFocusManager.current

                        LaunchedEffect(Unit) {
                            focusRequester.requestFocus()
                        }

                        Row {
                            OutlinedTextField(
                                value = firstName,
                                onValueChange = {
                                    firstName = it
                                },
                                singleLine = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .focusRequester(focusRequester),
                                label = { Text(text = "First Name") },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Right)
                                    }
                                )
                            )
                            OutlinedTextField(
                                value = lastName,
                                onValueChange = {
                                    lastName = it
                                },
                                singleLine = true,
                                modifier = Modifier
                                    .weight(1f),
                                label = { Text(text = "Last Name") },
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Next
                                ),
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Next)
                                    }
                                )
                            )
                        }
                        OutlinedTextField(
                            value = email,
                            onValueChange = {
                                email = it
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = { Text(text = "Email") },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            )
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                            label = { Text(text = "Password") },
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.clearFocus()
                                    Toast.makeText(
                                        applicationContext,
                                        "Form submitted by keyboard",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            )
                        )
                        Button(
                            onClick = {
                                Toast.makeText(
                                    applicationContext,
                                    "SignIn Success",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Sign In")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ComposeFormKeyboardManagementPreview() {
    ComposeFormKeyboardManagemenetTheme { }
}

