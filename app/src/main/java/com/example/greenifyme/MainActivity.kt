package com.example.greenifyme

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.greenifyme.compose_utilities.getString
import com.example.greenifyme.compose_utilities.theme.ComposeTheme
import com.example.greenifyme.ui.admin.home.AdminHomeActivity
import com.example.greenifyme.ui.database_manager.DBManagerActivity
import com.example.greenifyme.ui.login.LoginNavigationActivity
import com.example.greenifyme.ui.shared.SharedColumn
import com.example.greenifyme.ui.user.UserHomeActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContent {
            ComposeTheme {
                LandingPage()
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun LandingPage() {
    val context = LocalContext.current

   SharedColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.fillMaxSize(),
        applyHorizontalPadding = false
    ) {
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(20.dp),
           horizontalArrangement = Arrangement.End
       ) {
           IconButton(
               onClick = {
                   //For testing purposes:
                   //Toast.makeText(context, "Home Icon Clicked", Toast.LENGTH_SHORT).show()
                   showDialog(context)
               },
               modifier = Modifier.size(40.dp)
           ){
               Icon(
                   imageVector = Icons.Outlined.Info,
                   contentDescription = "Info",
                   tint = Color.Gray,
                   modifier = Modifier
                       .size(32.dp)
               )
           }
       }
       /*
        Image(
            painter = painterResource(id = R.drawable.greenifyme_logo),
            contentDescription = stringResource(id = R.string.logoDescription),
            modifier = Modifier
                .size(80.dp)
                .padding(0.dp)
        )
        */
        Text(
            text = stringResource(id = R.string.login_label),
            fontSize = 32.sp,
            color = Color.Gray
        )

        FilledTonalButton(
            onClick = {
                context.startActivity(Intent(context, LoginNavigationActivity::class.java))
            },
            modifier = Modifier
                .height(52.dp)
                .padding(bottom = 3.dp)
                .width(200.dp)
        ) {
            Text(text = stringResource(id = R.string.user_login), fontSize = 18.sp)
        }

        FilledTonalButton(
            onClick = {
                context.startActivity(Intent(context, LoginNavigationActivity::class.java))
            },
            modifier = Modifier
                .height(52.dp)
                .padding(bottom = 3.dp)
                .width(200.dp)
        ) {
            Text(text = stringResource(id = R.string.admin_login), fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.weight(5f))
        Text(text="OR",fontSize = 18.sp)
        //Spacer(modifier = Modifier.weight(5f))
        Surface(
            color = Color(0xFF6F78A8),
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(text = stringResource(id = R.string.login_as_label),
                     fontSize = 20.sp,
                     color = Color.White
                     )

                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            context.startActivity(Intent(context, DBManagerActivity::class.java))
                        },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White, //Text color
                            containerColor = Color.Transparent //Background color
                        ),
                        border = BorderStroke(1.dp, Color.White), //Border color
                        modifier = Modifier
                            .height(52.dp)
                            .width(180.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.database_manager),
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                    OutlinedButton( onClick = {
                        context.startActivity(Intent(context, AdminHomeActivity::class.java))
                    },
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White, //Text color
                            containerColor = Color.Transparent //Background color
                        ),
                        border = BorderStroke(1.dp, Color.White), //Border color
                        modifier = Modifier
                            .height(52.dp)
                            .width(180.dp)

                    ) {
                        Text(
                            text = stringResource(id = R.string.admin_presentation),
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }
        }
    }
}

fun showDialog(context: Context) {
    val builder = AlertDialog.Builder(context)

    builder.setTitle("App Information")
    builder.setMessage("Greenify Me - Save the environment one recyclable material at a time\n\nApp Version 1.0\nDeveloped by:\nmikevafeiad045\nTonyGnk\net al")

    builder.setPositiveButton("OK") { dialog, _ ->
        dialog.dismiss()
    }

    val dialog = builder.create()
    dialog.show()
}

