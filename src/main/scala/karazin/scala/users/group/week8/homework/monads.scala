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

//  trait Monad[F[_]]:
//    def apply[A](x: A): F[A]
//    
//    extension [A, B](x: F[A])
//      def flatMap(f: A => F[B]): F[B]
//
//  end Monad
//  
//  given listMonad[A]: Monad[List] with
//    def apply[A](x: A): List[A] =
//      List(x)
//
//    extension [A, B](x: List[A])
//      def flatMap(f: A => List[B]): List[B] =
//        x.flatMap(f)
//
//
//  given Monad[ErrorOr] with
//    def apply[A](x: A): ErrorOr[A] =
//      ErrorOr.apply(x)
//    extension [A, B](x: ErrorOr[A])  
//      def flatMap(f: A => ErrorOr[B]): ErrorOr[B] =
//        x.flatMap(f)
//  
//  given Monad[ErrorOr[F[_]]] with
//    def apply[A](x: A): ErrorOr[F[A]] = ???
  trait Monad[F[_]]:

    /** The unit value for a monad */
    def pure[A](x: A): F[A]

    extension [A, B](x: F[A])
    /** The fundamental composition operation */
      def flatMap(f: A => F[B]): F[B]

  end Monad

  given listMonad[A]: Monad[List] with
    def pure[A](x: A): List[A] =
      List(x)
    extension [A, B](xs: List[A])
      def flatMap(f: A => List[B]): List[B] =
        xs.flatMap(f) // rely on the existing `flatMap` method of `List`


end monads
