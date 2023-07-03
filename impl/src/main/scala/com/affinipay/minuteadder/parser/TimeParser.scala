package com.affinipay.minuteadder.parser

import com.affinipay.minuteadder.domain.HMMTime
import scala.util.parsing.combinator._

class TimeParser extends RegexParsers {
  override def skipWhitespace: Boolean = false
  def hour = """(1[0-2]|0?[1-9])""".r ^^ { _.toInt }
  def colon = ":"
  def minute = """([0-5][0-9])""".r ^^ { _.toInt }
  def space = whiteSpace ^^ { _.toCharArray }
  def morning = """([Aa][Mm])""".r ^^ { _.toString }
  def afternoon = """([Pp][Mm])""".r ^^ { _.toString }

  def parseTime = (hour ~ colon ~ minute ~ space ~ (morning | afternoon)) ^^ {
    case h ~ c ~ m ~ _ ~ ap =>
      HMMTime(h, m, ap.toUpperCase)
  }
}
