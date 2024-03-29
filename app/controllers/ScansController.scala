package controllers

import data.{ExhibitorInfo, UserInfo}
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import services.{ExhibitorService, StorageService, UserService}

@Singleton
class ScansController @Inject()(userService: UserService,
                                exhibitorService: ExhibitorService,
                                storageService: StorageService,
                                cc: ControllerComponents) extends AbstractController(cc) {
  def getScans(userId: String): Action[AnyContent] = Action {
    userService.resolve(userId) match {
      case None => BadRequest("Invalid user id")
      case Some(user) =>
        val scans = storageService.scans(user)
        val exhibitors = scans.map(exhibitorService.info)
        Ok(
          Json.obj(
            "scans" -> Json.toJson(exhibitors)
          )
        )
    }
  }
}
