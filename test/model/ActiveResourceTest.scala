package model

import org.specs2.mutable._
import org.specs2.specification.{Scope, BeforeExample}
import play.api.test.Helpers._
import play.api.test._


/**
 * Created by katsuki on 2014/09/21.
 */
class ActiveResourceTest extends Specification {

  "implicitParameterの学習テスト" should {
    "暗黙型パラメータを明示的に渡せる" in {
      val result = new ImplicitParameterReceiver().receive(new Param(("a")))
      result must be_== ("a received")
    }
  }

  class ImplicitParameterReceiver {
    def receive(implicit param: Param):String = {
      param.str + " received"
    }
  }

  class Param(val str: String)

}
