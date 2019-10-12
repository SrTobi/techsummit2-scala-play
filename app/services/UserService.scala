package services

import data.UserId
import javax.inject.Singleton

trait UserService {
  def resolve(id: String): Option[UserId]
}



@Singleton
class UserServiceImpl extends UserService {
  override def resolve(id: String): Option[UserId] = {
    Some(new UserId(id))
  }
}
