package util.geometry

class Vector(val system: CoordSystem)(val dx: Double, val dy: Double) {
  val (dxAbs, dyAbs) = system match {
    case BaseCoordSystem => (dx, dy)
    case default => (
      dx * system.i.dx + dy * system.j.dx,
      dx * system.i.dy + dy * system.j.dy
    )
  }
    
  val coords = (dx, dy)
  val coordsAbs = (dxAbs, dyAbs)
  def abs = Vector(coordsAbs)
  def rel = Vector(coords)
  
  def coordsIn(cs: CoordSystem) = cs getCoordsOf this
  def in(cs: CoordSystem) = Vector(cs)(coordsIn(cs))
    
//	def +(v: Vector) = Vector(system)(dx + v.dx, dy + v.dy)
//	def -(v: Vector) = Vector(system)(dx - v.dx, dy - v.dy)
  def *(i: Double) = Vector(system)(dx * i, dx * i)
  def /(i: Double) = Vector(system)(dx / i, dy / i)
  
//  def *(v: Vector) = dx*v.dx + dy*v.dy
  
  def isParallelTo(v: Vector) = dx*v.dy == dy*v.dx
  
  override def toString = "Vector(" + dxAbs + ", " + dyAbs + ")"
}

object Vector {
  def apply(cs: CoordSystem)(dxy: (Double, Double)) = new Vector(cs)(dxy._1, dxy._2)
  def apply(dxy: (Double, Double)) = new Vector(BaseCoordSystem)(dxy._1, dxy._2)
  
  def unapply(v: Vector) = Some(v.system, v.dx, v.dy)
}

case object NullVector extends Vector(BaseCoordSystem)(0, 0)