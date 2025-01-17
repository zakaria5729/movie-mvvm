package com.zakariahossain.moviemvvm.data.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status, val message: String) {
    companion object {
        val LOADING: NetworkState = NetworkState(Status.RUNNING, "Running")
        val LOADED: NetworkState = NetworkState(Status.SUCCESS, "Success")
        val ERROR: NetworkState = NetworkState(Status.FAILED, "Something went wrong")
    }
}