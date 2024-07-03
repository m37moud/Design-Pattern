package structure

fun main() {
    println("Hello World!")
    val starTrek = DefaultStartTrekRepository()
    val validate = ValidatingCapName(starTrek)
    val loggingStarTrek = LoggingStarTrek(validate)

    println(loggingStarTrek.getCaptainName("first star ship"))

    loggingStarTrek.setCaptain("second star ship" , "Cap : Fathy FathyFathyFathyFathy")
}



interface StarTrekRepository {
    fun getCaptainName(starShip: String): String
    fun setCaptain(starShip: String, captainName: String)

}

class DefaultStartTrekRepository : StarTrekRepository {
    private val map = mutableMapOf("first star ship" to "cap :Mahmoud ")
    override fun getCaptainName(starShip: String): String {
        return map[starShip] ?: "Unknown Error"
    }

    override fun setCaptain(starShip: String, captainName: String) {
        map[starShip] = captainName
    }

}

class LoggingStarTrek(val repository: StarTrekRepository) :
    StarTrekRepository by repository {

    override fun getCaptainName(starShip: String): String {
         println("try to get Captain for ship $starShip")
        return repository.getCaptainName(starShip)
    }
}

class ValidatingCapName(private val repository: StarTrekRepository) : StarTrekRepository by repository {
    private val captainMaxLengthChar = 15
    override fun setCaptain(starShip: String, captainName: String) {
        require(captainName.length <= captainMaxLengthChar) {
            println("captain $captainName char length is ")
        }

        repository.setCaptain(starShip, captainName)
    }
}


