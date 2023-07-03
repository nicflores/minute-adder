package com.affinipay.minuteadder.domain

import java.text.DecimalFormat

case class HMMTime(hour: Int, minute: Int, meridiem: String) {

  override def toString = {
    val formatter = new DecimalFormat("#00")
    val fixminutes = formatter.format(minute)

    val fixhours = if (hour == 0) "12" else hour.toString

    s"${fixhours}:${fixminutes} ${meridiem}"
  }

}
