import scala.io.Source

object InsecureApp {

  // Hardcoded credentials
  val password     = "admin123"
  val dbPassword   = "superSecret99"
  val secret       = "my_super_secret_value"
  val token        = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.abc123"

  // Hardcoded AWS API Key
  val apiKey       = "AKIA1234567890ABCDEF"
  val awsSecret    = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"

  // Hardcoded internal IP addresses
  val dbHost       = "192.168.1.105"
  val internalApi  = "http://10.0.0.22:8080/api/v1"

  // Hardcoded suspicious URL with credentials embedded
  val jdbcUrl      = "jdbc:postgresql://db.internal.corp:5432/mydb?user=admin&password=admin123"

  def connectToDatabase(): Unit = {
    // Printing sensitive credentials to console
    println(password)
    println(s"Connecting to DB at $dbHost with password: $dbPassword")
    println(s"Using API key: $apiKey")

    // Simulated connection logic
    val connection = s"Connected to $dbHost"
    println(connection)
  }

  def authenticate(user: String): Boolean = {
    // Hardcoded password used in comparison
    val adminPass = "admin123"
    if (user == "admin" && adminPass == password) {
      println(s"Token granted: $token")
      true
    } else {
      false
    }
  }

  def fetchExternalData(): String = {
    // Hardcoded external endpoint with embedded token
    val url = s"https://api.example.com/data?access_token=$token"
    println(s"Fetching from: $url")
    "data"
  }

  def logConfig(): Unit = {
    // Logging all secrets at once
    println(s"[CONFIG] password=$password secret=$secret token=$token apiKey=$apiKey")
  }

  def main(args: Array[String]): Unit = {
    connectToDatabase()
    authenticate("admin")
    fetchExternalData()
    logConfig()
  }
}