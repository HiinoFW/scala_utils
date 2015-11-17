package util.geometry

class Point(val system: CoordSystem)(val x: Double, val y: Double) {
  val (xAbs, yAbs) = system getAbsOf (x, y)
  
  val coords = (x, y)
  def rel = Point(coords)
  val coordsAbs = (xAbs, yAbs)  
  def abs = Point(coordsAbs)
  
  def coordsIn(cs: CoordSystem) = cs getCoordsOf this
  def in(cs: CoordSystem) = Point(cs)(coordsIn(cs))
	
	def +(v: Vector) = Point(x + v.dx, y + v.dy)
	def -(v: Vector) = Point(x - v.dx, y - v.dy)
  def ==(p: Point) = (xAbs, yAbs) == (p.xAbs, p.yAbs)
  
  def to(p: Point) = Vector(p.x - x, p.y - y)
  def from(p: Point) = Vector(x - p.x, y - p.y)
  
  override def toString = "Point(" + xAbs + ", " + yAbs + ")"
  
  //def isIn(rect: Rect) = rect contains this
}

object Point {
  def apply(cs: CoordSystem)(xy: (Double, Double)) = new Point(cs)(xy._1, xy._2)
  def apply(xy: (Double, Double)) = new Point(BaseCoordSystem)(xy._1, xy._2)
  
  def unapply(p: Point) = Some(p.system, p.x, p.y)
}

class Origin(cs: CoordSystem) extends Point(cs)(0, 0)

object Origin {
  def apply(cs: CoordSystem) = new Origin(cs)
  def unapply(o: Origin) = Some(o.system)
}

case object BaseOrigin extends Origin(BaseCoordSystem)