package controllers

import model.Employee
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._


object ActiveResourceController extends Controller {
  def get(id: Int) = Action {
    Ok(Employee.find(id).toJsValue())
  }
}