package model

import anorm.SqlParser._
import anorm._
import play.api.db.DB
import play.api.Play.current
import play.api.libs.json.Json.{JsValueWrapper}
import play.api.libs.json.{JsObject, JsValue, Json, Writes}




/**
 * Created by katsuki on 2014/09/15.
 */

class Employee(val id: Int,val name: String)extends Resource {
  def this(id: Int) = this(id, "")
  def ResourceName = "Employee"

  def jsField:Seq[Tuple2[String,JsValueWrapper]] = {
    Seq("id" -> id, "name" -> name)
  }

  def parser:RowParser[Employee] = {
    for {
      id <- int("id")
      name <- str("name")
    } yield new Employee(id,name)
  }
}
