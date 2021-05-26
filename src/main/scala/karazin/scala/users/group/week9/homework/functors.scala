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
  type FLTOI = Future[List[Try[Option[Int]]]]

  def sum(l: FLTOI, n: Int): FLTOI =
    Functor[Future].compose[List].compose[Try].compose[Option].map(l)((x: Int) => x + n)

  def product(l: FLTOI, n: Int): FLTOI =
    Functor[Future].compose[List].compose[Try].compose[Option].map(l)((x: Int) => x * n)

end functors