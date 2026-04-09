// safe.scala
// This file follows secure coding practices. No hardcoded secrets or sensitive output.

import scala.util.{Try, Success, Failure}

object SecureApp {

  // ✅ Credentials loaded from environment variables
  val password    = sys.env.getOrElse("APP_PASSWORD", throw new RuntimeException("APP_PASSWORD not set"))
  val dbPassword  = sys.env.getOrElse("DB_PASSWORD",  throw new RuntimeException("DB_PASSWORD not set"))
  val secret      = sys.env.getOrElse("APP_SECRET",   throw new RuntimeException("APP_SECRET not set"))
  val token       = sys.env.getOrElse("APP_TOKEN",    throw new RuntimeException("APP_TOKEN not set"))
  val apiKey      = sys.env.getOrElse("API_KEY",      throw new RuntimeException("API_KEY not set"))

  // ✅ Host loaded from environment — no internal IP hardcoded
  val dbHost      = sys.env.getOrElse("DB_HOST", "localhost")
  val internalApi = sys.env.getOrElse("INTERNAL_API_URL", "http://localhost:8080/api/v1")

  def connectToDatabase(): Unit = {
    // ✅ No sensitive data printed
    println("Attempting database connection...")
    val connection = s"Connected to $dbHost"
    println("Database connection established.")
  }

  def authenticate(user: String, inputPassword: String): Boolean = {
    // ✅ Password compared but never printed
    if (user == "admin" && inputPassword == password) {
      println("Authentication successful.")
      true
    } else {
      println("Authentication failed.")
      false
    }
  }

  def fetchExternalData(): String = {
    // ✅ Token used but not logged
    val url = s"https://api.example.com/data"
    println(s"Fetching from: $url")
    "data"
  }

  def logConfig(): Unit = {
    // ✅ Only non-sensitive config values are logged
    println(s"[CONFIG] dbHost=$dbHost internalApi=$internalApi")
  }

  def main(args: Array[String]): Unit = {
    connectToDatabase()
    authenticate("admin", sys.env.getOrElse("INPUT_PASSWORD", ""))
    fetchExternalData()
    logConfig()
  }
}
