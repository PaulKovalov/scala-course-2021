package karazin.scala.users.group.week9.homework

import scala.concurrent.Future
import scala.util.{Failure, Success, Try}
import scala.concurrent.ExecutionContext.Implicits.global

import cats.Functor

object functors:

  /*
    Implement combined functor which and calculates   with
      * sum of elements
      * product of
    over Future[List[Try[Option[Int]]]]
   */
  type LTOI = List[Try[Option[Int]]]

  private def listOp(v: Future[LTOI], op: (Int, Int) => Int, reduce: (Int, Int) => Int, neutral: Int): Future[Int] = {

    def applyOp(elToAdd: Try[Option[Int]], l: LTOI): LTOI =
      elToAdd match
        case Success(Some(v)) => Functor[List].compose[Try].compose[Option].map(l)(x => op(x, v))
        case _ => throw Exception("Future failed or Option had None")

    def foldList(l: LTOI, initialList: LTOI): LTOI =
      l match {
        case Nil => initialList
        case head :: tail => {
          val addedList = foldList(tail, initialList)
          applyOp(head, addedList)
        }
      }
    v map { l =>
      foldList(l, l).lift(0) match {
        case Some(Success(Some(v))) => {
          l.lift(0) match {
            case Some(Success(Some(v2))) => reduce(v, v2)
            case _ => neutral
          }
        }
        case _ => neutral
      }
    }
  }

  def sum(l: Future[LTOI]): Future[Int] =
    listOp(l, (x, y) => x + y, (x, y) => x - y, 0)

  def mul(l: Future[LTOI]): Future[Int] =
    listOp(l, (x, y) => x * y, (x, y) => {
      x / (y match {
        case 0 => 1
        case _ => y
      })}, 1)

end functors