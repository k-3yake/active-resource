import java.io.File
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder
import org.dbunit.{JdbcDatabaseTester, IDatabaseTester}
import org.specs2.mutable._
import org.specs2.specification.BeforeAfter
import play.api.test.Helpers._
import play.api.test._


class GetSpec2 extends Specification {

  trait WithDbApp extends WithApplication with BeforeAfter {
    val setupFiles: Seq[String]
    val tester = new JdbcDatabaseTester("org.h2.Driver","jdbc:h2:tcp://localhost/~/test","sa","sa");

    override def before(){
      for(fileName <- setupFiles)
        tester.setDataSet(new FlatXmlDataSetBuilder().build(new File("./test/" + fileName)))
      tester.onSetup()
    }
    override def after() = tester.onTearDown()
  }

  "EmployeeのGET" should {
    "IDのEmployeeを返す" in new WithDbApp {
      val setupFiles = Seq("employee-2record.xml")
      val response = route(FakeRequest(GET, "/Employee/2")).get
      status(response) must equalTo(OK)
      contentType(response) must beSome.which(_ == "application/json")
      contentAsString(response) must contain ("""{"Employee":{"id":2,"name":"tom"}}""")
    }
  }

  "AnimalのGET" should {
    "IDのAnimalを返す" in new WithDbApp {
      val setupFiles = Seq("animal-2record.xml")
      val response = route(FakeRequest(GET, "/Animal/2")).get
      status(response) must equalTo(OK)
      contentType(response) must beSome.which(_ == "application/json")
      contentAsString(response) must contain ("""{"Animal":{"id":2,"name":"cat","legsCount":4}}""")
    }
  }

}
