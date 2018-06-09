import daos.CompanyDAO
import org.bson.types.ObjectId
import src.main.scala.model.{Company, Contacts, Statistics}

import scala.util.Random
import scala.util.Random._

object MongoWorkshop extends App {
  val simpleCompany = Company(
    new ObjectId(),
    "Dataroot Labs",
    "Big Data and AI",
    100000,
    List("Ivan Didur", "Max Frolov"),
    Statistics(
      15,
      2014,
      10000,
      2,
      4.0,
      21.0,
      "male"
    ),
    Contacts(
      "011240124124",
      "datarootlabs.com",
      "Polyova str., 21, Kiev"
    )
  )

  CompanyDAO.create(simpleCompany)

  CompanyDAO.update(new ObjectId("5b1bd63db888110c7b57f51e"),"name", "Dataroot Labs 2.0")

  CompanyDAO.delete(new ObjectId("5b1bd63db888110c7b57f51e"))

  val companyToFind = CompanyDAO.find(new ObjectId("5b1bd5d8b888110c5ff08ab4"))
  println(companyToFind)

  CompanyDAO.deleteAll()

  val companies: Seq[Company] = (1 to 10) map { index =>
    Company.empty()
  }

  CompanyDAO.createMany(companies)

  CompanyDAO.updateMany("name","", "name", Random.nextString(10).toLowerCase.toString())

  CompanyDAO.printAll()
}
