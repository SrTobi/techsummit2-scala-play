package controllers

import data.{ExhibitorInfo, UserInfo}
import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._

@Singleton
class RecommendationController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def getRecommendations(user: String): Action[AnyContent] = Action {
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
