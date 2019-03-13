name := "spark-sbt-jar"

version := "0.1"

scalaVersion := "2.11.12"

organization := "com.databricks.examples"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-sql_2.11" % "2.4.0" % "provided",
  "com.databricks" % "dbutils-api_2.11" % "0.0.3"
)