package com.fetchassignment.displaylist.usecase

import com.fetchassignment.displaylist.repository.model.BaseInfo

interface DisplayListUseCase {
    suspend fun fetchListInfo(): List<BaseInfo>
}