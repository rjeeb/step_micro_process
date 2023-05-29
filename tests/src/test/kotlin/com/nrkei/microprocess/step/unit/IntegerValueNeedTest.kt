package com.nrkei.microprocess.step.unit

import com.nrkei.microprocess.step.needs.IntegerValueNeed
import com.nrkei.microprocess.step.needs.NeedState
import com.nrkei.microprocess.step.needs.NeedState.*
import com.nrkei.microprocess.step.util.TestLabel.A
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IntegerValueNeedTest {

    @Test
    fun `valid value`() {
        IntegerValueNeed(A, 5).also {
            assertEquals(UNSATISFIED, it.state)
            it be 6
            assertEquals(PROBLEM, it.state)
            it be 5
            assertEquals(SATISFIED, it.state)
            it.reset()
            assertEquals(UNSATISFIED, it.state)
        }
    }
}
