package data

import play.api.libs.json._
import play.api.libs.functional.syntax._

case class ExhibitorInfo(id: ExhibitorId,
                         name: String,
                         location: String,
                         icon: String,
                         description: String,
                         contact: String,
                         phone: String,
                         email: String,
                         filename: String)

object ExhibitorInfo {
  implicit val exhibitorInfoFormat: OFormat[ExhibitorInfo] = (
    (JsPath \ "exhibitorId").format[ExhibitorId] and
      (JsPath \ "name").format[String] and
      (JsPath \ "location").format[String] and
      (JsPath \ "icon").format[String] and
      (JsPath \ "description").format[String] and
      (JsPath \ "contact").format[String] and
      (JsPath \ "phone").format[String] and
      (JsPath \ "email").format[String] and
      (JsPath \ "filename").format[String]
    )(ExhibitorInfo.apply, unlift(ExhibitorInfo.unapply))
}