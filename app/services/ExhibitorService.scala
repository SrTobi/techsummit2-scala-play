package services

import data.{ExhibitorId, ExhibitorInfo}
import javax.inject.Singleton

trait ExhibitorService {
  def resolve(id: String): Option[ExhibitorId]
  def info(id: ExhibitorId): ExhibitorInfo
  def exhibitors: Seq[ExhibitorInfo]
  def exhibitorIds: Set[ExhibitorId]
}


import ExhibitorServiceImpl._

@Singleton
class ExhibitorServiceImpl extends ExhibitorService {
  private val validExhibitorIds =
    staticExhibitors.map(info => info.id.internal -> info).toMap

  override def resolve(id: String): Option[ExhibitorId] =
    validExhibitorIds.get(id).map(_.id)

  override def info(id: ExhibitorId): ExhibitorInfo =
    validExhibitorIds(id.internal)

  override val exhibitors: Seq[ExhibitorInfo] = staticExhibitors

  override val exhibitorIds: Set[ExhibitorId] =
    exhibitors.map(_.id).toSet
}

object ExhibitorServiceImpl {
  private[this] def id(id: String): ExhibitorId = new ExhibitorId(id)

  private val staticExhibitors = Seq(
    ExhibitorInfo(
      id = id("dummy1"),
      name = "Dummy Exhibitor",
      location = "Halle 3, Stand 124",
      icon = "https://www.w3schools.com/favicon.ico"
    ),

    ExhibitorInfo(
      id = id("dummy2"),
      name = "Dummy 2 Exhibitor",
      location = "Halle 3, Stand 9",
      icon = "https://www.w3schools.com/favicon.ico"
    ),

    ExhibitorInfo(
      id = id("dummy3"),
      name = "Dummy 3 Exhibitor",
      location = "Halle 3, Stand 19",
      icon = "https://www.w3schools.com/favicon.ico"
    )
  )
}

