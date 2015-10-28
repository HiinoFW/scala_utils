package util.geometry

case class Rect(x1: Int, y1: Int, w1: Int, h1: Int) {
	val (x, w) = if (w1 < 0) (x1 + w1, -w1) else (x1, w1)
	val (y, h) = if (h1 < 0) (y1 + h1, -h1) else (y1, h1)
	
	val p = Point(x, y)
	val v = Vector(w, h)
	
	val center = Point(x + w/2, y + h/2)
}

