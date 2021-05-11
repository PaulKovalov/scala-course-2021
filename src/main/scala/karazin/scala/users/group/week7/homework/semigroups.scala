package karazin.scala.users.group.week7.homework

object semigroups:

  trait Semigroup[T]:
    extension(l: T) 
      def combine(r: T): T
  
  given Semigroup[Int] with
    extension(l: Int)
      def combine(r: Int) = l + r
  
  given Semigroup[Boolean] with
    extension(l: Boolean)
      def combine(r: Boolean) = l & r
  
  given Semigroup[String] with
    extension(l: String)
      def combine(r: String) = l concat r
  
  given Semigroup[List[_]] with
    extension(l: List[_])
      def combine(r: List[_]) = l :: r :: Nil

  /*
  Merges two maps, in case same key appears in both of the maps,
  combines values of the same keys
   */
  given MapSemigroup[T](using semigroup: Semigroup[T]): Semigroup[Map[String, T]] with
    extension(l: Map[String, T])
      def combine(r: Map[String, T]) =
        r.keysIterator.foldLeft(l)((resultingMap: Map[String, T], key: String) => {
            val newVal = if l.contains(key) then l(key).combine(r(key)) else r(key)
            resultingMap + (key -> newVal)
        })

end semigroups
