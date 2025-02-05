package com.example.supervisorscope

import androidx.lifecycle.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserViewModel : ViewModel() {

    private val _userProfile = MutableLiveData<UserProfile?>()
    val userProfile: LiveData<UserProfile?> = _userProfile

    private val _userPosts = MutableLiveData<List<UserPost>>(emptyList())  // Ensure an empty list is the default value
    val userPosts: LiveData<List<UserPost>> = _userPosts

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> = _errorMessage

    private val apiService = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    fun fetchUserData(userId: Int) {
        viewModelScope.launch {
            supervisorScope {  // Supervisor Scope Starts Here
                val profileJob = async {
                    try {
                        _userProfile.postValue(apiService.getUserProfile(userId))
                    } catch (e: Exception) {
                        _errorMessage.postValue("Failed to fetch profile: ${e.message}")
                    }
                }

                val postsJob = async {
                    try {
                        _userPosts.postValue(apiService.getUserPosts(userId))
                    } catch (e: Exception) {
                        _errorMessage.postValue("Failed to fetch posts: ${e.message}")
                    }
                }

                profileJob.await()
                postsJob.await()
            }
        }
    }
}
