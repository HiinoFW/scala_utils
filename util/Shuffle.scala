package util

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object Shuffle {
	def apply[T](input: T*) = {
		val arr = input.to[ArrayBuffer]
		@tailrec
		def shuffle_rec(n: Int, acc: Seq[T]): Seq[T] = n match {
			case 0 => acc
			case i => shuffle_rec(i-1, acc :+ arr.remove(Random.get.nextInt(i)))
		}
		shuffle_rec(arr.length, Seq())
	}
}