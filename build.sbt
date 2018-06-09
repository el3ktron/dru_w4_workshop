val globalSettings = Seq[SettingsDefinition](
  version := "0.1",
  scalaVersion := "2.12.6",
)

val mongoDb = Project("mongo", file("mongo_db"))
  .settings(globalSettings: _*)
  .settings(
    name := "MongoDb",
    libraryDependencies ++= Seq(
      "org.mongodb.scala" %% "mongo-scala-driver" % "2.3.0",
    )
  )

val reactiveMongoDb = Project("reactive_mongo", file("reactive_mongo"))
  .dependsOn(mongoDb)
  .settings(globalSettings: _*)
  .settings(
    name := "ReactiveMongo",
    libraryDependencies ++= Seq(
      "org.reactivemongo" %% "reactivemongo" % "0.12.7",
      "com.typesafe.akka" %% "akka-actor" % "2.5.13",
      "com.typesafe.akka" %% "akka-http" % "10.1.1",
      "com.typesafe.akka" %% "akka-stream" % "2.5.13",
      "net.ruippeixotog" %% "scala-scraper" % "2.1.0"
    )
  )