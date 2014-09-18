package model

import anorm.SqlParser._
import anorm._
import play.api.db.DB
import play.api.Play.current
/**
 * Created by katsuki on 2014/09/15.
 */
class Employee(val id: Int,val name: String){
}

object Employee {
  val con = DB.getConnection()

  def find(id: Int) = {
    DB.withConnection { implicit c =>
      val parser = for {
        id <- int("id")
        name <- str("name")
      } yield (new Employee(id,name))

      SQL("select * from Employee where id = {id}").on('id -> id).as(parser.single)
    }
  }

  def unapply(employee: Employee) = {
    (employee.id,employee.name)
  }
}

