package karazin.scala.users.group.week6.homework

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
    
    extension (s: String)
      def toJsonString: String = encode(s)

  given JsonIntEncoder: JsonEncoder[Int] with
    def encode(v: Int): String = v.toString
    
    extension (i: Int)
      def toJsonString: String = encode(i)

  given JsonBooleanEncoder: JsonEncoder[Boolean] with
    def encode(v: Boolean): String =
      v match
        case true  => "true"
        case false => "false"
    
    extension (b: Boolean)
      def toJsonString: String = encode(b)

  given JsonListEncoder[T](using encoder: JsonEncoder[T]): JsonEncoder[List[T]] with
    def encode(l: List[T]): String =
      l match
        case Nil => "[]"
        case _ => l.foldLeft("[")
          ((result: String, v: T) => {result + encoder.encode(v) + ","}).dropRight(1) + "]"
    
    extension(l: List[T])
        def toJsonString: String = encode(l)

  given JsonMapEncoder[T](using tEncoder: JsonEncoder[T]): JsonEncoder[Map[String, T]] with
    def encode (m: Map[String, T]): String =
      m.foldLeft[String]("{")((result: String, entry: (String, T)) => {
        result + JsonStringEncoder.encode(entry._1) + ":" + tEncoder.encode(entry._2) + ","
      }).dropRight(1) + "}"

    extension (m: Map[String, T])
      def toJsonString: String = encode(m)
