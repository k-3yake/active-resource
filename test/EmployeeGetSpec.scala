import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import org.specs2.specification.{Scope, BeforeExample}
import play.api.test.Helpers._
import play.api.test._
import anorm._
import play.api.db.DB


class EmployeeGetSpec extends Specification {

  trait employeeSetupApplication extends WithApplication with Scope {
    DB.withConnection { implicit c =>
      SQL("delete from Employee").executeUpdate()
      val insert = SQL("insert into Employee(ID,NAME) values ({id}, {name})")
      insert.on('id -> "1", 'name -> "bob").executeInsert()
      insert.on('id -> "2", 'name -> "tom").executeInsert()
    }
  }

  "IDでのEmployeeの取得" should {
    "そのIDのEmployeeを返す" in new employeeSetupApplication{
      val response = route(FakeRequest(GET, "/Employee/2")).get
      status(response) must equalTo(OK)
      contentType(response) must beSome.which(_ == "application/json")
      contentAsString(response) must contain ("""{"Employee":{"id":2,"name":"tom"}}""")
    }
  }
}
