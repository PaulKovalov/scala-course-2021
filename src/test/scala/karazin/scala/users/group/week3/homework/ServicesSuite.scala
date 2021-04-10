package karazin.scala.users.group.week3.homework

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import services._
import model._

import java.util.UUID
import scala.util.{Failure, Success}

/*
  Write test for all service in karazin.scala.users.group.week3.homework.services

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */

class ServicesSuite extends munit.FunSuite:

  test("getUserProfile returns success future") {
    getUserProfile(ExecutionContext.global) onComplete {
      case Success(_) => assert(true)
      case Failure(error) => fail(error.getMessage)
    }
  }

  test("test getPosts returns success future") {
    val userId = UUID.randomUUID
    getPosts(userId = userId)(ExecutionContext.global) map { posts =>
      posts foreach {
        case Post(`userId`, _) => assert(true)
        case Post(differentUserId, _) => fail(s"Expected to get $userId, got $differentUserId instead")
      }
    } recover {
      case error => fail(error.getMessage)
    }
  }

  test("test getComments returns success future") {
    val postId = UUID.randomUUID
    getComments(postId = postId)(ExecutionContext.global) map { comments =>
      comments foreach {
        case Comment(_, `postId`) => assert(true)
        case Comment(_, differentPostId) => fail(s"Expected to get $postId, got $differentPostId instead")
      }
    } recover {
      case error => fail(error.getMessage)
    }
  }

  test("test getLikes returns success future") {
    val postId = UUID.randomUUID
    getLikes(postId = postId)(ExecutionContext.global) map { likes =>
      likes foreach {
        case Like(_, `postId`) => assert(true)
        case Like(_, differentPostId) => fail(s"Expected to get $postId, got $differentPostId instead")
      }
    } recover {
      case error => fail(error.getMessage)
    }
  }

  test("test getShares returns success future") {
    val postId = UUID.randomUUID
    getShares(postId = postId)(ExecutionContext.global) map { shares =>
      shares foreach {
        case Share(_, `postId`) => assert(true)
        case Share(_, differentPostId) => fail(s"Expected to get $postId, got $differentPostId instead")
      }
    } recover {
      case error => fail(error.getMessage)
    }
  }

  