package karazin.scala.users.group.week2.homework

// Do not forget to import custom implementation
import adt._
import model._
import services._

object program:
  
  /*
   Print all view for all user's posts if they exists
  */
  def printPostsViews(): ErrorOr[List[PostView]] =
    val postViews = getPostsViews()
    postViews foreach { listOfPosts =>
      listOfPosts foreach(post => println(post))
    }
    postViews


  /*
   Getting view for all user's posts if they exists
   If at least one ErrorOr has an error, whole method fails
  */
  def getPostsViewsAndErrorsAreExtremelyImportant(): ErrorOr[List[PostView]] =
    for
      profile   <- getUserProfile()
      posts     <- getPosts(profile.userId)
      postsView <- ErrorOr(posts map {
        post ⇒ getPostView(post) match {
          case ErrorOr.Value(PostView(post, comments, likes, shares)) => PostView(post, comments, likes, shares)
          case _ => throw new Exception("Get post view returned error") 
        }
      })
    yield postsView

  /*
    Getting view for all user's posts if they exists
    ErrorOr containing errors are filtered
   */
  def getPostsViews(): ErrorOr[List[PostView]] =
    for
      profile   <- getUserProfile()
      posts     <- getPosts(profile.userId)
      postsView <- ErrorOr(posts map {
        post ⇒ getPostView(post) match {
          case ErrorOr.Value(PostView(post, comments, likes, shares)) => PostView(post, comments, likes, shares)
          case _ => null
        }
      } filter (post => post != null)
      )
    yield postsView

  /* 
    Getting view for a particular user's post
    Provide an argument and a result type
  */
  def getPostView(post: Post): ErrorOr[PostView] =
    for
      comments <- getComments(post.postId)
      likes <- getLikes(post.postId)
      shares <- getShares(post.postId) 
    yield PostView(post, comments, likes, shares)

  /*
   Provide desugared version of the previous two methods
  */
  def getPostsViewsAndErrorsAreExtremelyImportantDesugared(): ErrorOr[List[PostView]] =
    getUserProfile() flatMap  { profile =>
      getPosts(profile.userId) map  {
        posts => posts map  {
          post => getComments(post.postId) flatMap {
            comments => getLikes(post.postId) flatMap {
              likes => getShares(post.postId) map {
                shares => PostView(post, comments, likes, shares)
              }
            }
          } match {
            case ErrorOr.Value(PostView(post, comments, likes, shares)) => PostView(post, comments, likes, shares)
            case _ => throw new Exception("Get post view returned error")
          }
        }
      }
    }


  