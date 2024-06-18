package com.fetchassignment.displaylist.ui

import com.fetchassignment.core.ui.commons.ScreenState
import com.fetchassignment.displaylist.repository.model.BaseInfo
import com.fetchassignment.displaylist.repository.model.Info
import com.fetchassignment.displaylist.usecase.DisplayListUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class HiringViewModelTest {

    @Mock
    private lateinit var useCase: DisplayListUseCaseImpl

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `given useCase getHiringData return success when getHiringData is called then asset the array values`() {
        runTest {
            val expectedData = listOf<BaseInfo>(
                Info(1, 3, "name")
            )

            Mockito.`when`(useCase.fetchListInfo()).thenReturn(expectedData)
            // when
            val viewModel = DisplayListViewModel(useCase)

            // then
            assertEquals(ScreenState.Success(expectedData), viewModel.screenState)
        }
    }


    @Test
    fun `given useCase getHiringData return error when getHiringData is called then asset error`() {

        runTest {
            // given
            val exception = RuntimeException("This is an error")
            Mockito.`when`(useCase.fetchListInfo()).thenThrow(exception)
            // when
            val viewModel = DisplayListViewModel(useCase)

            // then
            assertTrue(viewModel.screenState is ScreenState.Error)
            assertEquals(ScreenState.Error(exception.message), viewModel.screenState)
        }
    }
}
