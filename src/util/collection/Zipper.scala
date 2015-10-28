package util.collection

import util.exception.EndOfZipperException

case class Zipper[T](val left: List[T], val right: List[T]) {
	
	def shift = Zipper(right.head :: left, right.tail)
	
	def unshift = Zipper(left.tail, left.head :: right)
	
	def reset = Zipper(this.getFull)
	
	def getFull = left.reverse ::: right
	
	def insert(t: T) = Zipper(left, t :: right)
  
  def get = {
    val t :: _ = right
    t
  }
  
  def apply(f: T => T) = {
    val t :: ts = right
    Zipper(left, f(t) :: ts)
  }
  
  def replace(t: T) = {
    val _ :: ts = right
    Zipper(left, t :: ts)
  }
}

object Zipper {
  def apply[T](right: List[T]): Zipper[T] = Zipper(List(), right)
  def apply[T](): Zipper[T] = Zipper(List(), List())
}