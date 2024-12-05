package com.example.androidassignment.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidassignment.domain.model.TrendingHashtag
import com.example.androidassignment.domain.usecase.GetTrendingPostsUseCase
import com.example.androidassignment.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrendingPostsViewModel @Inject constructor(
    private val getTrendingPostsUseCase: GetTrendingPostsUseCase
): ViewModel() {

    private val _trendingPosts = MutableLiveData<Response<List<TrendingHashtag>>>()
    val trendingPosts: LiveData<Response<List<TrendingHashtag>>> = _trendingPosts

    fun fetchTrendingPosts() = viewModelScope.launch {
        _trendingPosts.value = Response.Loading()
        getTrendingPostsUseCase.invoke().let {
            _trendingPosts.value = it
        }
    }
}