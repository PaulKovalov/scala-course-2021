package karazin.scala.users.group.week12.topic

// Do not forget to import custom implementation
import cats.FlatMap
import cats.data.ReaderT
import cats.implicits._
import karazin.scala.users.group.week12.topic.model._
import karazin.scala.users.group.week12.topic.services._

import java.util.UUID

object program:

  object InitilizeAppContext:
    def fromAppConfig[F[_]: FlatMap]: ReaderT[F, AppConfig, AppContext] =
      for
        database    ← Database.fromDatabaseConfig[F].local[AppConfig](_.databaseConfig)
        httpServer  ← HttpServer.fromHttpServerConfig[F].local[AppConfig](_.httpServerConfig)
      yield AppContext(database, httpServer)

  object initialize:

    val appConfig = ???

    val appContext: Option[AppContext] = InitilizeAppContext.fromAppConfig[Option].apply(appConfig)

  end initialize


