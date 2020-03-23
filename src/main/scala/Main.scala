package project

import cats.effect._
import cats.implicits._
import org.http4s.implicits._
import org.http4s.server.blaze._

object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(JsonService.jsonService.orNotFound)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
