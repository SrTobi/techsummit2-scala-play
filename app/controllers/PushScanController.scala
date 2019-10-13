package controllers

import data.{ExhibitorInfo, UserInfo}
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import services.{ExhibitorService, StorageService, UserService}
import play.api.Logger

@Singleton
class PushScanController @Inject()(storageService: StorageService,
                                   exhibitorService: ExhibitorService,
                                   userService: UserService,
                                   cc: ControllerComponents) extends AbstractController(cc) {
  private val logger = Logger("PushScanController")

  def clearScans: Action[AnyContent] = Action {
    storageService.clearScans()
    logger.debug("Clear storage")
    Ok("Ok")
  }

  def pushScan(userId: String, exhibitorId: String): Action[AnyContent] = Action {
    (userService.resolve(userId), exhibitorService.resolve(exhibitorId)) match {
      case (Some(user), Some(exhibitor)) =>
        storageService.addScan(user, exhibitor)
        logger.debug(s"Added ($userId, $exhibitorId)")
        Ok("Ok")
      case _ =>
        logger.error(s"Wrong user or id:($userId, $exhibitorId)")
        BadRequest("Invalid usedId or exhibitorId")
    }
  }
}
