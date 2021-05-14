package karazin.scala.users.group.week12.topic

import java.util.UUID

object model:

  trait Database
  trait HttpServer
  case class AppContext(database: Database, httpServer: HttpServer)

  case class DatabaseConfig(url: String, user: String, password: String)
  case class HttpServerConfig(addr: String, port: Int)

  case class AppConfig(databaseConfig: DatabaseConfig, httpServerConfig: HttpServerConfig)








