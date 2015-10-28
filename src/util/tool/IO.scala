package util.tool

object IO {
  def using[A <: { def close(): Unit }, B](param: A)(f: A => B): B =
    try {
      f(param)
    } finally {
      param.close()
    }

  def usingFile[B](name: String)(f: io.BufferedSource => B) =
    using(io.Source.fromFile(name))(file => f(file))
    
  def readFileToString(name: String) =
    usingFile(name)(_.mkString)
}