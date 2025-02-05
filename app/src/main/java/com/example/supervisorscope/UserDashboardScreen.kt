package com.example.supervisorscope

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserDashboardScreen(
    userViewModel: UserViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    // Observe LiveData with default values
    val userProfile by userViewModel.userProfile.observeAsState()
    val userPosts by userViewModel.userPosts.observeAsState(emptyList())
    val errorMessage by userViewModel.errorMessage.observeAsState()

    // Fetch data when screen is launched
    LaunchedEffect(Unit) {
        userViewModel.fetchUserData(userId = 1)
    }

    Column(modifier = modifier.padding(16.dp)) { // Apply modifier here
        Text(text = "User Dashboard", style = MaterialTheme.typography.headlineLarge)

        // Display error message if any
        errorMessage?.let {
            Text(text = "Error: $it", color = MaterialTheme.colorScheme.error)
        }

        // Display User Profile if available
        userProfile?.let { profile ->
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Name: ${profile.name}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Email: ${profile.email}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }

        // Display User Posts in a LazyColumn
        if (userPosts.isNotEmpty()) {
            LazyColumn {
                items(userPosts) { post ->
                    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = post.title, style = MaterialTheme.typography.bodyLarge)
                            Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}
