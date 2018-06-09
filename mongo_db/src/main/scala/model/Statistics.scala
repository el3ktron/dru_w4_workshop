package src.main.scala.model

object Statistics {
  def empty(): Statistics = Statistics(0,0,0,0,0,0,"")
}

case class Statistics(employees: Int,
                      yearOfFounding: Int,
                      mediumProjectSize: Int,
                      mediumEstimates: Int,
                      rate: Double,
                      mediumAge: Double,
                      mediumGender: String)
