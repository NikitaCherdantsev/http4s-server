package project

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import cats.effect._
import io.circe._
import io.circe.generic.semiauto.deriveEncoder
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._

object JsonService {

  implicit val jsonsEncoder: Encoder[JsonInfo] = deriveEncoder[JsonInfo]

  val jsonService: HttpRoutes[IO] =
    HttpRoutes.of[IO] {
      case GET -> Root / "json" / msg =>
        for {
          resp <- Ok(JsonInfo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")), msg).asJson)
        } yield resp
    }

}

final case class JsonInfo(time: String, message: String)
