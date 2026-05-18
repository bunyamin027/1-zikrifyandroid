package com.bunyamin027.zikrify.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bunyamin027.zikrify.model.Badge
import com.bunyamin027.zikrify.model.Moods
import com.bunyamin027.zikrify.ui.theme.*
import com.bunyamin027.zikrify.viewmodel.CounterViewModel

@Composable
fun JourneyScreen(
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = viewModel()
) {
    val isEnglish = viewModel.isEnglish
    val totalToday by viewModel.totalToday.collectAsState()
    val dailyTotalGoal by viewModel.dailyTotalGoal.collectAsState()
    val earnedBadges by viewModel.earnedBadges.collectAsState()

    var showGoalDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = if (isEnglish) "My Journey" else "Yolculuğum",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
        )

        // Daily Goal Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .clickable { showGoalDialog = true }
                .padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = if (isEnglish) "Daily Target" else "Günlük Hedef",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "$totalToday / $dailyTotalGoal",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Box(contentAlignment = Alignment.Center, modifier = Modifier.size(50.dp)) {
                    CircularProgressIndicator(
                        progress = 1f,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        strokeWidth = 6.dp,
                        modifier = Modifier.fillMaxSize()
                    )
                    CircularProgressIndicator(
                        progress = (totalToday.toFloat() / dailyTotalGoal).coerceIn(0f, 1f),
                        color = AccentGold,
                        strokeWidth = 6.dp,
                        modifier = Modifier.fillMaxSize()
                    )
                    val percent = ((totalToday.toFloat() / dailyTotalGoal).coerceIn(0f, 1f) * 100).toInt()
                    Text(
                        text = "$percent%",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Healing & Wisdom
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = AccentGold)
                    Text(
                        text = if (isEnglish) "Healing & Wisdom" else "Şifa ve Bilgelik",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                val items = listOf(
                    Triple(Moods.consolation, Icons.Default.Favorite, AccentBlue),
                    Triple(Moods.secure, Icons.Default.Info, DangerRed),
                    Triple(Moods.gratitude, Icons.Default.Star, AccentGreen),
                    Triple(Moods.ask, Icons.Default.Star, AccentGold),
                    Triple(Moods.purification, Icons.Default.Info, Color.Gray),
                    Triple(Moods.joy, Icons.Default.Favorite, Color.Magenta)
                )
                
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    for (i in items.indices step 2) {
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            HealingCard(
                                title = Moods.title(items[i].first, isEnglish),
                                icon = items[i].second,
                                color = items[i].third,
                                modifier = Modifier.weight(1f)
                            )
                            if (i + 1 < items.size) {
                                HealingCard(
                                    title = Moods.title(items[i + 1].first, isEnglish),
                                    icon = items[i + 1].second,
                                    color = items[i + 1].third,
                                    modifier = Modifier.weight(1f)
                                )
                            } else {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Badges Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MaterialTheme.colorScheme.surface)
                .padding(20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = if (isEnglish) "Badges" else "Rozetler",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Badge.allBadges.forEach { badge ->
                        val isEarned = earnedBadges.contains(badge.id)
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                                    .background(if (isEarned) AccentGold.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = badge.nameTR,
                                    tint = if (isEarned) AccentGold else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Text(
                                text = if (isEnglish) badge.nameEN else badge.nameTR,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isEarned) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(100.dp))
    }

    if (showGoalDialog) {
        var newGoalStr by remember { mutableStateOf("$dailyTotalGoal") }
        AlertDialog(
            onDismissRequest = { showGoalDialog = false },
            title = { Text(if (isEnglish) "Set Daily Target" else "Günlük Hedef Belirle") },
            text = {
                OutlinedTextField(
                    value = newGoalStr,
                    onValueChange = { newGoalStr = it },
                    singleLine = true
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    val newGoal = newGoalStr.toIntOrNull()
                    if (newGoal != null && newGoal > 0) {
                        viewModel.dailyTotalGoal.value = newGoal
                        viewModel.saveSettings()
                    }
                    showGoalDialog = false
                }) {
                    Text(if (isEnglish) "Done" else "Tamam", color = AccentGold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showGoalDialog = false }) {
                    Text(if (isEnglish) "Close" else "Kapat")
                }
            }
        )
    }
}

@Composable
fun HealingCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .height(100.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { /* Handle click */ }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Icon(imageVector = icon, contentDescription = title, tint = color)
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}
