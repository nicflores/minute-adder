package com.affinipay.minuteadder

import org.scalacheck.Prop.forAllNoShrink
import org.scalacheck.Gen
import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

import com.affinipay.minuteadder.parser.TimeExtractor
import com.affinipay.minuteadder.domain.HMMTime

import java.text.DecimalFormat

class TimeParserTest extends Properties("TimeParserTest") {

  val formatter = new DecimalFormat("#00")

  val timeGen: Gen[String] = for {
    hour <- Gen.oneOf(1 to 12)
    mins <- Gen.oneOf(0 to 59)
    fixmins = formatter.format(mins)
    ampm <- Gen
      .oneOf(Seq("AM", "Am", "am", "aM", "PM", "Pm", "pm", "pM"))
    time = s"$hour:$fixmins $ampm"
  } yield time

  property("gen testing") = {
    forAll(timeGen) { s =>
      (TimeExtractor
        .timeparser(s))
        .isRight
    }
  }
}
