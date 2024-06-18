package com.fetchassignment.displaylist.usecase

import com.fetchassignment.displaylist.repository.HiringService
import com.fetchassignment.displaylist.repository.model.BaseInfo
import com.fetchassignment.displaylist.repository.model.Info
import com.fetchassignment.displaylist.repository.model.SectionHeadingInfo
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DisplayListUseCaseImplTest {
    private lateinit var useCase: DisplayListUseCase

    @Mock
    lateinit var service: HiringService

    @Before
    fun setup() {
        useCase = DisplayListUseCaseImpl(service)
    }

    @Test
    fun given_getHiringData_return_empty_list_when_getHiringData_is_called_then_assert_empty() =
        runTest {
            // given
            `when`(service.getDisplayList()).thenReturn(listOf())
            // when
            val result = useCase.fetchListInfo()
            // then
            assertEquals(listOf<BaseInfo>(), result)
        }


    @Test
    fun given_getHiringData_return_null_as_name_list_when_getHiringData_is_called_then_assert_empty() {
        // give
        runTest {
            val data = listOf(
                Info(1, 1, null)
            )
            `when`(service.getDisplayList()).thenReturn(data)
            // when
            val result = useCase.fetchListInfo()
            // then
            assertEquals(listOf<BaseInfo>(), result)
        }
    }


    @Test
    fun given_getHiringData_return_null_string_as_name_list_when_getHiringData_is_called_then_assert_empty() {
        runTest {
            // given
            val data = listOf(
                Info(1, 1, "null")
            )
            `when`(service.getDisplayList()).thenReturn(data)
            // when
            val result = useCase.fetchListInfo()
            // then
            assertEquals(listOf<BaseInfo>(), result)
        }
    }


    @Test
    fun given_getHiringData_return_empty_string_as_name_list_when_getHiringData_is_called_then_assert_empty() {
        runTest {
            // give
            val data = listOf(
                Info(1, 1, "")
            )
            `when`(service.getDisplayList()).thenReturn(data)
            // when
            val result = useCase.fetchListInfo()
            // then
            assertEquals(listOf<BaseInfo>(), result)
        }
    }


    @Test
    fun given_getHiringData_returns_list_when_getHiringData_is_called_then_assert_empty() {
        runTest {
            // give
            val data = listOf(
                Info(1, 11, "name1"),
                Info(2, 12, "name2"),
                Info(3, 13, "name3"),
                Info(4, 14, "name4")
            )
            `when`(service.getDisplayList()).thenReturn(data)
            // when
            val result = useCase.fetchListInfo()
            // then
            val expectedResult = listOf(
                SectionHeadingInfo(11),
                Info(1, 11, "name1"),
                SectionHeadingInfo(12),
                Info(2, 12, "name2"),
                SectionHeadingInfo(13),
                Info(3, 13, "name3"),
                SectionHeadingInfo(14),
                Info(4, 14, "name4")
            )
            assertEquals(expectedResult, result)
        }
    }
}