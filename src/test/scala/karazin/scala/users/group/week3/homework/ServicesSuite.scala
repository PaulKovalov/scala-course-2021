package karazin.scala.users.group.week3.homework

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import services._
import model._

import java.util.UUID
import karazin.scala.users.group.week3.homework.TestUtils._

/*
  Write test for all service in karazin.scala.users.group.week3.homework.services

  Review:
    • https://scalameta.org/munit/docs/tests.html
    • https://scalameta.org/munit/docs/assertions.html
 */

class ServicesSuite extends munit.FunSuite:

  test("getUserProfile returns success future") {
    Future {
      val profileFuture = getUserProfile(ExecutionContext.global)
      profileFuture map { profile => assertNotNull(profile)}
    }
  }

  test("test getPosts returns success future") {
    Future {
      val userId = UUID.randomUUID()
      val postsFuture = getPosts(userId=userId)(ExecutionContext.global)
      postsFuture map { posts =>
        posts foreach(post => {
          post match {
            case Post(userId, _) => assert(true)
            case _ => fail("Actual userId was different from the expected") 
          }
        })
      }
    }
  }
  
  test("test getComments returns success future") {
    Future {
      val postId = UUID.randomUUID()
      val commentsFuture = getComments(postId=postId)(ExecutionContext.global)
      commentsFuture map { comments =>
        comments foreach(comment => {
          comment match {
            case Comment(_, postId) => assert(true)
            case _ => fail("Actual postId was different from the expected")  
          }
        })
      }
    }
  }
  
  test("test getLikes returns success future") {
    Future {
      val postId = UUID.randomUUID()
      val likesFuture = getLikes(postId=postId)(ExecutionContext.global)
      likesFuture map { likes =>
        likes foreach(like => {
          like match {
            case Like(_, postId) => assert(true)
            case _ => fail("Actual postId was different from the expected")
          }
        })
      }
    }
  }

  test("test getShares returns success future") {
    Future {
      val postId = UUID.randomUUID()
      val sharesFuture = getShares(postId=postId)(ExecutionContext.global)
      sharesFuture map { shares =>
        shares foreach(share => {
          share match {
            case Share(_, postId) => assert(true)
            case _ => fail("Actual postId was different from the expected")
          }
        })
      }
    }
  }

  