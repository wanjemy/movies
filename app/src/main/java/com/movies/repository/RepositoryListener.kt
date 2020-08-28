package com.movies.repository

interface RepositoryListener<S, F> {
    fun onSuccessListener(success: S)
    fun onFailureListener(failure: F)
}