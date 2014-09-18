package model

import anorm.SqlParser._
import anorm._
import play.api.db.DB
import play.api.Play.current
import play.api.libs.json.{JsValue, Json, Writes}

/**
 * Created by katsuki on 2014/09/15.
 */

class Employee(val id: Int,val name: String){
  implicit val writes = new Writes[Employee] {
    def writes(employee: Employee) = Json.obj("id" -> employee.id, "name" -> employee.name)
  }
  def toJsValue() = {
    Json.toJson(Map(Employee.ResourceName -> this))
  }
}

object Employee {
  def ResourceName = "Employee"
  val con = DB.getConnection()
  val parser = for {
    id <- int("id")
    name <- str("name")
  } yield new Employee(id,name)
  def find(id: Int) = {
    DB.withConnection { implicit c =>
      SQL("select * from " + ResourceName + " where id = {id}").on('id -> id).as(parser.single)
    }
  }
}