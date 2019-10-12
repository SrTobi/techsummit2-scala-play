package controllers

import data.{ExhibitorInfo, UserInfo}
import javax.inject.{Inject, Singleton}
import play.api.mvc._

@Singleton
class PushScanController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def pushScan(userId: String, exhibitorId: String): Action[AnyContent] = Action {
    if (userId == UserInfo.dummyName && exhibitorId == ExhibitorInfo.dummyInfo.name)
      Ok("Ok")
    else
      BadRequest("Invalid usedId or exhibitorId")
  }
}
