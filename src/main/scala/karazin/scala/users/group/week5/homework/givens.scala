package karazin.scala.users.group.week5.homework

object givens:
  
  /* 
    The trait is used for converting instances to a json string representation
    Provide a type parameter(s) for the trait and the method 
    and argument(s) and a result type
  */
  
  trait JsonEncoder[T]:
    def encode(value: T): String
  
  given JsonStringEncoder: JsonEncoder[String] with
    def encode(v: String): String = "\"" + v + "\""
  
  given JsonIntEncoder: JsonEncoder[Int] with
    def encode(v: Int): String = v.toString
  
  given JsonBooleanEncoder: JsonEncoder[Boolean] with
    def encode(v: Boolean): String =
      v match
        case true  => "true"
        case false => "false"
  
  given JsonListEncoder[T](using encoder: JsonEncoder[T]): JsonEncoder[List[T]] with
    def encode(l: List[T]): String =
      l match
        case Nil => "[]"
        case _ => l.foldLeft("[")
          ((result: String, v: T) => {result + encoder.encode(v) + ","}).dropRight(1) + "]"
      