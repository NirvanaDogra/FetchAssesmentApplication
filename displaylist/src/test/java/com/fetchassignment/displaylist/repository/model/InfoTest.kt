package com.fetchassignment.displaylist.repository.model

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InfoTest {
    @Test
    fun given_InfoValues_assert_values() {
        // given
        val info = Info(1, 1, "name1")
        // when
        // then
        assertEquals(1, info.id)
        assertEquals(1, info.listId)
        assertEquals("name1", info.name)
    }
}