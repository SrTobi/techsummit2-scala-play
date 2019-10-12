package controllers

import data.{ExhibitorInfo, UserInfo}
import javax.inject.{Inject, Singleton}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

@Singleton
class ScansController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def getScans(user: String): Action[AnyContent] = Action {
    if (user == UserInfo.dummyName)
      Ok(
        Json.obj(
          "recommendations" -> Json.arr(Json.toJson(ExhibitorInfo.dummyInfo))
        )
      )
    else
      BadRequest("Invalid user id")
  }
}
