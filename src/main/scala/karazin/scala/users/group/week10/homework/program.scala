package karazin.scala.users.group.week10.homework
import cats.Applicative
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object program:

  /*
    Provide a meaningful example of Applicative usage.
    Where would you try to use Applicative?
   */

  object applicatives:
    val getAccountABalance =
      Future.successful(Some(100.0))

    val getAccountBBalance =
      Future.successful(Some(200.0))

    def getTotalBalance(balance1: Future[Option[Double]], balance2: Future[Option[Double]]): Future[Option[Double]] =
      Applicative[Future].compose[Option].map2(balance1, balance2)(_ + _)

    getTotalBalance(getAccountABalance, getAccountBBalance)
  end applicatives

end program
