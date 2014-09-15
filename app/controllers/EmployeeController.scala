package controllers

import model.Employee
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._


object EmployeeController extends Controller {

  implicit val employeeWrites = new Writes[Employee] {
    def writes(employee: Employee) = Json.obj("id" -> employee.id, "name" -> employee.name)
  }
  def get(id: Int) = Action {
    val map = Map("employee" -> new Employee(1,"bob"))
    Ok(Json.toJson(map))
  }

}