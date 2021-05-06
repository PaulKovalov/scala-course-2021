package karazin.scala.users.group.week8.homework
import karazin.scala.users.group.week8.homework.monads.{given, _}
import karazin.scala.users.group.week2.and.three.quarters.homework.adt._
import munit.ScalaCheckSuite
import org.scalacheck.Prop._

class MonadSuite extends ScalaCheckSuite:
  property("test listMonad works") {
    forAll { (l: List[Int]) =>
      def mappingFunction: Int => List[Int] = x => x * x :: Nil
      val monadsFlatMappedList = listMonad.flatMap(l)(mappingFunction)
      val realFlatMappedList = l.flatMap(mappingFunction)
      monadsFlatMappedList == realFlatMappedList
    }
  }

  property("test SimpleValueMonad works") {
    forAll { (i: Int) =>
      def mappingFunction: Int => ErrorOr[Int] = x => ErrorOr.apply(x * x)
      val monadsFlatMappedValue = SimpleValueMonad.flatMap(ErrorOr.apply(i))(mappingFunction)
      val errorOrFlatMappedValue = ErrorOr.apply(i).flatMap(mappingFunction)
      monadsFlatMappedValue == errorOrFlatMappedValue
    }
  }

  property("test ListValueMonad works") {
    forAll { (l: List[Int]) =>
      def mappingFunction: Int => List[String] = x => (x * x).toString :: Nil
      val monadsFlatMappedValue = ListValueMonad.flatMap(ErrorOr.apply(l))(ErrorOr[List[String]].apply compose mappingFunction)
      val realFlatMappedValue = l.flatMap(mappingFunction)
      monadsFlatMappedValue == ErrorOr.apply(realFlatMappedValue)
    }
  }
