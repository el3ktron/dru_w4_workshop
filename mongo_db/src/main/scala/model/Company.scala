package src.main.scala.model

import org.bson.types.ObjectId

case class Company(_id: ObjectId,
                   name: String,
                   sphere: String,
                   price: Int,
                   owners: List[String],
                   statistics: Statistics,
                   contacts: Contacts)
