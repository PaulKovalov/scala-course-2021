package karazin.scala.users.group.week8.homework

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

    /** The unit value for a monad */
    def pure[A](x: A): F[A]

    extension [A, B](x: F[A])
    /** The fundamental composition operation */
      def flatMap(f: A => F[B]): F[B]

  end Monad

end monads
