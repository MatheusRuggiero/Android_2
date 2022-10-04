package br.com.tecnomotor.marvin.controller.test.flags

import br.com.tecnomotor.commonrail.device.commands.result.TestResult
import br.com.tecnomotor.commonrail.device.utils.EnumTestStatus

class TestFlagsControl() {
    private var valigFlags: ArrayList<TestFlags> = arrayListOf()
    var flag: TestFlags = TestFlags.flgNone
    fun clearFlag() { flag = TestFlags.flgNone}
    fun isValid() = valigFlags.contains(flag)
    fun setStatus(testResult: TestResult) {
        when(flag) {
            TestFlags.flgSkipRotation -> testResult.status = EnumTestStatus.TEST_SKIPPED_ROTATION
            else -> { }
        }
    }
    fun addValidFlags(flag: TestFlags):TestFlagsControl {
        valigFlags.add(flag)
        return this
    }
    fun clearValidFlags() {
        valigFlags.clear()
    }
}