package controllers

import data.{ExhibitorInfo, UserInfo}
import javax.inject.{Inject, Singleton}
import play.api.mvc._
import services.{ExhibitorService, StorageService, UserService}

@Singleton
class PushScanController @Inject()(storageService: StorageService,
                                   exhibitorService: ExhibitorService,
                                   userService: UserService,
                                   cc: ControllerComponents) extends AbstractController(cc) {

  def clearScans: Action[AnyContent] = Action {
    storageService.clearScans()
    Ok("Ok")
  }

  def pushScan(userId: String, exhibitorId: String): Action[AnyContent] = Action {
    (userService.resolve(userId), exhibitorService.resolve(exhibitorId)) match {
      case (Some(user), Some(exhibitor)) =>
        storageService.addScan(user, exhibitor)
        Ok("Ok")
      case _ =>
        BadRequest("Invalid usedId or exhibitorId")
    }
  }
}
