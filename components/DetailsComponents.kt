package com.emiliagomez.a243700_examenu3_moviles.presentation.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.emiliagomez.a243700_examenu3_moviles.pokeapi.pokeModels.AbilitySlot
import com.emiliagomez.a243700_examenu3_moviles.pokeapi.pokeModels.StatSlot
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Typography
import kotlin.collections.forEach


@Composable
fun StatsSection(stats: List<StatSlot>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Estadísticas Base",
                style = Typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            stats.forEach { statSlot ->
                StatBar(
                    statName = statSlot.stat.name.replace("-", " ")
                        .replaceFirstChar { it.uppercase() },
                    statValue = statSlot.baseStat
                )
            }
        }
    }
}

@Composable
fun StatBar(statName: String, statValue: Int) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statName,
            style = Typography.bodyMedium,
            modifier = Modifier.width(100.dp)
        )

        LinearProgressIndicator(
            progress = (statValue / 255f).coerceIn(0f, 1f),
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = when {
                statValue >= 100 -> Color.Green
                statValue >= 50 -> Color.Yellow
                else -> Color.Red
            }
        )
        Text(
            text = statValue.toString(),
            style = Typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(40.dp),
            textAlign = TextAlign.End
        )
    }
}

@Composable
fun AbilitiesSection(abilities: List<AbilitySlot>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Habilidades",
                style = Typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            abilities.forEach { abilitySlot ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = if (abilitySlot.isHidden)
                            Icons.Default.VisibilityOff
                        else
                            Icons.Default.Visibility,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = if (abilitySlot.isHidden) Color.Gray else MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = abilitySlot.ability.name.replace("-", " ")
                            .replaceFirstChar { it.uppercase() },
                        style = Typography.bodyLarge
                    )
                    if (abilitySlot.isHidden) {
                        Text(
                            text = "(Oculta)",
                            style = Typography.bodySmall,
                            color = Color.Gray,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }
            }
        }
    }
}