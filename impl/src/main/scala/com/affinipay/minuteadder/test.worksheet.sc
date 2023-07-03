import java.text.DecimalFormat
import $dep.`org.scala-lang.modules::scala-parser-combinators:2.3.0`
import $dep.`org.scalatestplus::scalacheck-1-17:3.2.16.0`

import scala.util.parsing.combinator._
import scala.util.matching.Regex
import org.scalacheck.Gen

class WorksheetTimeTest extends RegexParsers {
  override def skipWhitespace: Boolean = false
  def hour = """(1[0-2]|0?[1-9])""".r ^^ { _.toInt }
  def colon = ":"
  def minute = """([0-5][0-9])""".r ^^ { _.toInt }
  def space = whiteSpace ^^ { _.toCharArray }
  def morning = """([Aa][Mm])""".r ^^ { _.toString }
  def afternoon = """([Pp][Mm])""".r ^^ { _.toString }

  def parseTime = (hour ~ colon ~ minute ~ space ~ (morning | afternoon)) ^^ {
    case h ~ c ~ m ~ _ ~ ap =>
      TimeParser.ATime(h, m, ap.toUpperCase)
  }
}

object TimeParser extends WorksheetTimeTest {
  case class ParseError(msg: String)
  def timeparser(time: String): Either[ParseError, ATime] = {
    parse(parseTime, time) match {
      case Success(result, next) => Right(result)
      case Failure(msg, next)    => Left(ParseError(msg))
      case Error(msg, next)      => Left(ParseError(msg))
    }
  }

  case class ATime(hour: Int, minute: Int, meridiem: String)
  object ATime {
    def show(t: ATime): String = {
      val fixhours = if (t.hour == 0) "12" else t.hour.toString
      val formatter = new DecimalFormat("#00")
      val fixminutes = if (t.minute == 0) "00" else t.minute.toString
      s"${fixhours}:${fixminutes} ${t.meridiem}"
    }
    def addMinutes(time: String, minutes: Int): String = {
      TimeParser.timeparser(time) match {
        case Left(value) => value.msg
        case Right(t) => {
          val tempmins = t.minute + minutes
          val hoursintempmins = tempmins / 60
          val newmins = tempmins % 60

          val temphours = t.hour + hoursintempmins
          val newhour = temphours % 12

          // val numberoftimewecrossmeridiem = temphours / 12
          val numberoftimewecrossmeridiem = newhour / 12
          println(s"ampm: $numberoftimewecrossmeridiem")

          val determinenewmeridiem =
            if (
              numberoftimewecrossmeridiem % 2 == 0 && t.hour != 12 && t.minute != 0
            ) t.meridiem
            else {
              if (t.meridiem == "AM") "PM" else "AM"
            }

          val result = ATime(newhour, newmins, determinenewmeridiem)
          show(result)
        }
      }
    }
  }

}

TimeParser.timeparser("1:20 AM")
TimeParser.timeparser("01:59 Pm")
TimeParser.timeparser("5:30 Am")
TimeParser.timeparser("12:20 pM")

// Left
TimeParser.timeparser("12:20pM")
TimeParser.timeparser("0:5 Pm")

TimeParser.ATime.addMinutes("1:20 AM", 20)
TimeParser.ATime.addMinutes("12:30 AM", 40)
TimeParser.ATime.addMinutes("12:30 AM", 30)
TimeParser.ATime.addMinutes("12:00 PM", 60)
TimeParser.ATime.addMinutes("11:30 AM", 40)
TimeParser.ATime.addMinutes("11:30 AM", 30)

TimeParser.ATime.addMinutes("01:59 Pm", 600)
TimeParser.ATime.addMinutes("12:00 PM", 720)

// val formatter = new DecimalFormat("#00")
// formatter.format(24)

val x = Gen.choose(1, 12)
x.sample.get

val y = Gen.oneOf(List("AM", "Am", "am", "aM", "PM", "Pm", "pm", "pM"))
y.sample.get

val timeGen = for {
  hour <- Gen.choose(1, 12)
  mins <- Gen.choose(0, 59)
  ampm <- Gen.oneOf(List("AM", "Am", "am", "aM", "PM", "Pm", "pm", "pM"))
} yield (hour, mins, ampm)
timeGen.sample.get

// TODO
// Handle errors when parser fails

val tweleveHours = 720
val m = (0 + tweleveHours) % 60
val h = tweleveHours / 60
val nh = (12 + h) % 24
val c = nh / 12
