package model

import anorm.RowParser
import play.api.libs.json.Json.JsValueWrapper
import play.api.libs.json.{Json, Writes, JsValue}
import anorm.SqlParser._



/**
 * Created by katsuki on 2014/09/20.
 */
class Animal(val id: Int,val name: String, val legsCount: Int) extends ActiveResource {
  def this(id: Int) = this(id, "", 0)

  override def ResourceName: String = "Animal"

  def jsField = Seq("id" -> id, "name" -> name, "legsCount" -> legsCount)

  def rowParser = for {
    id <- int("id")
    name <- str("name")
    legsCount <- int("legs_count")
  } yield new Animal(id,name,legsCount)
}