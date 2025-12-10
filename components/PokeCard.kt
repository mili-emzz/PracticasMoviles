package com.emiliagomez.a243700_examenu3_moviles.presentation.views.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Height
import androidx.compose.material.icons.filled.MonitorWeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.emiliagomez.a243700_examenu3_moviles.pokeapi.pokeModels.PokeModel
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Bug
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Dark
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Dragon
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Electric
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Fairy
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Fighting
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Fire
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Flying
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Ghost
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Grass
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Ground
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Ice
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Normal
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Poison
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Psychic
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Rock
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Steel
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.Water

@Composable
fun PokemonCard(pokemon: PokeModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .size(width = 170.dp, height = 200.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(2.dp)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(100.dp)
            )
            Row(
                modifier = Modifier.padding(2.dp) .fillMaxWidth()
            ) {
                Text(
                    text = pokemon.name.replaceFirstChar { it.titlecase() },
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "#${pokemon.id}",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(2.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                pokemon.types.forEach { typeSlot ->
                    TileName(
                        typeName = typeSlot.type.name,
                        modifier = Modifier.padding(2.dp)
                    )
                }
            }

        }
    }
}

@Composable
fun TileHW(
    height: Int,
    weight: Int,
    modifier: Modifier
) {
    Card(
        modifier = Modifier.padding(2.dp)
            .size(width = 100.dp, height = 170.dp)
    ) {
        Row {
            Row {
                Text(
                    text = "${height} M"
                )
                Icon(
                    imageVector = Icons.Default.Height,
                    contentDescription = "pokeHeight",
                    modifier = Modifier.padding(end = 2.dp)
                )
            }
            Row {
                Text(
                    text = "${weight} KG"
                )
                Icon(
                    imageVector = Icons.Default.MonitorWeight,
                    contentDescription = "pokeWeight",
                    modifier = Modifier.padding(end = 2.dp),
                )
            }
        }
    }
}

@Composable
fun TileName(
    typeName: String,
    modifier: Modifier
) {
    Card(
        modifier = Modifier.padding(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = getTypeColor(typeName)
        )
    ) {
        Text(
            text = typeName.replaceFirstChar { it.uppercase() },
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
            maxLines = 1,
        )
    }
}

fun getTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "normal" -> Normal
        "fire" -> Fire
        "water" -> Water
        "grass" -> Grass
        "electric" -> Electric
        "ice" -> Ice
        "fighting" -> Fighting
        "poison" -> Poison
        "ground" -> Ground
        "flying" -> Flying
        "psychic" -> Psychic
        "bug" -> Bug
        "rock" -> Rock
        "ghost" -> Ghost
        "dragon" -> Dragon
        "dark" -> Dark
        "steel" -> Steel
        "fairy" -> Fairy

        else -> {
            Color.Gray
        }
    }
}