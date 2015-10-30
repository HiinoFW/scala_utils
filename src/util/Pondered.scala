package util

import scala.annotation.tailrec

trait Pondered[T] {
	class Ponderation(val pnd: Double, val item: T) extends Tuple2[Double, T](pnd, item)
	
	var items: Seq[Ponderation] = Seq()
	var size = 0D
	var ptr = (0D, -1)
	
	def add(i: Double, item: T) {
		size += i
		items = items :+ new Ponderation(i, item)
	}
	
	@tailrec
	private def rec_fetch(i: Double): T = {
		if (i < ptr._1) {
			ptr = (ptr._1 - i, ptr._2)
			items(ptr._2).item
		}
		else {
			val newi = i - ptr._1
			val newitemid = (ptr._2 + 1) % items.length
			ptr = (items(newitemid).pnd, newitemid)
			rec_fetch(newi)
		}
	}
	
	def fetch(i: Double): T = {
		rec_fetch(i % size)
	}

	def reset {
		ptr = (0D, -1)
	}

	def get: T
}