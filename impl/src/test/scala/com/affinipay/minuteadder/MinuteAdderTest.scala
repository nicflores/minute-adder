package com.affinipay.minuteadder

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class MinuteAdderTest extends AnyFunSpec with Matchers {
  val hours12_as_mins = 720

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

    it("corner case 1") {
      MinuteAdder.addMinutes("6:00 PM", hours12_as_mins) should be("6:00 AM")
    }

    it("corner case 2") {
      MinuteAdder.addMinutes("11:59 PM", 1) should be("12:00 AM")
    }

    it("corner case 3") {
      MinuteAdder.addMinutes("11:59 AM", 1) should be("12:00 PM")
    }

    it("corner case 4") {
      MinuteAdder.addMinutes("12:00 AM", hours12_as_mins) should be("12:00 PM")
    }

    it("corner case 5") {
      MinuteAdder.addMinutes("12:01 AM", hours12_as_mins) should be("12:01 PM")
    }

    it("corner case 6") {
      MinuteAdder.addMinutes("12:00 AM", hours12_as_mins * 2) should be(
        "12:00 AM"
      )
    }
  }

}
