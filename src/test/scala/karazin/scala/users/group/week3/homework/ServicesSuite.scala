package karazin.scala.users.group.week3.homework

import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import services._

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
      val postsFuture = getPosts(userId=UUID.randomUUID())(ExecutionContext.global)
      postsFuture map { posts => 
        assertNotNull(posts)
        posts foreach(post => {
          assertNotNull(posts) 
        })
      }
    }
  }
  
  test("test getComments returns success future") {
    Future {
      val commentsFuture = getComments(postId = UUID.randomUUID())(ExecutionContext.global)
      commentsFuture map { comments =>
        assertNotNull(comments)
        comments foreach(comment => {
          assertNotNull(comment)
        })
      }
    }
  }
  
  test("test getLikes returns success future") {
    Future {
      val likesFuture = getLikes(postId = UUID.randomUUID())(ExecutionContext.global)
      likesFuture map { likes => 
        assertNotNull(likes)
        likes foreach(like => {
          assertNotNull(like)
        })
      }
    }
  }

  test("test getShares returns success future") {
    Future {
      val sharesFuture = getShares(postId = UUID.randomUUID())(ExecutionContext.global)
      sharesFuture map { shares =>
        assertNotNull(shares)
        shares foreach(like => {
          assertNotNull(like)
        })
      }
    }
  }

  