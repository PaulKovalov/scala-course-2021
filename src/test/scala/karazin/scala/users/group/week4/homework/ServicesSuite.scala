package karazin.scala.users.group.week4.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import karazin.scala.users.group.week4.homework.model._
import karazin.scala.users.group.week4.homework.services._

import java.util.UUID
import java.util.concurrent.Executors
import scala.util.{Failure, Success}

/*
  Write test for all service in karazin.scala.users.group.week4.homework.services
  Make sure you control custom execution contexts in tests using `before` and `after` logic

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/fixtures.html#ad-hoc-test-local-fixtures
 */

class ServicesSuite extends munit.FunSuite:
  var executionContext: ExecutionContextExecutorService = null

  override def beforeEach(context: BeforeEach): Unit =
    executionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(2))

  override def afterEach(context: AfterEach): Unit =
    executionContext.shutdown

  test("test getUserProfile returns UserProfile") {
    getUserProfile() onComplete {
      case Success(_) => assert(true)
      case Failure(error) => fail(error.getMessage)
    }
  }

  test("test getPosts returns success future") {
    val userId = UUID.randomUUID()
    getPosts(userId) map { posts =>
      posts foreach {
        case Post(`userId`, _) => assert(true)
        case Post(differentUserId, _) => fail(s"Actual userId was $differentUserId but expected $userId")
      }
    } recover {
      case error => fail(error.getMessage)
    }
  }

  test("test getComments returns success future") {
    val postId = UUID.randomUUID()
    getComments(postId) map { comments =>
      comments foreach {
        case Comment(_, `postId`) => assert(true)
        case Comment(_, differentPostId) => fail(s"Actual postId was $differentPostId but expected $postId")
      }
    } recover {
      case error => fail(error.getMessage)
    }
  }

  test("test getLikes returns success future") {
    val postId = UUID.randomUUID()
    getLikes(postId) map { likes =>
      likes foreach {
        case Like(_, `postId`) => assert(true)
        case Like(_, differentPostId) => fail(s"Actual postId was $differentPostId but expected $postId")
      }
    } recover {
      case error => fail(error.getMessage)
    }
  }

  test("test getShares returns success future") {
    val postId = UUID.randomUUID()
    getShares(postId) map { shares =>
      shares foreach {
        case Share(_, `postId`) => assert(true)
        case Share(_, differentPostId) => fail(s"Actual postId was $differentPostId but expected $postId")
      }
    } recover {
      case error => fail(error.getMessage)
    }
  }

  