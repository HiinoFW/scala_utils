package util.tool

import scala.annotation.tailrec
import util.collection.Zipper

object DeclineZipper {
	def apply[T](input: List[T]): List[Zipper[T]] = {
		@tailrec
		def declineZipper_rec(z: Zipper[T], acc: List[Zipper[T]]): List[Zipper[T]] = z match {
			case Zipper(_, List()) => z :: acc
			case default => declineZipper_rec(z.shift, z :: acc)
		}
		declineZipper_rec(Zipper(input), List())
	}
	
	def apply[T](z: Zipper[T]): Seq[Zipper[T]] = apply(z.getFull)
}