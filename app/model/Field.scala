package model

import anorm.NamedParameter

import scala.collection.immutable.Map

/**
 * Created by katsuki on 2014/09/23.
 */
class Fields(val fields: Map[String,AnyVal]*){

/*
  def toSqlFields:Seq[NamedParameter] = {
    for((name,value) <- fields;param = NamedParameter.apply(name,value)) yield (param)
  }
*/
}