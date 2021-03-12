package karazin.scala.users.group.week1.homework

import scala.util.control.NonFatal

/* 
  Custom implementation of Option (Maybe monad in Haskell)
  Implemented via Scala 3 way for Algebraic Data Types (ADT)
  
  Resources:
  * https://en.wikipedia.org/wiki/Algebraic_data_type
  * https://docs.scala-lang.org/scala3/book/types-adts-gadts.html
*/

object adt:
  
  enum ErrorOr[+V]:
    // sealed classes created here
    // sealed means that subclasses are only possible to define in the same file
    case Value(v: V) extends ErrorOr[V]

    case Error(v: Throwable) extends ErrorOr[V]

    /* 
      The method is used for defining execution pipelines
      Provide a type parameter, an argument and a result type
      
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
    */ 
    def flatMap[Q](f: V => ErrorOr[Q]): ErrorOr[Q] =
      this match
        case ErrorOr.Value(v) =>
          try
            f(v)
          catch
            case NonFatal(e) => ErrorOr.Error(e)
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
          try
            ErrorOr.Value(f(v))
          catch
            case NonFatal(e) => ErrorOr.Error(e)
        case ErrorOr.Error(e) => ErrorOr.Error(e)
      
  // Companion object to define constructor
  object ErrorOr:
    /* 
      Provide a type parameter, an argument and a result type
      
      Make sure that in case of failing the method with exception
      no exception is thrown but the case for an error is returned
    */
    def apply[V](v: V): ErrorOr[V] =
      if v == null then
        ErrorOr.Error(new NullPointerException("Can not construct an ErrorOr instance from null"))
      else
        ErrorOr.Value(v)