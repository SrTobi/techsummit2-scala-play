package data

import play.api.libs.json.{Format, Json}

class ExhibitorId(val internal: String) {
  override def hashCode(): Int = internal.hashCode
  override def equals(o: Any): Boolean = o match {
    case o: ExhibitorId => o.internal == internal
    case _ => false
  }
}

object ExhibitorId {
  implicit val exhibitorInfoFormat: Format[ExhibitorId] =
    Json.valueFormat[ExhibitorId]
}