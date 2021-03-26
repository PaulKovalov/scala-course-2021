package karazin.scala.users.group.week2.and.three.quarters.homework

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

/* 
  Custom implementation of Option (Maybe monad in Haskell)
  Implemented via Scala 3 way for Algebraic Data Types (ADT)
  
  Optional task:
  In addition to 
  ```
    ErrorOr.fromTry(
      Try {
        bla-bla-bla
      }
    )
  ```
  Make 
  ```
    Try {
      bla-bla-bla
    }.toErrorOr
  ```
  also available.
  NB: Don't forget about type parameters.

  
  Resources:
  * https://en.wikipedia.org/wiki/Algebraic_data_type
  * https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
*/

object adt:
  
  enum ErrorOr[+V]:
    
    case Value(v: V)
    
    case Error(v: Throwable)
  
    /* 
      The method is used for defining execution pipelines
      Provide a type parameter, an argument and a result type
      
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
    */ 
    def flatMap[Q](f: V => ErrorOr[Q]): ErrorOr[Q] = 
      this match
        case ErrorOr.Value(v) =>
          // here this value has type ErrorOr[ErrorOr[V]] so I need to unpack it
          ErrorOr.fromTry(Try(f(v))) match {
            case ErrorOr.Value(v) => v
            case ErrorOr.Error(error) => ErrorOr.Error(error)
          }
        case ErrorOr.Error(e) => ErrorOr.Error(e)
    /* 
      The method is used for changing the internal object
      Provide a type parameter, an argument and a result type
      
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
     */
    def map[Q](f: V => Q): ErrorOr[Q] =
      this match
        case ErrorOr.Value(v) =>
          ErrorOr.fromTry(Try(f(v)))
        case ErrorOr.Error(e) => ErrorOr.Error(e)
  
    /* 
      The method is used for filtering
      Provide a type parameter, an argument and a result type
      
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
     */
    def withFilter(p: V => Boolean): ErrorOr[V] = ???
  
    /* 
      The method is used for getting rid of internal box
      Provide a type parameter, an argument and a result type
    */
    def flatten[U](implicit ev: V <:< ErrorOr[U]): ErrorOr[U] =
      this match
        case ErrorOr.Value(v) => ev(v)
        case ErrorOr.Error(v)    => ErrorOr.Error(v)
    
    /* 
      The method is used for applying side effects without returning any result
      Provide a type parameter, an argument and a result type
    */
    def foreach[U](f: V => U): Unit =
      this match
        case ErrorOr.Error(v)    => ()
        case ErrorOr.Value(v)    => f(v)
      
  // Companion object to define constructor
  object ErrorOr:
    /* 
      Provide a type parameter, an argument and a result type
      
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
    */
    def apply[V](v: V)(implicit ev: V <:< Throwable = null) : ErrorOr[V] =
      Option(ev).fold[ErrorOr[V]](ErrorOr.Value(v))(_ => ErrorOr.Error(v))
    
    /*
      The method is used for creating `ErrorOr` instance from `Try`
      Provide a type parameter, an argument and a result type
    */
    def fromTry[V](t: Try[V]): ErrorOr[V] =
      t match
        case Success(v) => ErrorOr.Value(v)
        case Failure(throwable) => ErrorOr.Error(throwable)
      
  