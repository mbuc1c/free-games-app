package com.example.freegamesapp.domain.util

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}

inline fun <T> Result<T>.onSuccess(action: (T) -> Unit) {
    if (this is Result.Success) {
        action(data)
    }
}