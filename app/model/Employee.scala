package model

import anorm.SqlParser._
import anorm._
import play.api.db.DB
import play.api.Play.current
import play.api.libs.json.{JsValue, Json, Writes}



/**
 * Created by katsuki on 2014/09/15.
 */

class Employee(val id: Int,val name: String)extends Resource[Employee] {
  def this(id: Int) = this(id, "")
  def ResourceName = "Employee"

  def toJsValue() = {
    implicit val writes = new Writes[Employee] {
      def writes(employee: Employee) = Json.obj("id" -> employee.id, "name" -> employee.name)
    }
    Json.toJson(Map(ResourceName -> this))
  }

  def parser:RowParser[Employee] = {
    for {
      id <- int("id")
      name <- str("name")
    } yield new Employee(id,name)
  }
}
