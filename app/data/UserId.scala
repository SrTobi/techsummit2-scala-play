package data

import play.api.libs.json.{Format, Json}

class UserId(private val internal: String) {
  override def hashCode(): Int = internal.hashCode
  override def equals(o: Any): Boolean = o match {
    case o: UserId => o.internal == internal
    case _ => false
  }
}

object UserId {
  def apply(id: String): UserId = new UserId(id)
  implicit val exhibitorInfoFormat: Format[UserId] =
    Json.valueFormat[UserId]
}