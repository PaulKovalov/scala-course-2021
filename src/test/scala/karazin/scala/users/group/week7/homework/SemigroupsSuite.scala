package karazin.scala.users.group.week7.homework

import karazin.scala.users.group.week7.homework.semigroups.{given, _}
/*
  Write test for semigroups

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

class SemigroupsSuite extends ScalaCheckSuite:
  
  property("monoids for Map[String, Int]") {
    forAll { (map1: Map[String, Int], map2: Map[String, Int]) =>
      val commonKeys = map1.keySet.intersect(map2.keySet)
      val commonKeysCombinedMap = map1.keySet.intersect(map2.keySet).map(k => k->(map1(k) combine map2(k))).toMap
      val map1distinct = map1 removedAll commonKeys
      val map2distinct = map2 removedAll commonKeys
      (map1 combine map2) == map1distinct ++ map2distinct ++ commonKeysCombinedMap
    }
  }

  property("monoids for Map[String, Boolean]") {
    forAll { (map1: Map[String, Boolean], map2: Map[String, Boolean]) =>
      val commonKeys = map1.keySet.intersect(map2.keySet)
      val commonKeysCombinedMap = map1.keySet.intersect(map2.keySet).map(k => k->(map1(k) combine map2(k))).toMap
      val map1distinct = map1 removedAll commonKeys
      val map2distinct = map2 removedAll commonKeys
      (map1 combine map2) == map1distinct ++ map2distinct ++ commonKeysCombinedMap
    }
  }

  property("monoids for Map[String, String]") {
    forAll { (map1: Map[String, String], map2: Map[String, String]) =>
      val commonKeys = map1.keySet.intersect(map2.keySet)
      val commonKeysCombinedMap = map1.keySet.intersect(map2.keySet).map(k => k->(map1(k) combine map2(k))).toMap
      val map1distinct = map1 removedAll commonKeys
      val map2distinct = map2 removedAll commonKeys
      (map1 combine map2) == map1distinct ++ map2distinct ++ commonKeysCombinedMap
    }
  }

  property("monoids for Map[String, List[String]]") {
    forAll { (map1: Map[String, List[String]], map2: Map[String, List[String]]) =>
      val commonKeys = map1.keySet.intersect(map2.keySet)
      val commonKeysCombinedMap = map1.keySet.intersect(map2.keySet).map(k => k->(map1(k) combine map2(k))).toMap
      val map1distinct = map1 removedAll commonKeys
      val map2distinct = map2 removedAll commonKeys
      (map1 combine map2) == map1distinct ++ map2distinct ++ commonKeysCombinedMap
    }
  }

  property("monoids for Map[String, List[Int]]") {
    forAll { (map1: Map[String, List[Int]], map2: Map[String, List[Int]]) =>
      val commonKeys = map1.keySet.intersect(map2.keySet)
      val commonKeysCombinedMap = map1.keySet.intersect(map2.keySet).map(k => k->(map1(k) combine map2(k))).toMap
      val map1distinct = map1 removedAll commonKeys
      val map2distinct = map2 removedAll commonKeys
      (map1 combine map2) == map1distinct ++ map2distinct ++ commonKeysCombinedMap
    }
  }

  property("monoids for Map[String, List[Boolean]]") {
    forAll { (map1: Map[String, List[Boolean]], map2: Map[String, List[Boolean]]) =>
      val commonKeys = map1.keySet.intersect(map2.keySet)
      val commonKeysCombinedMap = map1.keySet.intersect(map2.keySet).map(k => k->(map1(k) combine map2(k))).toMap
      val map1distinct = map1 removedAll commonKeys
      val map2distinct = map2 removedAll commonKeys
      (map1 combine map2) == map1distinct ++ map2distinct ++ commonKeysCombinedMap
    }
  }
