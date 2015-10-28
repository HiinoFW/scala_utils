package util

import scala.annotation.tailrec

trait Pondered[T] {
	class Ponderation(val pnd: Int, val item: T) extends Tuple2[Long, T](pnd, item)
	
	var items: Seq[Ponderation] = Seq()
	var size = 0
	var ptr = (0, -1)
	
	def add(i: Int, item: T) {
		size += i
		items = items :+ new Ponderation(i, item)
	}
	
	@tailrec
	private def rec_fetch(i: Int): T = {
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
	
	def fetch(i: Int): T = {
		rec_fetch(i % size)
	}
	
	def fetch(i: Long): T = {
		rec_fetch(i % size toInt)
	}

	def reset {
		ptr = (0, -1)
	}

	def get: T
}