package daos

import mongo.Helpers
import org.bson.codecs.configuration.CodecRegistries.{fromProviders, fromRegistries}
import org.bson.codecs.configuration.CodecRegistry
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.bson.codecs.DEFAULT_CODEC_REGISTRY
import org.mongodb.scala.bson.codecs.Macros._
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Updates._
import org.mongodb.scala.{MongoClient, MongoCollection}
import src.main.scala.model.{Company, Contacts, Statistics}

object CompanyDAO extends Helpers {
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

  val mongoClient = MongoClient("mongodb://admin:testpass1@ds241530.mlab.com:41530/dru_w4_mongo")
  val companyCodecRegistry: CodecRegistry = fromRegistries(fromProviders(classOf[Company], classOf[Statistics], classOf[Contacts]), DEFAULT_CODEC_REGISTRY)
  val database = mongoClient.getDatabase("dru_w4_mongo").withCodecRegistry(companyCodecRegistry)
  val companyCollection: MongoCollection[Company] = database.getCollection("companies")

  def create(company: Company) =
    companyCollection
      .insertOne(company)
      .printHeadResult("Insert result = ")

  def createMany(companies: Seq[Company]) =
    companyCollection
      .insertMany(companies)
      .printHeadResult("Insert (many) result = ")

  def update(companyId: ObjectId, fieldToUpdate: String, newValue: Any) =
    companyCollection
      .updateOne(equal("_id", companyId), set(fieldToUpdate, newValue))
      .printHeadResult("Update result = ")

  def updateMany(fieldToIdentify: String, identifier: Any, fieldToUpdate: String, newValue: Any) =
    companyCollection
      .updateMany(equal(fieldToIdentify, identifier), set(fieldToUpdate, newValue))
      .printHeadResult("Update (many) result = ")

  def delete(companyId: ObjectId) =
    companyCollection
      .deleteOne(equal("_id", companyId))
      .printHeadResult("Delete result = ")

  def deleteAll(): Unit = companyCollection.drop()

  def find(companyId: ObjectId) =
    companyCollection
      .find(equal("_id", companyId))
      .first()
      .headResult()

  def findAll(): Seq[Company] = companyCollection.find().results()

  def printAll(): Unit = findAll() foreach println
}
