package util.geometry

case class Vector(val dx: Int, val dy: Int) {
	def +(v: Vector) = Vector(dx + v.dx, dy + v.dy)
	def -(v: Vector) = Vector(dx - v.dx, dy - v.dy)

	def toPoint = Point(dx, dy)
}