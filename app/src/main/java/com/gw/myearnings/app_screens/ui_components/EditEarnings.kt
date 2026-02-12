package com.gw.myearnings.app_screens.ui_components



import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gw.myearnings.R
import com.gw.myearnings.model.EarnedCashEntity
import com.gw.myearnings.utils.dateFormat
import com.gw.myearnings.viewmodel.EarnedCashViewModel
import org.koin.androidx.compose.koinViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEarnings(
    onDismiss: () -> Unit,
    amountSpent: Float,
    amountSaved: Float,
    amountEarned: Float,
    source: String,
    noteDescription: String?,
    itemNo: String
) {



    val earnedCashViewModel: EarnedCashViewModel = koinViewModel()

    val today = dateFormat(System.currentTimeMillis())

    // Active earnings for today
    val earningsToday by earnedCashViewModel
        .getEarningsByDate(today)
        .collectAsStateWithLifecycle(initialValue = emptyList())

    val backgroundColor = colorResource(id = R.color.dusk_blue)

    var noteDescription by remember { mutableStateOf(noteDescription) }
    var source by remember { mutableStateOf(source) }
    var amountEarned by remember {
        mutableStateOf(amountEarned.toString())
    }

    var amountSpent by remember {
        mutableStateOf(amountSpent.toString())
    }

    var amountSaved by remember {
        mutableStateOf(amountSaved.toString())
    }

//    var amountEarned by remember { mutableFloatStateOf(amountEarned) }
//    var amountSpent by remember { mutableFloatStateOf(amountSpent) }
//    var amountSaved by remember { mutableFloatStateOf(amountSaved) }

    val context = LocalContext.current


    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.96f else 1f,
        label = ""
    )

    // Error states
    var amountSavedError by remember { mutableStateOf(false) }
    var amountEarnedError by remember { mutableStateOf(false) }
    var sourceError by remember { mutableStateOf(false) }
    var amountSpentError by remember { mutableStateOf(false) }

    fun autoCalculate() {
        val earned = amountEarned.toFloatOrNull() ?: 0f
        val spent = amountSpent.toFloatOrNull() ?: 0f

        if (earned >= spent) {
            val saved = earned - spent
            amountSaved = if (saved == 0f) "" else saved.toString()
        }
    }


    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .imePadding()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Text(
                    text = "Add New Earned income",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                // SOURCE
                OutlinedTextField(
                    value = source,
                    onValueChange = {
                        source = it
                        sourceError = false
                    },
                    label = { Text("Source *") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    isError = sourceError,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    )
                )
                if (sourceError) {
                    Text("Enter source", color = Color.Red, fontSize = 12.sp)
                }

                // AMOUNT EARNED
                OutlinedTextField(
                    value = amountEarned,
                    onValueChange = {
                        amountEarned = it
                        amountEarnedError = false
                        autoCalculate()
                    },
                    label = { Text("Amount Earned *") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = amountEarnedError,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    )
                )

                if (amountEarnedError) {
                    Text("Enter valid amount", color = Color.Red, fontSize = 12.sp)
                }

                // AMOUNT SPENT
                OutlinedTextField(
                    value = amountSpent,
                    onValueChange = {
                        amountSpent = it
                        amountSpentError = false
                        autoCalculate()
                    },
                    label = { Text("Amount Spent *") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    isError = amountSpentError,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    )
                )

                if (amountSpentError) {
                    Text("Enter valid amount", color = Color.Red, fontSize = 12.sp)
                }

                // AMOUNT SAVED (AUTO)
                OutlinedTextField(
                    value = amountSaved,
                    onValueChange = {},
                    label = { Text("Amount Saved (Auto)") },
                    modifier = Modifier.fillMaxWidth(),
                    readOnly = true,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    )
                )

                // NOTE
                OutlinedTextField(
                    value = noteDescription!!,
                    onValueChange = { noteDescription = it },
                    label = { Text("Reference Note (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 4,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = backgroundColor,
                        focusedLabelColor = backgroundColor,
                        cursorColor = backgroundColor
                    )
                )

                // BUTTONS
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    TextButton(onClick = onDismiss) {
                        Text("Cancel", color = colorResource(id = R.color.charcoal_blue))
                    }

                    Spacer(modifier = Modifier.width(8.dp))




                    Button(
//                        onClick = { /* your logic */ },
                        interactionSource = interactionSource,
                        modifier = Modifier
                            .height(56.dp)
                            .fillMaxWidth()
                            .graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                            },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = backgroundColor
                        )

                        ,
                        onClick = {

                            val earned = amountEarned.toDoubleOrNull()
                            val spent = amountSpent.toDoubleOrNull()
                            val saved = amountSaved.toDoubleOrNull()

                            var valid = true

                            if (source.isBlank()) {
                                sourceError = true
                                valid = false
                            }

                            if (earned == null || earned <= 0) {
                                amountEarnedError = true
                                valid = false
                            }

                            if (spent == null || spent < 0) {
                                amountSpentError = true
                                valid = false
                            }

                            if (earned != null && spent != null) {
                                if (spent > earned) {
                                    Toast.makeText(
                                        context,
                                        "Amount Spent cannot be greater than Amount Earned",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    valid = false
                                }

                                if (saved != earned - spent) {
                                    Toast.makeText(
                                        context,
                                        "Values do not balance correctly",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    valid = false
                                }
                            }

                            if (valid) {

                                earnedCashViewModel.insertEarnings(
                                    EarnedCashEntity(
                                        itemNumber = generateTimestampBased10DigitNumberItemNo().toString(),
                                        totalEarned = amountEarned.toFloat(),
                                        totalSaved = amountSaved.toFloat(),
                                        totalSpend = amountSpent.toFloat(),
                                        source = source,
                                        note = noteDescription,
                                        date = today
                                    )
                                )

                                Toast.makeText(
                                    context,
                                    "Saved successfully",
                                    Toast.LENGTH_SHORT
                                ).show()

                                onDismiss()
                            }
                        }
                    ) {
                        Text(
                            text = "Add",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}




