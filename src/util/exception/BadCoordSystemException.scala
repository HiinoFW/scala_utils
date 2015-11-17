package util.exception

object BadCoordSystemException extends RuntimeException
case class BadCoordSystemException(msg: String) extends RuntimeException(msg)