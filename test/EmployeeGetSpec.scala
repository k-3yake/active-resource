import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

class EmployeeGetSpec extends Specification {

  "EmployeeへのGET" should {
    "IDのEmployeeがある場合" in new WithApplication{
      val response = route(FakeRequest(GET, "/employee/1")).get
      status(response) must equalTo(OK)
      contentType(response) must beSome.which(_ == "application/json")
      contentAsString(response) must contain ("""{"employee":{"id":1,"name":"bob"}}""")
    }
  }
}
