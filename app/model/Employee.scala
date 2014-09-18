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
  val con = DB.getConnection()
  def toJsValue() = {
    implicit val employeeWrites = new Writes[Employee] {
      def writes(employee: Employee) = Json.obj("id" -> employee.id, "name" -> employee.name)
    }
    Json.toJson(Map("Employee" -> this))
  }
  def unapply(employee: Employee) = {
    (employee.id,employee.name)
  }
}

object Employee {
  def find(id: Int) = {
    DB.withConnection { implicit c =>
      val parser = for {
        id <- int("id")
        name <- str("name")
      } yield (new Employee(id,name))
      SQL("select * from Employee where id = {id}").on('id -> id).as(parser.single)
    }
  }
}