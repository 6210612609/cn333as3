package com.example.guessingnumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guessingnumber.ui.theme.GuessingNumberTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessingNumberTheme {
                appComponent()
                }
            }
        }

    var random: Int = Random.nextInt(1, 1000)

    @Composable
    fun appComponent() {
        var guessNumber by remember { mutableStateOf("") }
        var hintText by remember { mutableStateOf("Enter the number in your mind :") }
        var points by remember {mutableStateOf(0)}

        Column (
            modifier = Modifier
                .fillMaxHeight()
                .background(color = Color.DarkGray),

            ){
            Text(
                text = "Guessing Number",
                color = Color.Black,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()

            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = "How to play",
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 20.dp)


            )
            Text(
                text = "=> Guess the secret number. If your guess is too high or low. You will get the hint.",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 20.dp)


            )
            Spacer(modifier = Modifier.padding(20.dp))
            Text(
                text = "$hintText",
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 20.dp)


            )
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = guessNumber,
                    onValueChange = {
                        guessNumber = it
                    },
                    placeholder = {
                        Text(text = stringResource(id = R.string.hintPlcaeholder))
                    },
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(start = 20.dp)
                )
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black,
                        contentColor = Color.White),
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(10.dp),
                    onClick = {
                        val number: Int = guessNumber.toString().toInt()

                        if (number < random){
                            points ++
                            hintText = "Unlucky,It's too low!!"

                        } else if (number > random) {
                            points ++
                            hintText ="Unlucky,It's too high!!"

                        } else{

                            hintText ="Very good! You are the winner!!"

                        }
                        guessNumber = ""

                    }

                ){
                    Text(text = stringResource(id = R.string.check))
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Row (
                horizontalArrangement = Arrangement.Center
            ){

                Text(
                    text = "Guessing Count : $points",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(start = 20.dp)
                )

                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray,
                        contentColor = Color.White),
                    modifier = Modifier
                        .alignByBaseline()
                        .padding(20.dp),
                    onClick = {
                        random = Random.nextInt(1, 1000)
                        hintText = "Please enter your guess:"
                        points = 0

                    }
                ) {
                    Text(text = stringResource(id = R.string.reset))
                }
            }
        }

    }

    }



