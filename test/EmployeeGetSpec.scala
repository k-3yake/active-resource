import org.junit.runner._
import org.specs2.mutable._
import org.specs2.runner._
import play.api.test.Helpers._
import play.api.test._

class EmployeeGetSpec extends Specification {

  "EmployeeをIDで取得した場合" should {
    "そのIDのEmployeeを取得する" in new WithApplication{
      val response = route(FakeRequest(GET, "/employee/2")).get
      status(response) must equalTo(OK)
      contentType(response) must beSome.which(_ == "application/json")
      contentAsString(response) must contain ("""{"employee":{"id":2,"name":"tom"}}""")
    }
  }
}
