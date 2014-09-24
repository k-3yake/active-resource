package controllers

import model.{Animal, Employee}
import play.api._
import play.api.mvc._
import play.api.libs.json._
import play.api.libs.functional.syntax._


object ActiveResourceController extends Controller {
  def get(resource: String, id: Int) = Action {
    resource match {
      case "Employee" => Ok(new Employee(id).find().toJsValue())
      case "Animal" => Ok(new Animal(id).find().toJsValue())
      case _ => throw new IllegalArgumentException("Resource [" + resource + "] Not Found")
    }
  }

  def put(resource: String, params: String) = Action {
    println(resource)
    resource match {
      //case "Employee" => {
      case _ => {
        val employee = new Employee(1,"bob")
        employee.save()
        Ok(employee.toJsValue())}
      //case _ => throw new IllegalArgumentException("Resource [" + resource + "] Not Found")
    }
  }
}