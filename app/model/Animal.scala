package model

import anorm.RowParser
import play.api.libs.json.{Json, Writes, JsValue}
import anorm.SqlParser._



/**
 * Created by katsuki on 2014/09/20.
 */
class Animal(val id: Int,val name: String, val legsCount: Int) extends Resource[Animal]{
  def this(id: Int) = this(id,"",0)
  override def ResourceName: String = "Animal"

  def toJsValue() = {
    implicit val writes = new Writes[Animal] {
      def writes(animal: Animal) = Json.obj("id" -> animal.id, "name" -> animal.name, "legsCount" -> animal.legsCount)
    }
    Json.toJson(Map(ResourceName -> this))
  }

  def parser:RowParser[Animal] = {
    for {
      id <- int("id")
      name <- str("name")
      legsCount <- int("legs_count")
    } yield new Animal(id,name,legsCount)
  }
}
