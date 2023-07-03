package com.affinipay.minuteadder

import com.affinipay.minuteadder.domain.HMMTime
import com.affinipay.minuteadder.parser.TimeExtractor

object MinuteAdder {
  /* Please don't change the signature of this method. */
  def addMinutes(time: String, minutes: Int): String = {
    TimeExtractor.timeparser(time) match {
      case Left(value) => value.msg
      case Right(t) => {

        val tempMins = t.minute + minutes
        val hoursIn_tempMins = tempMins / 60
        val reduce_tempMins_mod_60 = tempMins % 60

        val tempHours = t.hour + hoursIn_tempMins
        val number_of_12hour_blocks_in_hoursIn_tempMins = hoursIn_tempMins / 12
        val newhour = tempHours % 12

        val newMeridiem =
          if (
            number_of_12hour_blocks_in_hoursIn_tempMins % 2 == 0 && tempHours != 12
          )
            t.meridiem
          else {
            if (t.meridiem == "AM") "PM" else "AM"
          }

        HMMTime(newhour, reduce_tempMins_mod_60, newMeridiem).toString
      }
    }
  }
}
