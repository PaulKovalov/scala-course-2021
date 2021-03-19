package karazin.scala.users.group.week3.homework

import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}
import scala.util.Success
import scala.util.Failure
import karazin.scala.users.group.week3.homework.model._


object services:
  
  def getUserProfile()(ec: ExecutionContext): Future[UserProfile] =
    Future {
      UserProfile(UUID.randomUUID())
    }(ec)
  
  def getPosts(userId: UUID)(ec: ExecutionContext): Future[List[Post]] =
    Future {
      Post(userId = UUID.randomUUID(), postId = UUID.randomUUID()) :: Nil
    }(ec)
  
  def getComments(postId: UUID)(ec: ExecutionContext): Future[List[Comment]] =
    Future {
      // Emulating time consumed operation
      Thread.sleep(5000)
      Comment(userId = UUID.randomUUID(), postId = UUID.randomUUID()) :: Nil
    }(ec)
  
  def getLikes(postId: UUID)(ec: ExecutionContext): Future[List[Like]] = 
    Future {
      // Emulating time consumed operation
      Thread.sleep(2000)
      Like(userId = UUID.randomUUID(), postId = UUID.randomUUID()) :: Nil
    }(ec)
  
  def getShares(postId: UUID)(ec: ExecutionContext): Future[List[Share]] = 
    Future {
      // Emulating time consumed operation
      Thread.sleep(500)
      Share(userId = UUID.randomUUID(), postId = UUID.randomUUID()) :: Nil
    }(ec)