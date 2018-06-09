package src.main.scala.model

import org.bson.types.ObjectId

object Company {
  def empty(): Company = Company(new ObjectId(), "" , "", 0, List[String](), Statistics.empty, Contacts.empty)
}

case class Company(_id: ObjectId,
                   name: String,
                   sphere: String,
                   price: Int,
                   owners: List[String],
                   statistics: Statistics,
                   contacts: Contacts)
