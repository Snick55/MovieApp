package com.android.movieapp.domain

import com.android.movieapp.core.Dispatchers
import com.android.movieapp.data.HandleError

interface Interactor {

    suspend fun <T> handle(
        successful:  (T) -> Unit,
        atFinish:  () -> Unit,
        block: suspend () -> T
    )

    abstract class Abstract(
        private val dispatchers: Dispatchers,
        private val handleError: HandleError
    ) : Interactor {

        override suspend fun <T> handle(
            successful:  (T) -> Unit,
            atFinish:  () -> Unit,
            block: suspend () -> T
        ) {
            try {
                val result = block.invoke()
                dispatchers.changeToUI { successful.invoke(result) }
            } catch (error: Exception) {
                handleError.handle(error)
            } finally {
                dispatchers.changeToUI { atFinish.invoke() }
            }
        }
    }
}