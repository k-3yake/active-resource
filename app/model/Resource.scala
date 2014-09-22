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
trait Resource {
  lazy val con = DB.getConnection()
  def ResourceName:String
  def id:Int
  def parser:RowParser[Resource]
  def jsField:Seq[Tuple2[String,JsValueWrapper]]

  def toJsValue() = {
    implicit val writes:Writes[Resource] = new Writes[Resource] {
      def writes(employee: Resource) = Json.obj(employee.jsField: _*)
    }
    Json.toJson(Map(ResourceName -> this))
  }


  def find():Resource = {
    DB.withConnection { implicit c =>
      SQL("select * from " + ResourceName + " where id = {id}").on('id -> id).as(parser.single)
    }
  }
}
