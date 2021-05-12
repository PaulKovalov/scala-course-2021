package karazin.scala.users.group.week9.homework

import karazin.scala.users.group.week9.homework.functors._

import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.postfixOps
import scala.util.Try
import scala.util.Random

import cats.Functor
import cats.implicits._

import munit.ScalaCheckSuite
import org.scalacheck.Prop._
import org.scalacheck.Gen
import org.scalacheck.Arbitrary



class FunctorsSuite extends ScalaCheckSuite:

  property("test sum works") {
    forAll { (l: List[Int]) =>
      val v: Future[LTOI] = Future.successful(l.map(el => Some(el)).map(el => Try(el)))
      val sumValue = Await.result(sum(v), 2 seconds)
      l.sum == sumValue
    }
  }

  property("test mul works") {
    forAll(Gen.listOfN(Random.between(0, 10), Arbitrary.arbitrary[Int].suchThat(x => x < 10 && x > -10))) { l =>
      val v: Future[LTOI] = Future.successful(l.map(el => Some(el)).map(el => Try(el)))
      val mulValue = Await.result(mul(v), 2 seconds)
      mulValue == l.product
    }
  }