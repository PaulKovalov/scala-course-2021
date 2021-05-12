package karazin.scala.users.group.week9.homework

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import concurrent.ExecutionContext.Implicits.global

import cats.Functor

object functors extends App:

  /*
    Implement combined functor which and calculates   with
      * sum of elements
      * product of
    over Future[List[Try[Option[Int]]]]
   */
  type LTOI = List[Try[Option[Int]]]

  def sum(v: Future[LTOI]): Future[Int] = {

    def addToList(elToAdd: Try[Option[Int]], l: LTOI): LTOI =
      elToAdd match
        case Success(Some(v)) => Functor[List].compose[Try].compose[Option].map(l)(x => x + v)
        case _ => throw Exception("Ba bah")

    def sumList(l: LTOI, initialList: LTOI): LTOI =
      l match {
        case Nil => initialList
        case head :: tail => {
          val addedList = sumList(tail, initialList)
          addToList(head, addedList)
        }
      }
    v map { l =>
      sumList(l, l).lift(0) match {
        case Some(Success(Some(v))) => {
          l.lift(0) match {
            case Some(Success(Some(v2))) => v - v2
            case _ => 0
          }
        }
        case _ => 0
      }
    }
  }
  // for each element in the list, add it to all remaining elements in the list

//    Functor[Future].compose[List].compose[Try].compose[Option].map(v) { (x: Int) =>
//      r + x
//    }

//  def product(v: Future[List[Try[Option[Int]]]]) = ???
  val l = Future.successful(Try(Some(1)) :: Try(Some(2)) :: Try(Some(3)) :: Try(Some(4)) :: Nil)
  sum(l) foreach { v =>
    println(v)
  }

end functors