package com.example.rickandmorty.core

object RoomCallHelper {

    suspend fun <T> safeRoomCall(
        dbCall: suspend () -> T
    ): OperationStatus<T> {
        return try {
            val result = dbCall.invoke()
            OperationStatus.Success(result)
        } catch (e: Exception) {
            OperationStatus.Failure(e)
        }
    }
}
