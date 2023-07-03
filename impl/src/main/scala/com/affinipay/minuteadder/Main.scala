package com.affinipay.minuteadder

object Main {
  def main(args: Array[String]): Unit = {
    println(
      s"Running Minute Adder with ${args.size} args: ${args.mkString(",")}"
    )
    println(MinuteAdder.addMinutes(args(0), args(1).toInt))
  }
}
