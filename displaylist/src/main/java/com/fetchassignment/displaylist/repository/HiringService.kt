package com.fetchassignment.displaylist.repository

import com.fetchassignment.displaylist.repository.model.Info
import retrofit2.http.GET

/**
 * This can be moved to the common-data module if used in one
 * or more places.
 */
interface HiringService {
    @GET("/hiring.json")
    suspend fun getDisplayList(): List<Info>
}