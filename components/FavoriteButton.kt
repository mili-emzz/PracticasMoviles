package com.emiliagomez.a243700_examenu3_moviles.presentation.views.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.emiliagomez.a243700_examenu3_moviles.pokeapi.pokeModels.PokeModel
import com.emiliagomez.a243700_examenu3_moviles.presentation.viewModels.PokeViewModel

@Composable
fun FavoriteButton(
    pokemon: PokeModel,
    viewModel: PokeViewModel,
    modifier: Modifier = Modifier
) {
    val isFavorite by viewModel.isFavorite(pokemon.id).collectAsState(initial = false)

    IconButton(
        onClick = { viewModel.toggleFavorite(pokemon) },
        modifier = modifier,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = if (isFavorite) Color.Red else Color.Gray
        )
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = if (isFavorite) "Quitar de favoritos" else "Agregar a favoritos",
            modifier = Modifier.size(32.dp)
        )
    }
}