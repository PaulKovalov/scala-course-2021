package karazin.scala.users.group.week3.homework

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import program._
import model._

import java.util.UUID
import karazin.scala.users.group.week3.homework.TestUtils._

/*
  Write test for all programs in karazin.scala.users.group.week3.homework.program

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */

class ProgramSuite extends munit.FunSuite:
  
  test("program works") {
    Future {
      val userId = UUID.randomUUID()
      val postId = UUID.randomUUID()
      val postViewFuture = getPostView(Post(userId = userId, postId = postId))
      postViewFuture map { postView =>
        postView match {
          case PostView(Post(`userId`, `postId`),_,_,_) => assert(true)
          case _ => fail(s"Values of userId or postId were not equal to ${userId} and ${postId}")
        } 
      }
    }
  }
