package src.main.scala.model

object Contacts {
  def empty(): Contacts = Contacts("", "", "")
}

case class Contacts(phoneNumber: String,
                    email: String,
                    address: String)
