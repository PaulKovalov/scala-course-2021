package karazin.scala.users.group.week3.homework

import java.util.UUID

import karazin.scala.users.group.week3.homework.model._
import karazin.scala.users.group.week3.homework.services._

import scala.util.{Success, Failure}
import scala.concurrent._
import scala.concurrent.ExecutionContext.Implicits.global

object program extends App:

  def getPostView(post: Post): Future[PostView] = 
    
    val getCommentsService  = getComments(post.postId)(ExecutionContext.global)
    val getLikesService     = getLikes(post.postId)(ExecutionContext.global)
    val getSharesService    = getShares(post.postId)(ExecutionContext.global)
    
    for
      comments ← getCommentsService
      likes ← getLikesService
      shares ← getSharesService
    yield PostView(post, comments, likes, shares)