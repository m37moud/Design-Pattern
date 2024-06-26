package structure

fun main() {
    println("Hello World!")
    val starTrek = DefaultStartTrekRepository()
    val validate = ValidatingCapName(starTrek)
    val loggingStarTrek = LoggingStarTrek(validate)

    println(loggingStarTrek.getCaptinName("first star ship"))

    loggingStarTrek.setCaptin("second star ship" , "Cap : Fathy FathyFathyFathyFathy")
}



interface StarTrekRepository {
    fun getCaptinName(starShip: String): String
    fun setCaptin(starShip: String, captinName: String)

}

class DefaultStartTrekRepository : StarTrekRepository {
    val map = mutableMapOf("first star ship" to "cap :Mahmoud ")
    override fun getCaptinName(starShip: String): String {
        return map[starShip] ?: "Unknewon Error"
    }

    override fun setCaptin(starShip: String, captinName: String) {
        map[starShip] = captinName
    }

}

class LoggingStarTrek(val repository: StarTrekRepository) :
    StarTrekRepository by repository {

    override fun getCaptinName(starShip: String): String {
         println("try to get Captain for ship $starShip")
        return repository.getCaptinName(starShip)
    }
}

class ValidatingCapName(private val repository: StarTrekRepository) : StarTrekRepository by repository {
    private val captinMaxLengthChar = 15
    override fun setCaptin(starShip: String, captinName: String) {
        require(captinName.length <= captinMaxLengthChar) {
            println("captain $captinName char length is ")
        }

        repository.setCaptin(starShip, captinName)
    }
}


