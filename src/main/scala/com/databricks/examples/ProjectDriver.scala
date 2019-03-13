package com.databricks.examples

import com.databricks.dbutils_v1.DBUtilsHolder.dbutils

object ProjectDriver {

  def main(args: Array[String]): Unit = {
    val dateDimension = new DateDimension()
    val df = dateDimension.createDataFrame()
    df.show()

    println("Secrets API call now")
    val specialKey = dbutils.secrets.get("mladen-example", "mladen-special-key")
    println("Special Key: " + specialKey)
  }
}
