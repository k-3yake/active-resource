import org.specs2.mutable._
import org.specs2.specification.{Scope, BeforeExample}
import play.api.test.Helpers._
import play.api.test._
import anorm._
import play.api.db.DB


class PutSpec extends Specification {

  trait employeeSetupApplication extends WithApplication with Scope {
    DB.withConnection { implicit c =>
      SQL("delete from Employee").executeUpdate()
    }
  }

  "Employeeの追加" should {
    "パラメータの値でEmployeeを追加する" in new employeeSetupApplication{
      val putResponse = route(FakeRequest(PUT, """/Employee/name=bob""")).get
      status(putResponse) must equalTo(OK)
      contentType(putResponse) must beSome.which(_ == "application/json")
      contentAsString(putResponse) must contain ("""{"Employee":{"id":1,"name":"bob"}}""")
      val getResponse = route(FakeRequest(GET, """/Employee/1""")).get
      status(getResponse) must equalTo(OK)
      contentType(getResponse) must beSome.which(_ == "application/json")
      contentAsString(getResponse) must contain ("""{"Employee":{"id":1,"name":"bob"}}""")
    }
  }
}
