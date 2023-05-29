package com.nrkei.microprocess.step.unit

import com.nrkei.microprocess.step.needs.IntegerNeed
import com.nrkei.microprocess.step.needs.IntegerValueNeed
import com.nrkei.microprocess.step.needs.NeedState
import com.nrkei.microprocess.step.needs.Status
import com.nrkei.microprocess.step.util.RoleBasedLabel
import com.nrkei.microprocess.step.util.TestRole
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class RoleTest {

    private lateinit var amountNeed: IntegerNeed
    private lateinit var insufficientFundsNeed: IntegerValueNeed

    @BeforeEach
    fun setup() {
        amountNeed = IntegerNeed.positiveWithMax(RoleBasedLabel.MAKER_LABEL, 100)
        insufficientFundsNeed = IntegerValueNeed(RoleBasedLabel.CHECKER_LABEL, 0)
    }

    @Test
    fun `delete by role`() {
        Status().also { status ->
            status inject amountNeed
            status inject insufficientFundsNeed
            assertEquals(NeedState.UNSATISFIED, status.state)

            amountNeed be 50
            insufficientFundsNeed be 20

            assertEquals(NeedState.PROBLEM, status.state)

            status.keepOnly(TestRole.MAKER)

            assertEquals(NeedState.SATISFIED, status.state)
            assertThrows<IllegalArgumentException> { status[RoleBasedLabel.CHECKER_LABEL] }
            assertEquals(50, status[RoleBasedLabel.MAKER_LABEL].currentValue())
        }
    }
}
