package com.truthordare.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.truthordare.app.R
import com.truthordare.app.data.Category
import com.truthordare.app.data.GameMode
import com.truthordare.app.data.Question
import com.truthordare.app.data.QuestionEngine
import com.truthordare.app.data.UserSettings
import com.truthordare.app.viewmodel.GameViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AgeVerifyScreen(onVerified: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surface
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.padding(24.dp)
        ) {
            Column(
                modifier = Modifier.padding(28.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.age_gate_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "This app is intended for adult party play. Please confirm you meet the age requirement.",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                )
                Button(onClick = onVerified, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.yes_18))
                }
                OutlinedButton(onClick = { /* exit no-op in demo */ }, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.no_under_18))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    settings: UserSettings,
    onCategoryClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.home_title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onSettingsClick() }
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
            ) {
                Column(Modifier.padding(20.dp)) {
                    Text("Session setup", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(8.dp))
                    Text("Players: ${settings.playerCount} • Timer: ${settings.timeLimitSeconds}s • Theme: ${settings.themeMode.name}")
                }
            }

            Button(onClick = onCategoryClick, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.start_game))
            }
        }
    }
}

@Composable
fun CategoryScreen(onCategorySelected: (Category) -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.category_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            CategoryOptionRow(label = "Bedroom", color = Color(0xFFB21F4A)) {
                onCategorySelected(Category.BEDROOM)
            }
            CategoryOptionRow(label = "Risky", color = Color(0xFFFF6B2C)) {
                onCategorySelected(Category.RISKY)
            }
            CategoryOptionRow(label = "No Fear", color = Color(0xFF7A2EFF)) {
                onCategorySelected(Category.NO_FEAR)
            }
        }
    }
}

@Composable
private fun CategoryOptionRow(label: String, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.14f))
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold)
            Text("Play now", color = color, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun SetupScreen(category: Category, onStart: () -> Unit, onBack: () -> Unit) {
    val questions = QuestionEngine.forCategory(category).take(3)
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable { onBack() }
                )
                Spacer(Modifier.size(12.dp))
                Text(
                    text = category.name.replace("_", " ") + " setup",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Preview questions")
                    questions.forEach { question ->
                        Text("• ${question.text}")
                    }
                }
            }
            Button(onClick = onStart, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.start_game))
            }
        }
    }
}

@Composable
fun GameScreen(category: Category, settings: UserSettings, onSummary: () -> Unit, onBack: () -> Unit) {
    val viewModel: GameViewModel = viewModel()
    val state = viewModel.uiState
    val currentQuestion = state.value.currentQuestion ?: Question(
        id = 0,
        text = "No question available yet. Tap next to continue.",
        type = com.truthordare.app.data.QuestionType.TRUTH,
        category = category,
        intensity = com.truthordare.app.data.Intensity.MEDIUM,
        rating = com.truthordare.app.data.ContentRating.SAFE
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable { onBack() }
                )
                Text(text = category.name.replace("_", " "), fontWeight = FontWeight.Bold)
                Text(text = "${state.value.timeRemaining}s")
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
            ) {
                Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(currentQuestion.text, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text("Mode: ${settings.defaultGameMode.name}")
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = { viewModel.skip() }, modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.skip))
                }
                Button(onClick = {
                    viewModel.nextQuestion()
                    onSummary()
                }, modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.next_question))
                }
            }
        }
    }
}

@Composable
fun SessionSummaryScreen(onHome: () -> Unit) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.summary_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Best round: 1 complete game")
                    Text("Questions covered: 12")
                    Text("Safe word used: none")
                }
            }
            Button(onClick = onHome, modifier = Modifier.fillMaxWidth()) {
                Text("Back to home")
            }
        }
    }
}

@Composable
fun SettingsScreen(
    settings: UserSettings,
    onSettingChanged: (UserSettings) -> Unit,
    onBack: () -> Unit
) {
    val items = listOf(
        "Enable countdown" to settings.enableCountdown,
        "Allow adult questions" to settings.allowAdultQuestions,
        "Strict no duplicates" to settings.strictNoDuplicateQuestions,
        "Reduce motion" to settings.reduceMotion,
        "Sound enabled" to settings.soundEnabled,
        "Enable haptics" to settings.enableHaptics
    )

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.clickable { onBack() }
                )
                Spacer(Modifier.size(12.dp))
                Text(stringResource(R.string.settings_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            }

            LazyColumn(contentPadding = PaddingValues(vertical = 8.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(items) { (label, value) ->
                    var checked by remember { mutableStateOf(value) }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(label)
                        Switch(
                            checked = checked,
                            onCheckedChange = {
                                checked = it
                                when (label) {
                                    "Enable countdown" -> onSettingChanged(settings.copy(enableCountdown = it))
                                    "Allow adult questions" -> onSettingChanged(settings.copy(allowAdultQuestions = it))
                                    "Strict no duplicates" -> onSettingChanged(settings.copy(strictNoDuplicateQuestions = it))
                                    "Reduce motion" -> onSettingChanged(settings.copy(reduceMotion = it))
                                    "Sound enabled" -> onSettingChanged(settings.copy(soundEnabled = it))
                                    "Enable haptics" -> onSettingChanged(settings.copy(enableHaptics = it))
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
