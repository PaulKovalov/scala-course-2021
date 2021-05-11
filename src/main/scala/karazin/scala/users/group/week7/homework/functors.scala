package karazin.scala.users.group.week7.homework

object functors:
  
  type MapString[T] = Map[String, T]

  trait Functor[F[_]]:
    def map[A, B](x: F[A])(f: A => B): F[B]
  
  given MapFunctor: Functor[MapString] with
    def map[A, B](x: MapString[A])(f: A => B): MapString[B] =
      x.keysIterator.foldLeft(Map())((resultingMap: MapString[B], key: String) => {
        resultingMap + (key -> f(x(key)))
      })
  
end functors