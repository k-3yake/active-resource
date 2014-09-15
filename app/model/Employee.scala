package model

/**
 * Created by katsuki on 2014/09/15.
 */
class Employee(val id: Int,val name: String){

}

object Employee {
  def unapply(employee: Employee) = {
    (employee.id,employee.name)
  }
}

