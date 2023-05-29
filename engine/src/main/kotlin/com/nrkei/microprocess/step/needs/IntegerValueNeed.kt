package com.nrkei.microprocess.step.needs

class IntegerValueNeed(override val label: NeedLabel, private val expectedValue: Int) : LabeledNeed {

    private var value: Int? = null

    override val state: NeedState
        get() {
            return when (value) {
                null -> NeedState.UNSATISFIED
                expectedValue -> NeedState.SATISFIED
                else -> NeedState.PROBLEM
            }
        }

    infix fun be(value: Int) {
        this.value = value
    }

    override fun currentValue() = value

    fun reset() {
        this.value = null
    }

    override fun clone() = IntegerValueNeed(label, expectedValue).also { it.value = value }

    override fun equals(other: Any?) =
        this === other || other is IntegerValueNeed && this.equals(other)

    private fun equals(other: IntegerValueNeed) =
        this.label == other.label && this.value == other.value

    override fun hashCode() = value.hashCode() * 37 + label.hashCode()
}
