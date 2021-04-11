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
  
  private def checkList[V](list: List[V])(f: V => Unit) =
    list foreach f

  test("test getPostView works") {
    val postId = UUID.randomUUID()
    getPostView(Post(UUID.randomUUID(), postId)) map {
      case PostView(Post(_, `postId`), comments, likes, shares) => {
        checkList[Comment](comments) {
          case Comment(_, `postId`)        => assert(true)
          case Comment(_, differentPostId) => fail(s"Comment object expected to have $postId, had $differentPostId instead")
        }
        checkList[Like](likes) {
          case Like(_, `postId`)        => assert(true)
          case Like(_, differentPostId) => fail(s"Like object expected to have $postId, had $differentPostId instead")
        }
        checkList[Share](shares) {
          case Share(_, `postId`)        => assert(true)
          case Share(_, differentPostId) => fail(s"Share object expected to have $postId, had $differentPostId instead")
        }
      }
      case PostView(Post(_, differentPostId), _, _, _) => fail(s"PostView had different Post. Actual post id was $differentPostId, expected $postId")
    }
  }

  test("test getPostsViews works") {
    getPostsViews() onComplete {
      case Success(_) => assert(true)
      case Failure(exception) => fail(exception.getMessage)
    }
  }


