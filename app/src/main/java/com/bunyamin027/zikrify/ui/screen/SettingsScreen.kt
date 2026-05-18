package com.bunyamin027.zikrify.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
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
import com.bunyamin027.zikrify.ui.theme.AccentGold
import com.bunyamin027.zikrify.ui.theme.AccentGreen
import com.bunyamin027.zikrify.ui.theme.DangerRed
import com.bunyamin027.zikrify.viewmodel.CounterViewModel

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: CounterViewModel = viewModel()
) {
    val isEnglish = viewModel.isEnglish
    val hapticEnabled by viewModel.hapticEnabled.collectAsState()
    val soundEnabled by viewModel.soundEnabled.collectAsState()
    val autoReset by viewModel.autoReset.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = if (isEnglish) "Settings" else "Ayarlar",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
        )

        // Language
        SettingsSection(title = if (isEnglish) "Language" else "Dil") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    Icon(Icons.Default.Settings, contentDescription = null, tint = Color.Blue)
                    Text(
                        text = if (isEnglish) "App Language" else "Uygulama Dili",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                var expanded by remember { mutableStateOf(false) }
                Box {
                    Text(
                        text = if (isEnglish) "English" else "Türkçe",
                        color = Color.Blue,
                        modifier = Modifier.clickable { expanded = true }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Türkçe") },
                            onClick = { 
                                viewModel.appLanguage.value = "tr"
                                viewModel.saveSettings()
                                expanded = false 
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("English") },
                            onClick = { 
                                viewModel.appLanguage.value = "en"
                                viewModel.saveSettings()
                                expanded = false 
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preferences
        SettingsSection(title = if (isEnglish) "Preferences" else "Tercihler") {
            ToggleRow(
                icon = Icons.Default.Settings,
                iconColor = AccentGreen,
                title = if (isEnglish) "Haptic" else "Titreşim",
                subtitle = if (isEnglish) "Haptic feedback on every count" else "Her sayımda titreşim geri bildirimi",
                isChecked = hapticEnabled,
                onCheckedChange = { 
                    viewModel.hapticEnabled.value = it
                    viewModel.saveSettings()
                }
            )
            Divider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(horizontal = 16.dp))
            ToggleRow(
                icon = Icons.Default.Info,
                iconColor = AccentGold,
                title = if (isEnglish) "Sound" else "Ses",
                subtitle = if (isEnglish) "Play click sound on every count" else "Her sayımda tık sesi çal",
                isChecked = soundEnabled,
                onCheckedChange = { 
                    viewModel.soundEnabled.value = it
                    viewModel.saveSettings()
                }
            )
            Divider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(horizontal = 16.dp))
            ToggleRow(
                icon = Icons.Default.Refresh,
                iconColor = Color(0xFF8E44AD),
                title = if (isEnglish) "Auto Reset" else "Otomatik Sıfırla",
                subtitle = if (isEnglish) "Start new round on target reach" else "Hedefe ulaşınca yeni tur başlat",
                isChecked = autoReset,
                onCheckedChange = { 
                    viewModel.autoReset.value = it
                    viewModel.saveSettings()
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // About
        SettingsSection(title = if (isEnglish) "About" else "Hakkında") {
            InfoRow(icon = Icons.Default.Info, iconColor = AccentGold, title = if (isEnglish) "App" else "Uygulama", value = "Zikrify")
            Divider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(horizontal = 16.dp))
            InfoRow(icon = Icons.Default.Info, iconColor = AccentGreen, title = if (isEnglish) "Version" else "Versiyon", value = "1.0.0")
            Divider(color = MaterialTheme.colorScheme.surfaceVariant, modifier = Modifier.padding(horizontal = 16.dp))
            InfoRow(icon = Icons.Default.Person, iconColor = AccentGold, title = if (isEnglish) "Developer" else "Geliştirici", value = "kahramandev")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Data
        SettingsSection(title = if (isEnglish) "Data" else "Veri") {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.reset() }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null, tint = DangerRed)
                Text(
                    text = if (isEnglish) "Reset Counter" else "Sayacı Sıfırla",
                    fontSize = 16.sp,
                    color = DangerRed
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quote
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
                .border(1.dp, AccentGold.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                .padding(20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "وَلَذِكْرُ اللَّهِ أَكْبَرُ",
                    fontSize = 20.sp,
                    color = AccentGold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = if (isEnglish) "\"Indeed, the remembrance of Allah is the greatest.\"" else "\"Allah'ı zikretmek elbette en büyük ibadettir.\"",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = if (isEnglish) "— Al-Ankabut 45" else "— Ankebut 45",
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun SettingsSection(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title.uppercase(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Column {
                content()
            }
        }
    }
}

@Composable
fun ToggleRow(icon: androidx.compose.ui.graphics.vector.ImageVector, iconColor: Color, title: String, subtitle: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp), modifier = Modifier.weight(1f)) {
            Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(28.dp))
            Column {
                Text(text = title, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
                Text(text = subtitle, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(checkedThumbColor = AccentGreen, checkedTrackColor = AccentGreen.copy(alpha = 0.5f))
        )
    }
}

@Composable
fun InfoRow(icon: androidx.compose.ui.graphics.vector.ImageVector, iconColor: Color, title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
            Icon(imageVector = icon, contentDescription = null, tint = iconColor, modifier = Modifier.size(28.dp))
            Text(text = title, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
        }
        Text(text = value, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}
