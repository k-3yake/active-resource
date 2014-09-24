package model

import anorm.SqlParser._
import anorm._
import org.h2.mvstore.`type`.StringDataType
import play.api.db.DB
import play.api.Play.current
import play.api.libs.json.Json.JsValueWrapper
import play.api.libs.json.{JsValue, Json, Writes}


/**
 * Created by katsuki on 2014/09/20.
 */
trait JsResource{
  def ResourceName:String
  def jsFields:Seq[Tuple2[String,JsValueWrapper]]

  def toJsValue() = {
    implicit val writes = new Writes[JsResource] {
      def writes(jr: JsResource) = Json.obj(jr.jsFields: _*)
    }
    Json.toJson(Map(ResourceName -> this))
  }
}
