package com.fetchassignment.displaylist.usecase

import com.fetchassignment.displaylist.repository.HiringService
import com.fetchassignment.displaylist.repository.model.BaseInfo
import com.fetchassignment.displaylist.repository.model.Info
import com.fetchassignment.displaylist.repository.model.SectionHeadingInfo
import javax.inject.Inject

class DisplayListUseCaseImpl @Inject constructor(
    private val service: HiringService
) : DisplayListUseCase {
    override suspend fun fetchListInfo(): List<BaseInfo> {
        val result = service.getDisplayList()
        return formatData(result)
    }

    private fun formatData(infoList: List<Info>): List<BaseInfo> {
        val list = removeNonNullOrBlankFromList(infoList)
        return groupListByListId(list)
    }

    /**
     * Filtering the list by name
     */
    private fun removeNonNullOrBlankFromList(list: List<Info>): List<Info> {
        return list.filter { it.name != null && it.name != "" && it.name != "null" }
    }

    /**
     *  Sorting list by list Id
     */
    private fun groupListByListId(list: List<Info>): MutableList<BaseInfo> {
        val displayListWithSectionHeadingList = mutableListOf<BaseInfo>()
        val groupedData = list.groupBy() { it.listId }
        groupedData.keys.sorted().forEach { key ->
            groupedData[key]?.let {
                displayListWithSectionHeadingList.add(SectionHeadingInfo(key))
                val sectionList = getSortedSectionListData(it)
                displayListWithSectionHeadingList.addAll(sectionList)
            }
        }
        return displayListWithSectionHeadingList
    }

    /**
     * Sorts the list by id. NOTE: I am sorting by id because the name is
     * made by adding "item" and id. This help me reduce string operations
     */
    private fun getSortedSectionListData(sectionList: List<Info>): List<BaseInfo> {
        return sectionList.sortedBy { it.id }
    }
}