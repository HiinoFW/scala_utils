package util.collection

import util.exception.EmptyRingException

case class Ring[T](val left: List[T], val right: List[T]) {
  if (right == List()) {
    if (left == List())
      throw EmptyRingException
    else reset
  }
  
  def shift = right match {
    case u :: List() => reset
    case _ => Ring(right.head :: left, right.tail)
  }
  
  def unshift = left match {
    case List() => Ring(right.init.reverse, right.last :: List())
    case _ => Ring(left.tail, left.head :: right)
  }
  
  private def reset = Ring(left.reverse ::: right)
  
  def toList = right ::: left.reverse
  
  def insert(t: T) = Ring(left, t :: right)
  
  def get = {
    right.head
  }
  
  def change(f: T => T) = Ring(left, f(right.head) :: right.tail)
  
  def map[U](f: T => U) = Ring(left map (f(_)), right map (f(_)))
  
  def replace(t: T) = {
    Ring(left, t :: right.tail)
  }
  
  def remove = Ring(left, right.tail)
}

object Ring {
  def apply[T](right: List[T]): Ring[T] = Ring(List(), right)
}