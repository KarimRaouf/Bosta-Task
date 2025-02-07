package com.example.bosttask.presentation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.clickable
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bosttask.core.utilits.Resource
import com.example.bosttask.data.model.Cities
import com.example.bosttask.data.model.District

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryAreaScreen(viewModel: CitiesViewModel = hiltViewModel()) {
    val citiesState = viewModel.citiesState.collectAsState(initial = Resource.Idle())
    var isLoading by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    var expandedCity by remember { mutableStateOf<String?>(null) }

    when (val state = citiesState.value) {
        is Resource.Loading -> {
            isLoading = true
        }
        is Resource.Success -> {
            isLoading = false
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("Choose the delivery area") },
                        actions = {
                            IconButton(onClick = { /* Handle close */ }) {
                                Icon(Icons.Default.Close, contentDescription = "Close")
                            }
                        }
                    )
                }
            ) { paddingValues ->
                Column(modifier = Modifier.padding(paddingValues)) {
                    // Search bar
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        singleLine = true,
                        decorationBox = { innerTextField ->
                            if (searchQuery.isEmpty()) {
                                Text(
                                    text = "City/Area",
                                    color = Color.Gray,
                                    fontSize = 16.sp
                                )
                            }
                            innerTextField()
                        }
                    )

                    state.data?.data?.let { cities ->
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            items(cities) { city ->
                                CityItem(city)
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                    }
                }
            }
        }
        is Resource.Error -> {
            isLoading = false
            Text(
                text = "Error: ${state.errorResponse?.message ?: "Unknown error"}",
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.error
            )
        }
        is Resource.Idle -> {
            // Handle idle state
        }
    }
}

@Composable
fun CityItem(city: Cities) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = city.cityName, style = MaterialTheme.typography.titleLarge)
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                contentDescription = "Expand/Collapse"
            )
        }

        if (isExpanded) {
            Column(modifier = Modifier.padding(start = 16.dp, top = 8.dp)) {
                city.districts.forEach { district ->
                    DistrictItem(district)
                }
            }
        }
    }
}

@Composable
fun DistrictItem(district: District) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = district.districtName,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = if (district.pickupAvailability && district.dropOffAvailability) "Covered" else "Uncovered",
            color = if (district.pickupAvailability && district.dropOffAvailability) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodySmall
        )
    }
}