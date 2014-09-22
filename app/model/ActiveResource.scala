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
trait ActiveResource extends RepositoryResource[ActiveResource] with JsResource

