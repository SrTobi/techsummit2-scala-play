package services

import StorageServiceImpl._
import data.{ExhibitorId, UserId}
import javax.inject._


trait StorageService {
  def clearScans(): Unit
  def addScan(userId: UserId, exhibitorId: ExhibitorId): Unit
  def scans(userId: UserId): Seq[ExhibitorId]
  def recommendations(userId: UserId): Seq[(ExhibitorId, Double)]
}




@Singleton
class StorageServiceImpl @Inject() (exhibitorService: ExhibitorService) extends StorageService {
  private val monitor = new Object
  @volatile
  private var state = State.initial

  override def clearScans(): Unit = monitor.synchronized {
    state = State.initial
  }

  override def addScan(userId: UserId, exhibitorId: ExhibitorId): Unit = monitor.synchronized {
    state = state.withVisit(userId, exhibitorId)
  }

  override def scans(userId: UserId): Seq[ExhibitorId] = {
    val State(visitedExhibitors, _) = state
    visitedExhibitors(userId).toSeq
  }

  override def recommendations(userId: UserId): Seq[(ExhibitorId, Double)] = {
    val state@State(visitedExhibitors, _) = this.state
    val alreadyVisited = visitedExhibitors(userId)
    exhibitorService
      .exhibitorIds
      .iterator
      .filterNot(alreadyVisited)
      .map(id => id -> rateExhibitor(id, userId, state))
      .toArray
      .sortBy(_._2)
  }
}

object StorageServiceImpl {
  private case class State(
    visitedExhibitors: Map[UserId, Set[ExhibitorId]],
    visitingUsers: Map[ExhibitorId, Set[UserId]]
  ) {
    def withVisit(userId: UserId, exhibitor: ExhibitorId): State = State(
      visitedExhibitors + (userId -> (visitedExhibitors(userId) + exhibitor)),
      visitingUsers + (exhibitor -> (visitingUsers(exhibitor) + userId))
    )
  }

  private object State {
    val initial: State = State(
      Map.empty.withDefaultValue(Set.empty),
      Map.empty.withDefaultValue(Set.empty)
    )
  }


  private def rateExhibitor(exhibitorId: ExhibitorId, userId: UserId, state: State): Double = {
    val State(_, visitedUser) = state

    val userVisitedSimilarities = visitedUser(exhibitorId)
        .flatMap(userSimilarity(_, userId, state))

    if (userVisitedSimilarities.isEmpty) 0.4
    else userVisitedSimilarities.iterator.sum / userVisitedSimilarities.size
  }

  private def userSimilarity(user1: UserId, user2: UserId, state: State): Option[Double] = {
    val State(visitedExhibitors, _) = state
    val user1Set = visitedExhibitors(user1)
    val user2Set = visitedExhibitors(user2)

    if (user1Set.isEmpty && user2Set.isEmpty) None
    else Some((user1Set & user2Set).size.toDouble / (user1Set | user2Set).size)
  }
}