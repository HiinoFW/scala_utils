package util

class Sequence[T](init: T, f: T => T) {
	var get = init
	
	def next = {
		skip
		get
	}
	
	def skip {
		get = f(get)
	}
		
}

class IntSequence(init: Int = 0, step: Int = 1) extends Sequence[Int](init, i => i + step)
class DoubleSequence(init: Double = 0, step: Double = 1) extends Sequence[Double](init, i => i + step)