package com.emiliagomez.a243700_examenu3_moviles.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.emiliagomez.a243700_examenu3_moviles.navigation.Destination
import com.emiliagomez.a243700_examenu3_moviles.navigation.SharedTab
import com.emiliagomez.a243700_examenu3_moviles.navigation.navigateToDetails
import com.emiliagomez.a243700_examenu3_moviles.presentation.viewModels.PokeViewModel
import com.emiliagomez.a243700_examenu3_moviles.presentation.views.components.SearchSuggestionItem
import com.emiliagomez.a243700_examenu3_moviles.ui.theme.BackgroundColor
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
                .background(BackgroundColor)
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
                onSearch = { searchQuery ->
                    // No hacer nada, búsqueda en tiempo real
                },
                active = active,
                onActiveChange = { isActive ->
                    active = isActive
                    if (!isActive && query.isEmpty()) {
                        viewModel.clearSearch()
                    }
                },
                placeholder = {
                    Text(
                        "Buscar Pokémon...",
                        color = Color.Gray
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = Color.Gray
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
                            contentDescription = "Limpiar",
                            tint = Color.Gray
                        )
                    }
                },
                colors = SearchBarDefaults.colors(
                    containerColor = Color.White,
                    dividerColor = Color.LightGray
                )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    when {
                        query.length < 2 -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = null,
                                        modifier = Modifier.size(48.dp),
                                        tint = Color.Gray.copy(alpha = 0.5f)
                                    )
                                    Text(
                                        text = "Escribe para buscar",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                        searchResults.isEmpty() -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Text(
                                        text = "No se encontraron Pokémon",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Text(
                                        text = "Intenta con otro nombre",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }

                        else -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
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

                                    if (pokemon != searchResults.last()) {
                                        Divider(
                                            modifier = Modifier.padding(horizontal = 16.dp),
                                            color = Color.LightGray.copy(alpha = 0.5f)
                                        )
                                    }
                                }
                            }
                        }
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
                            color = ClrWhite,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Toca la barra de búsqueda para empezar",
                            style = MaterialTheme.typography.bodyMedium,
                            color = ClrWhite.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
