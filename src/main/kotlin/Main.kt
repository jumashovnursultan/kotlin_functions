
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    val url = "https://jsonplaceholder.typicode.com/todos/1"
    val response = makeGetRequest(url)
    println(response)
}



fun makeGetRequest(url: String): String {
    var result = ""

    try {
        val urlObject = URL(url)
        val connection = urlObject.openConnection() as HttpURLConnection

        // Set the request method to GET
        connection.requestMethod = "GET"

        // Get the response code
        val responseCode = connection.responseCode

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response
            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            var line: String?

            while (reader.readLine().also { line = it } != null) {
                result += line
            }
            reader.close()
        } else {
            result = "Error: $responseCode"
        }
    } catch (e: Exception) {
        e.printStackTrace()
        result = "Error: ${e.message}"
    }

    return result
}
