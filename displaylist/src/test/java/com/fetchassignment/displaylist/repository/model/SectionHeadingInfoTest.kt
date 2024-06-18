package com.fetchassignment.displaylist.repository.model

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SectionHeadingInfoTest {
    @Test
    fun givenSectionHeadingValues_assert_values() {
        // given
        val info = SectionHeadingInfo(1)
        // when
        // then
        assertEquals(1, info.section)
    }
}