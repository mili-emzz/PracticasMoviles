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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SwipeLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.emiliagomez.a243700_examenu3_moviles.presentation.viewModels.PokemonUiState
import com.emiliagomez.a243700_examenu3_moviles.presentation.views.components.TileName
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.BackgroundColor
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.ClrGray
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.ClrWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: PokeViewModel
) {
    var query by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    val searchResults by viewModel.searchResults.collectAsState()

    Scaffold(
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "BUSCAR",
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
                    selectedDestination = Destination.SEARCH
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
        ) {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                query = query,
                onQueryChange = { newQuery ->
                    query = newQuery
                    if (newQuery.length >= 2) {
                        viewModel.searchPokemonLocal(newQuery)
                    } else {
                        viewModel.clearSearch()
                    }
                },
                onSearch = {
                },
                active = active,
                onActiveChange = { isActive ->
                    active = isActive
                    if (!isActive && query.isEmpty()) {
                        viewModel.clearSearch()
                    }
                },
                placeholder = { Text("Buscar Pokémon...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable {
                                query = ""
                                viewModel.clearSearch()
                            },
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Limpiar"
                        )
                    }
                }
            ) {
                if (query.length >= 2) {
                    if (searchResults.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No se encontraron Pokémon",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxWidth(),
                            contentPadding = PaddingValues(vertical = 8.dp)
                        ) {
                            items(
                                items = searchResults,
                                key = { it.id }
                            ) { pokemon ->
                                SearchSuggestionItem(
                                    pokemon = pokemon,
                                    onClick = {
                                        viewModel.selectPokemon(pokemon)
                                        navController.navigateToDetails(pokemon.name)
                                        active = false
                                        query = ""
                                        viewModel.clearSearch()
                                    }
                                )
                            }
                        }
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Escribe al menos 2 caracteres para buscar",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
            if (!active) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            modifier = Modifier.size(80.dp),
                            tint = ClrWhite.copy(alpha = 0.3f)
                        )
                        Text(
                            text = "Busca tu Pokémon favorito",
                            style = MaterialTheme.typography.titleLarge,
                            color = ClrWhite.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Toca la barra de búsqueda para empezar",
                            style = MaterialTheme.typography.bodyMedium,
                            color = ClrWhite.copy(alpha = 0.5f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SearchSuggestionItem(
    pokemon: PokeModel,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = pokemon.imageUrl,
            contentDescription = pokemon.name,
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = pokemon.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )

    }
}