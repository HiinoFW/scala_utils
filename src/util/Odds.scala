package util

class Odds[T] extends Pondered[T] {
	def get: T =
		fetch(size * scala.util.Random.nextDouble)
}