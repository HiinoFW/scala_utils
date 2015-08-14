package util

class Odds[T] extends Pondered[T] {
	def get: T =
		fetch(Random.get.nextInt(size))
}