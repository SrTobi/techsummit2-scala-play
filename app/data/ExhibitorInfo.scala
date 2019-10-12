package data

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class ExhibitorInfo(id: String, name: String, location: String, icon: String)

object ExhibitorInfo {
  implicit val exhibitorInfoFormat: Format[ExhibitorInfo] = (
    (JsPath \ "exhibitorId").format[String] and
      (JsPath \ "name").format[String] and
      (JsPath \ "location").format[String] and
      (JsPath \ "icon").format[String]
  )(ExhibitorInfo.apply, unlift(ExhibitorInfo.unapply))

  val dummyInfo: ExhibitorInfo = ExhibitorInfo(
    id = "dummy",
    name = "Dummy Exhibitor",
    location = "Halle 3, Stand 124",
    icon = "https://www.w3schools.com/favicon.ico"
  )
}