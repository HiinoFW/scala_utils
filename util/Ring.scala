package util

import exceptions.EmptyRingException

case class Ring[T](val left: List[T], val right: List[T]) {
  if (right == List()) {
    if (left == List())
      throw EmptyRingException
    else reset
  }
     
  def shift = right match {
    case u :: List() => reset
    case _ => Ring(left :+ right.head, right.tail)
  }
  
  def unshift = left match {
    case List() => Ring(right.init, right.last +: List())
    case _ => Ring(left.init, left.last +: right)
  }
  
  private def reset = Ring(left ::: right)
  
  def toList = right ::: left
  
  def insert(t: T) = Ring(left, t :: right)
  
  def get = {
    right.head
  }
  
  def change(f: T => T) = {
    val t :: ts = right
    Ring(left, f(t) :: ts)
  }
  
  def map[U](f: T => U) = Ring(left map (f(_)), right map (f(_)))
  
  def replace(t: T) = {
    Ring(left, t :: right.tail)
  }
  
  def remove = Ring(left, right.tail)
}

object Ring {
  def apply[T](right: List[T]): Ring[T] = Ring(List(), right)
}