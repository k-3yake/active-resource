package model

import org.specs2.mutable._
import org.specs2.specification.{Scope, BeforeExample}
import play.api.test.Helpers._
import play.api.test._

/**
 * Created by katsuki on 2014/09/21.
 */
class EmployeeTest extends Specification {

  "JSON化のテスト" should {
    "フィールドの値をJSON化する" in {
    val employee = new Employee(1,"name1")
    employee.toJsValue().toString() must be_== ("""{"Employee":{"id":1,"name":"name1"}}""")
    }
  }
}
