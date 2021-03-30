package karazin.scala.users.group.week4.homework

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import karazin.scala.users.group.week4.homework.model._
import karazin.scala.users.group.week4.homework.services._

import java.util.UUID
import java.util.concurrent.Executors

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
    Future {
      val profileFuture = getUserProfile() // context is used implicitly here
      profileFuture map { profile =>
        profile match {
          case UserProfile(_) => assert(true)
          case null => fail("getUserProfile somehow returned null")
        }
      }
    }
  }

  test("test getPosts returns success future") {
    Future {
      val userId = UUID.randomUUID()
      val postsFuture = getPosts(userId=userId) // context is used implicitly here
      postsFuture map { posts =>
        posts foreach(post => {
          post match {
            case Post(`userId`, _) => assert(true)
            case Post(differentUserId, _) => fail(s"Actual userId was $differentUserId but expected $userId")
          }
        })
      }
    }
  }
  
  test("test getComments returns success future") {
    Future {
      val postId = UUID.randomUUID()
      val commentsFuture = getComments(postId=postId) // context is used implicitly here
      commentsFuture map { comments =>
        comments foreach(comment => {
          comment match {
            case Comment(_, `postId`) => assert(true)
            case Comment(_, differentPostId) => fail(s"Actual postId was $differentPostId but expected $postId")
          }
        })
      }
    }
  }

  test("test getLikes returns success future") {
    Future {
      val postId = UUID.randomUUID()
      val likesFuture = getLikes(postId=postId) // context is used implicitly here
      likesFuture map { likes =>
        likes foreach(like => {
          like match {
            case Like(_, `postId`) => assert(true)
            case Like(_, differentPostId) => fail(s"Actual postId was $differentPostId but expected $postId")
          }
        })
      }
    }
  } 
  
  test("test getShares returns success future") {
    Future {
      val postId = UUID.randomUUID()
      val sharesFuture = getShares(postId=postId) // context is used implicitly here
      sharesFuture map { shares =>
        shares foreach(share => {
          share match {
            case Share(_, `postId`) => assert(true)
            case Share(_, differentPostId) => fail(s"Actual postId was $differentPostId but expected $postId")
          }
        })
      }
    }
  }

  