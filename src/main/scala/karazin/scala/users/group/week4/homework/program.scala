/*

Task:
  • fix the code to make it compilable
  • use at least to execution contexts:
    • one for a `for comprehension`
    • at least one for `getComments`, `getLikes`, `getShares`
  • write tests
*/

package karazin.scala.users.group.week4.homework

import java.util.UUID
import scala.concurrent.{ExecutionContext, ExecutionContextExecutorService, Future}
// or I can declare implicit vals in methods that require the execution context
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Success
import scala.util.Failure
import karazin.scala.users.group.week4.homework.model._
import karazin.scala.users.group.week4.homework.services._

import java.util.concurrent.Executors


object program:
  
  def getPostsViews(): Future[List[PostView]] =
    for
      profile   <- getUserProfile()
      posts     <- getPosts(profile.userId)
      postsView = posts flatMap {
        post ⇒ getPostView(post) match {
          case PostView(post, comments, likes, shares) => Option(PostView(post, comments, likes, shares))
          case _ => None
        }
      }
    yield postsView
      

  def getPostView(post: Post): Future[PostView] =
    // some custom context
    val singleThreadPoolContext: ExecutionContextExecutorService =
      ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor)
    
    val getCommentsService  = getComments(post.postId) // uses implicit global execution context
    val getLikesService     = getLikes(post.postId)(using singleThreadPoolContext) // uses explicit context
    val getSharesService    = getShares(post.postId) // uses implicit global execution context
    
    for
      comments  ← getCommentsService
      likes     ← getLikesService
      shares    ← getSharesService
    yield PostView(post, comments, likes, shares)
