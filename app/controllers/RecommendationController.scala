package controllers

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._
import services.{ExhibitorService, StorageService, UserService}

@Singleton
class RecommendationController @Inject()(userService: UserService,
                                         exhibitorService: ExhibitorService,
                                         storageService: StorageService,
                                         cc: ControllerComponents) extends AbstractController(cc) {
  def getRecommendations(userId: String): Action[AnyContent] = Action {
    userService.resolve(userId) match {
      case None => BadRequest("Invalid user id")
      case Some(user) =>
        val recommendations = storageService.recommendations(user)
        Ok(
          Json.obj(
            "recommendations" -> recommendations.map {
              case (exhibitorId, rating) =>
                Json.toJsObject(exhibitorService.info(exhibitorId)) +
                  ("rating" -> Json.toJson(rating))
            }
          )
        )
    }
  }
}

