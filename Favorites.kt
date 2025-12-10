package com.emiliagomez.a243700_examenu3_moviles.presentation.views

import SwipeToDelete
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwipeLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
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
        modifier = Modifier.fillMaxSize(),
        containerColor = BackgroundColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(BackgroundColor)
        ) {
            when {
                favorites.isEmpty() -> {
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
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(
                            count = favorites.size,
                            key = { index -> favorites[index].id }
                        ) { index ->
                            val fav = favorites[index]
                            SwipeToDelete(
                                favoritePokemon = fav,
                                viewModel = viewModel,
                                onClick = {
                                    viewModel.searchPokemon(fav.name)
                                    navController.navigateToDetails(fav.name)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteCard(
    favoritePokemon: PokeFavorites,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = favoritePokemon.imageUrl,
                contentDescription = favoritePokemon.name,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = favoritePokemon.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    favoritePokemon.types.split(",").forEach { typeName ->
                        val cleanType = typeName.trim()
                        if (cleanType.isNotEmpty()) {
                            TileName(
                                typeName = cleanType,
                                modifier = Modifier
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.SwipeLeft,
                        contentDescription = "Swipe Left",
                        tint = ClrGray,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Desliza para eliminar",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}