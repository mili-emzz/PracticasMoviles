package com.emiliagomez.a243700_examenu3_moviles.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.emiliagomez.a243700_examenu3_moviles.navigation.Destination
import com.emiliagomez.a243700_examenu3_moviles.navigation.SharedTab
import com.emiliagomez.a243700_examenu3_moviles.pokeapi.pokeModels.AbilitySlot
import com.emiliagomez.a243700_examenu3_moviles.pokeapi.pokeModels.PokeModel
import com.emiliagomez.a243700_examenu3_moviles.pokeapi.pokeModels.StatSlot
import com.emiliagomez.a243700_examenu3_moviles.presentation.viewModels.PokeViewModel
import com.emiliagomez.a243700_examenu3_moviles.presentation.views.components.TileHW
import com.emiliagomez.a243700_examenu3_moviles.presentation.views.components.TileName
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.BackgroundColor
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Typography

@Composable
fun DetailsView(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: PokeViewModel,
    pokemonName: String
) {
    val selectedPokemon by viewModel.selectedPokemons.collectAsState()

    Scaffold(
        topBar = {
            SharedTab(
                navController = navController,
                selectedDestination = Destination.DETAILS
            )
        },
        modifier = Modifier.fillMaxSize(),
        containerColor = BackgroundColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (selectedPokemon != null) {
                selectedPokemon?.let { pokemon ->
                    PokemonDetails(pokemon = pokemon)
                } ?: run {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No hay Pokémon seleccionado",
                            style = Typography.titleMedium,
                            color = Color.White
                        )
                    }
                }
            } else {
                "No existe el pokemon"
            }
        }
    }
}

@Composable
fun PokemonDetails(
    pokemon: PokeModel
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier
                    .size(250.dp)
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                )

            }
        }
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                pokemon.types.forEach { typeSlot ->
                    TileName(
                        typeName = typeSlot.type.name,
                        modifier = Modifier
                    )
                }
            }
        }

        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { it.titlecase() },
                    modifier = Modifier.padding(2.dp),
                    style = Typography.displaySmall,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
                TileHW(
                    height = pokemon.height,
                    weight = pokemon.weight,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        item {
            StatsSection(
                stats = pokemon.stats
            )
        }

        item {
            AbilitiesSection(
                abilities = pokemon.abilities
            )
        }

    }
}

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
                    statName = statSlot.stat.name.replace("-", " ").replaceFirstChar { it.uppercase() },
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
                        text = abilitySlot.ability.name.replace("-", " ").replaceFirstChar { it.uppercase() },
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
