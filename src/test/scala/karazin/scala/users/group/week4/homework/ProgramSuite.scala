package karazin.scala.users.group.week4.homework

import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
import karazin.scala.users.group.week4.homework.program._
import karazin.scala.users.group.week4.homework.model._

import java.util.UUID
import scala.util.{Failure, Success}

/*
  Write test for all programs in karazin.scala.users.group.week4.homework.program
  Make sure you control custom execution contexts in tests using `before` and `after` logic

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
    • https://scalameta.org/munit/docs/fixtures.html#ad-hoc-test-local-fixtures
 */

class ProgramSuite extends munit.FunSuite:
  var executionContext: ExecutionContextExecutorService = null

  override def beforeEach(context: BeforeEach): Unit =
    executionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(2))

  override def afterEach(context: AfterEach): Unit =
    executionContext.shutdown

  test("test getPostView works") {
    Future {
      val postId = UUID.randomUUID()
      val post: Post = Post(UUID.randomUUID(), postId)
      val postView = getPostView(post)
      postView match {
        case PostView(Post(_, `postId`), _, _, _) => assert(true)
        case PostView(Post(_, differentPostId), _, _, _) => fail(s"Actual post id was $differentPostId, expected $postId")  
      }
    }
  }
  
  test("test getPostsViews works") {
    Future {
      val listOfPostsFuture = getPostsViews()
      listOfPostsFuture onComplete {
        case Success(listOfPosts) => assert(true)
        case Failure(exception)   => fail(exception.getMessage)
      }
    }
  }


