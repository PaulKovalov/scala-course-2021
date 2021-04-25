package karazin.scala.users.group.week7.homework

import karazin.scala.users.group.week7.homework.functors.{given, _}

/*
  Write test for functors

  Make sure that the following cases are tested:
    • Map[String, Int] works
    • Map[String, Boolean] works
    • Map[String, String] works
    • Map[String, List[Int]], Map[String, List[Boolean]], Map[String, List[String]] works

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/assertions.html#compileerrors
    • https://scalameta.org/munit/docs/integrations/scalacheck.html

 */

import munit.ScalaCheckSuite
import org.scalacheck.Prop._

class FunctorsSuite extends ScalaCheckSuite:
  
  property("functor for Map[String, Int]") {
    forAll { (map: Map[String, Int], f: Int ⇒ Int) =>
      MapFunctor.map(map)(f) == map.view.mapValues(f).toMap
    }
  }

  property("functor for Map[String, Boolean]") {
    forAll { (map: Map[String, Boolean], f: Boolean ⇒ Boolean) =>
      MapFunctor.map(map)(f) == map.view.mapValues(f).toMap
    }
  }

  property("functor for Map[String, String]") {
    forAll { (map: Map[String, String], f: String ⇒ String) =>
      MapFunctor.map(map)(f) == map.view.mapValues(f).toMap
    }
  }

  property("functor for Map[String, List[Int]") {
    forAll { (map: Map[String, List[Int]], f: List[Int] ⇒ List[Int]) =>
      MapFunctor.map(map)(f) == map.view.mapValues(f).toMap
    }
  }

  property("functor for Map[String, List[String]") {
    forAll { (map: Map[String, List[String]], f: List[String] ⇒ List[String]) =>
      MapFunctor.map(map)(f) == map.view.mapValues(f).toMap
    }
  }

  property("functor for Map[String, List[Boolean]") {
    forAll { (map: Map[String, List[Boolean]], f: List[Boolean] ⇒ List[Boolean]) =>
      MapFunctor.map(map)(f) == map.view.mapValues(f).toMap
    }
  }

  // mapping to the type different from the initial
  property("functor for Map[String, Int]") {
    forAll { (map: Map[String, Int], f: Int ⇒ String) =>
      MapFunctor.map(map)(f) == map.view.mapValues(f).toMap
    }
  }

  // check that Functor for Map[Int, _] raises compile error
  test("functor only works for Map[String, _], test with Int") {
    compileErrors("val m = MapFunctor.map(Map(1 -> 2))((i: Int) => i.toString)")
  }

  // check that Functor for Map[Boolean, _] raises compile error
  test("functor only works for Map[String, _], test with Boolean") {
    compileErrors("val m = MapFunctor.map(Map(true -> 1))((i: Int) => i.toString)")
  }