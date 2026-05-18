package com.bunyamin027.zikrify.ui.screen

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bunyamin027.zikrify.ui.theme.*
import com.bunyamin027.zikrify.viewmodel.CounterViewModel

@Composable
fun CounterScreen(
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = viewModel()
) {
    val count by viewModel.count.collectAsState()
    val target by viewModel.targetCount.collectAsState()
    val selectedZikir by viewModel.selectedZikir.collectAsState()
    val isCompleted = viewModel.isCompleted

    var showResetDialog by remember { mutableStateOf(false) }
    var showTargetDialog by remember { mutableStateOf(false) }

    val progress = if (target > 0) (count.toFloat() / target).coerceIn(0f, 1f) else 0f
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = spring(stiffness = Spring.StiffnessLow)
    )
    val scale by animateFloatAsState(
        targetValue = if (isCompleted) 1.05f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Zikir Details
        Text(
            text = selectedZikir.arabicText,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = AccentGold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = selectedZikir.name,
            fontSize = 32.sp,
            fontWeight = FontWeight.Black,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = selectedZikir.meaning,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        // Circular Counter
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(280.dp)
                .scale(scale)
                .clip(CircleShape)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    viewModel.increment()
                }
        ) {
            CircularProgressIndicator(
                progress = animatedProgress,
                modifier = Modifier.fillMaxSize(),
                color = AccentGreen,
                strokeWidth = 16.dp,
                trackColor = MaterialTheme.colorScheme.surface
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (isCompleted) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Completed",
                        tint = AccentGreen,
                        modifier = Modifier.size(60.dp)
                    )
                    Text(
                        text = "Tamamlandı",
                        fontWeight = FontWeight.Bold,
                        color = AccentGreen,
                        fontSize = 20.sp
                    )
                } else {
                    Text(
                        text = "$count",
                        fontSize = 80.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "/ $target",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Target Selectors
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf(33, 99, 1000).forEach { t ->
                val isSelected = target == t
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(60.dp, 40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isSelected) AccentGreen else MaterialTheme.colorScheme.surface)
                        .clickable { viewModel.setTarget(t) }
                ) {
                    Text(
                        text = "$t",
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(60.dp, 40.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { showTargetDialog = true }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Target",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Reset Button
        IconButton(
            onClick = { showResetDialog = true },
            modifier = Modifier.size(60.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Reset",
                    tint = MaterialTheme.colorScheme.onSurface
                )
                Text("Sıfırla", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurface)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Zikri Sıfırla") },
            text = { Text("Emin misiniz? Zikir sayacı sıfırlanacak.") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.reset()
                    showResetDialog = false
                }) {
                    Text("Evet", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("İptal")
                }
            }
        )
    }

    if (showTargetDialog) {
        var newTargetStr by remember { mutableStateOf("$target") }
        AlertDialog(
            onDismissRequest = { showTargetDialog = false },
            title = { Text("Özel Hedef") },
            text = {
                OutlinedTextField(
                    value = newTargetStr,
                    onValueChange = { newTargetStr = it },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    val newTarget = newTargetStr.toIntOrNull()
                    if (newTarget != null && newTarget > 0) {
                        viewModel.setTarget(newTarget)
                    }
                    showTargetDialog = false
                }) {
                    Text("Kaydet")
                }
            },
            dismissButton = {
                TextButton(onClick = { showTargetDialog = false }) {
                    Text("İptal")
                }
            }
        )
    }
}
