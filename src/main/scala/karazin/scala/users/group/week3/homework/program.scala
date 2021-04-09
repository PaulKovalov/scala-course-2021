package karazin.scala.users.group.week3.homework

import java.util.UUID

import karazin.scala.users.group.week3.homework.model._
import karazin.scala.users.group.week3.homework.services._

import scala.util.{Success, Failure}
import scala.concurrent._

object program extends App:

  def getPostView(post: Post): Future[PostView] =
    implicit val ec: ExecutionContext = ExecutionContext.global

    val getCommentsService  = getComments(post.postId)(ec)
    val getLikesService     = getLikes(post.postId)(ec)
    val getSharesService    = getShares(post.postId)(ec)
    
    for
      comments ← getCommentsService
      likes ← getLikesService
      shares ← getSharesService
    yield PostView(post, comments, likes, shares)