package karazin.scala.users.group.week8.homework
import scala.language.postfixOps
/*
  Provide implementation for Monad trait and provide
  a monad instance for ErrorOr trait from HW2¾

  Hint:
    * treat Monad as a typeclass
    * provide 3 instances:
      * Monad[List]
      * Moand[ErrorOr] for a simple value
      * Monad[ErrorOr] for a F[_], to be able to use List
 */

import karazin.scala.users.group.week2.and.three.quarters.homework.adt._

object monads:

  trait Monad[F[_]]:
    def apply[A](x: A): F[A]

    extension [A, B](x: F[A])
      def flatMap(f: A => F[B]): F[B]


  given listMonad: Monad[List] with
    def apply[A](x: A): List[A] =
      List(x)

    extension [A, B](x: List[A])
      def flatMap(f: A => List[B]): List[B] =
        x.flatMap(f)

  type EV = [X] =>> ErrorOr[X]
  type EL = [X] =>> ErrorOr[List[X]]

  given SimpleValueMonad: Monad[EV] with
    def apply[A](x: A): ErrorOr[A] =
      ErrorOr.apply(x)

    extension [A, B](x: ErrorOr[A])
      def flatMap(f: A => ErrorOr[B]): ErrorOr[B] =
        x.flatMap(f)

  given ListValueMonad: Monad[EL] with
    def apply[A](x: A): ErrorOr[List[A]] =
      ErrorOr.apply(x :: Nil)

    extension [A, B](x: ErrorOr[List[A]])
      def flatMap(f: A => ErrorOr[List[B]]): ErrorOr[List[B]] =
        x map { (listOfA: List[A]) =>
          listOfA flatMap { (a: A) =>
            f(a) match {
              case ErrorOr.Value(listOfB) => listOfB
              case _ => None
            }
          }
        }

end monads
