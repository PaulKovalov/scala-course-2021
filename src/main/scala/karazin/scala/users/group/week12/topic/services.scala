package karazin.scala.users.group.week12.topic

import cats.FlatMap
import cats.data.ReaderT
import cats.implicits._

import java.util.UUID
import karazin.scala.users.group.week12.topic.model._


object services:

  object Database:
    def fromDatabaseConfig[F[_]: FlatMap]: ReaderT[F, DatabaseConfig, Database] = ???

  object HttpServer:
    def fromHttpServerConfig[F[_]: FlatMap]: ReaderT[F, HttpServerConfig, HttpServer] = ???



