package com.affinipay.minuteadder

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class MinuteAdderTest extends AnyFunSpec with Matchers {
  
  describe("addMinutes") {
    it("should be implemented") {
      noException should be thrownBy MinuteAdder.addMinutes("5:02 PM", 1)
    }

    it("should add minutes") {
      MinuteAdder.addMinutes("5:02 PM", 1) should be("5:03 PM")
    }

    it("should add a lot of minutes") {
      MinuteAdder.addMinutes("5:02 PM", 60) should be("6:02 PM")
    }
  }

}