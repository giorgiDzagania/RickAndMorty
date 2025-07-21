package com.example.rickandmorty.domain.internet_connection

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {

    val isConnected: Flow<Boolean>
}
