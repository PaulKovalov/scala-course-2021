package karazin.scala.users.group.week9.homework

import karazin.scala.users.group.week9.homework.functors._

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps
import scala.util.{Random, Success, Try}
import cats.Functor
import cats.implicits._
import munit.ScalaCheckSuite

import org.scalacheck.Prop._
import org.scalacheck.Gen
import org.scalacheck.Arbitrary


class FunctorsSuite extends ScalaCheckSuite:

  property("test sum works") {
    forAll { (l: List[Int]) =>
      val list = l.map(el => Some(el)).map(el => Try(el))
      val v: FLTOI = Future.successful(list)
      val n = Random.between(1, 5)
      val summed = Await.result(sum(v, n), 2 seconds)
      summed.zip(list) foreach { tuple =>
        tuple match
          case (Success(Some(v1)), Success(Some(v2))) => assert(v1 == v2 + n)
          case _ => fail("Future failed or Option had None")
      }
    }
  }

  property("test mul works") {
    forAll(Gen.listOf(Arbitrary.arbitrary[Int].suchThat(x => x < 10 && x > -10))) { l =>
      val list = l.map(el => Some(el)).map(el => Try(el))
      val v: FLTOI = Future.successful(list)
      val n = Random.between(1, 5)
      val multiplied = Await.result(product(v, n), 2 seconds)
      multiplied.zip(list) foreach { tuple =>
        tuple match
          case (Success(Some(v1)), Success(Some(v2))) => assert(v1 == v2 * n)
          case _ => fail("Future failed or Option had None")
      }
    }
  }