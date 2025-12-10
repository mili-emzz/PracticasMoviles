package com.emiliagomez.a243700_examenu3_moviles.presentation.views

import SwipeToDelete
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwipeLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.emiliagomez.a243700_examenu3_moviles.navigation.Destination
import com.emiliagomez.a243700_examenu3_moviles.navigation.SharedTab
import com.emiliagomez.a243700_examenu3_moviles.navigation.navigateToDetails
import com.emiliagomez.a243700_examenu3_moviles.pokeRoomComponents.pokeModel.PokeFavorites
import com.emiliagomez.a243700_examenu3_moviles.pokeapi.pokeModels.PokeModel
import com.emiliagomez.a243700_examenu3_moviles.presentation.viewModels.PokeViewModel
import com.emiliagomez.a243700_examenu3_moviles.presentation.views.components.TileName
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.BackgroundColor
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.ClrGray
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.ClrWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesView(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: PokeViewModel
) {

    val favorites by viewModel.getAllFavorites().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Favoritos",
                            fontWeight = FontWeight.Black,
                            fontSize = 45.sp,
                            color = ClrWhite
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = BackgroundColor
                    )
                )
                SharedTab(
                    navController = navController,
                    selectedDestination = Destination.FAVORITES
                )
            }
        },
        modifier = Modifier.fillMaxSize()

    ) { innerPading ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPading)
                .background(BackgroundColor)
        ) {
            if (favorites.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                ) {
                    Text(
                        text = "No tienes favoritos aún",
                        style = MaterialTheme.typography.titleLarge,
                        color = ClrWhite,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Agrega Pokémon a favoritos para verlos aquí",
                        style = MaterialTheme.typography.bodyMedium,
                        color = ClrWhite.copy(alpha = 0.7f),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)

                ) {
                    items(
                        items = favorites,
                        key = { it.id }
                    ) { favoritePokemon ->
                        SwipeToDelete(
                            favoritePokemon = favoritePokemon,
                            viewModel = viewModel,
                            onCardClick = {
                                navController.navigateToDetails(favoritePokemon.name)
                            }
                        )
                    }
                }
            }
        }

        @Composable
        fun FavoriteCard(
            favoritePokemon: PokeFavorites,
            onCardClick: () -> Unit
        ) {
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.padding(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AsyncImage(
                        model = favoritePokemon.imageUrl,
                        contentDescription = favoritePokemon.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                    Column(
                        modifier = Modifier.padding(2.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = favoritePokemon.name.replaceFirstChar { it.titlecase() },
                            style = MaterialTheme.typography.titleLarge
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        favoritePokemon.types.split("").forEach { typeName ->
                            TileName(
                                typeName = typeName.trim(),
                                modifier = Modifier.padding(2.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Desliza a la izquierda para borrar",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.SwipeLeft,
                        contentDescription = "Desliza para eliminar",
                        tint = ClrGray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}