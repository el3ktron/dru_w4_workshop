package daos

import org.bson.types.ObjectId
import org.mongodb.scala.MongoClient
import src.main.scala.model.{Company, Contacts, Statistics}


object CompanyDAO  {
  def apply(name: String,
            sphere: String,
            price: Int,
            owners: List[String],
            statistics: Statistics,
            contacts: Contacts) = Company(
    new ObjectId(),
    name,
    sphere,
    price,
    owners,
    statistics,
    contacts
  )

  val mongoClient = MongoClient()
}
