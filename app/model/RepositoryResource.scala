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
trait RepositoryResource {
  lazy val con = DB.getConnection()
  def ResourceName:String
  def id:Int
  def rowParser:RowParser[ActiveResource]

  def find():ActiveResource = {
    DB.withConnection { implicit c =>
      SQL("select * from " + ResourceName + " where id = {id}").on('id -> id).as(rowParser.single)
    }
  }
}
