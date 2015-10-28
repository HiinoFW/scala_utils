package util.geometry

case class Point(val x: Int, val y: Int) {	
	def to(x: Int, y: Int) = Vector(x - this.x, y - this.y)
	def to(p: Point): Vector = to(p.x, p.y)
	
	def in(rect: Rect) = {
		x >= rect.x &&
		x < (rect.x + rect.w) &&
		y >= rect.y &&
		y < (rect.y + rect.h)		
	}
	
	def from(x: Int, y: Int) = Vector(this.x - x, this.y - y)
	def from(p: Point): Vector = from(p.x, p.y)
	
	def toVector = Vector(x, y)
	
	def +(v: Vector) = Point(x + v.dx, y + v.dy)
	def -(v: Vector) = Point(x - v.dx, y - v.dy)
}