package com.affinipay.minuteadder.parser

import com.affinipay.minuteadder.domain.HMMTime

object TimeExtractor extends TimeParser {
  case class ParseError(msg: String)
  def timeparser(time: String): Either[ParseError, HMMTime] = {
    parse(parseTime, time) match {
      case Success(result, next) => Right(result)
      case Failure(msg, next)    => Left(ParseError(msg))
      case Error(msg, next)      => Left(ParseError(msg))
    }
  }
}
