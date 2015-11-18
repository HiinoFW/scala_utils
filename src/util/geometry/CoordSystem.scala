package util.geometry

import util.exception.BadCoordSystemException

//abstract class CoordSystem {
//  val system: CoordSystem
//  val o: Point
//  val i: Vector
//  val j: Vector
//}
//
//case object BaseCoordinateSystem extends CoordSystem {
//  val system = this
//}
//
//case class RelativeCoordSystem(val system: CoordSystem)(val o: Point, val i: Vector, val j: Vector) extends CoordSystem

abstract class CoordSystem {
  val system: CoordSystem
  val o: Point
  val i: Vector
  val j: Vector
  
  def getAbsOfPoint(x: Double, y: Double): (Double, Double) = this match {
    case BaseCoordSystem => (x, y)
    case default => {
      val (oxa, oya) = o.coordsAbs
      val (ixa, iya) = i.coordsAbs
      val (jxa, jya) = j.coordsAbs
      system getAbsOfPoint (
        x * ixa + y * jxa + oxa,
        x * iya + y * jya + oya
      )
    }
  }
  
  def getAbsOfVector(dx: Double, dy: Double): (Double, Double) = this match {
    case BaseCoordSystem => (dx, dy)
    case default => {
      val (oxa, oya) = o.coordsAbs
      val (ixa, iya) = i.coordsAbs
      val (jxa, jya) = j.coordsAbs
      system getAbsOfVector (
        dx * ixa + dy * jxa,
        dx * iya + dy * jya
      )
    }
  }
  
  def getCoordsOf(p: Point): (Double, Double) = this match {
    case BaseCoordSystem => p.coordsAbs
    case default => {
      val (xa, ya) = p.coordsAbs
      val (oxa, oya) = o.coordsAbs
      val (ixa, iya) = i.coordsAbs
      val (jxa, jya) = j.coordsAbs
      (
          (xa*jya - ya*jxa + oya*jxa - oxa*jya)/(ixa*jya - iya*jxa),
          (xa*iya - ya*ixa + oya*ixa - oxa*iya)/(iya*jxa - ixa*jya)
      )
    }
  }
  
  def getCoordsOf(v: Vector): (Double, Double) = this match {
    case BaseCoordSystem => v.coordsAbs
    case default => {
      val (dxa, dya) = v.coordsAbs
      val (ixa, iya) = i.coordsAbs
      val (jxa, jya) = j.coordsAbs
      (
          (dxa*jya - dya*jxa)/(ixa*jya - iya*jxa),
          (dxa*iya - dya*ixa)/(iya*jxa - ixa*jya)
      )
    }
  }
  
  override def toString = "CoordSystem(" + o + ", " + i + ", " + j + ")"
}
//
//object CoordSystem {
//  def apply(): CoordSystem =
//    CoordinateSystem(
//        Point(0, 0), 
//        Vector(1, 0),
//        Vector(0, 1)
//    )
//    
//  def apply(cs: CoordSystem)(ro: Point, ri: Vector, rj: Vector): CoordSystem =
//    CoordSystem(
//        ro + (Point() to cs.o), 
//        cs.i * ri.dx + cs.j * ri.dy,
//        cs.i * rj.dx + cs.j * rj.dy
//    )
//}

case object BaseCoordSystem extends CoordSystem {
  val system = this
  val o = Point(this)(0, 0)
  val i = Vector(this)(1, 0)
  val j = Vector(this)(0, 1)
}

class RelativeCoordSystem(val system: CoordSystem)(val o: Point, val i: Vector, val j: Vector) extends CoordSystem

object RelativeCoordSystem {
  def checkBad(i: Vector, j: Vector) =
    if (i isParallelTo j) {
      val errorMsg =
        if (i == NullVector) ("Vector " + i + " is null.")
        else if (j == NullVector) ("Vector " + j + " is null.")
        else ("Vectors " + i + " and " + j + " are parallel to each other.")
      throw BadCoordSystemException(errorMsg)
    }
  
  def apply(cs: CoordSystem)(o: Point, i: Vector, j: Vector) = {
    checkBad(i, j)
    new RelativeCoordSystem(cs)(o, i, j)
  }
  
  def apply(o: Point, i: Vector, j: Vector) = {
    checkBad(i, j)
    new RelativeCoordSystem(BaseCoordSystem)(o, i, j)
  }
  
  def unapply(cs: CoordSystem) = Some(cs.system, cs.o, cs.i, cs.j)
}