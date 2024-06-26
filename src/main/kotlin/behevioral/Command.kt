package behevioral

/**
 * command design patter aim to handle some function to execute simulation
 * or in another time
 */

fun main() {
    val trooper = Trooper()
    trooper
        .appendMove(20, 0)
        .appendMove(20, 20)
        .appendMove(5, 20)
        .executeCommand()

}

/**
 * act like interface have one function
 * this function doesn't take parameter and don't return anything
 * interface Command {
 *     fun execute()
 * }
 */
typealias Command = () -> Unit

class Trooper {
    private val commands = mutableListOf<Command>()

    /**
     * Generator Function :
     *     take a function and return function
     */
    val moveGenerator = fun(
        t: Trooper,
        x: Int,
        y: Int
    ): Command {
        return fun() {
            t.move(x, y)
        }
    }

    fun appendMove(x: Int, y: Int) = apply {
        commands.add(moveGenerator(this, x, y))
    }

    fun executeCommand() {
        while (commands.isNotEmpty()) {
            val order = commands.removeFirst()
            order.invoke()
        }
    }

    private fun move(x: Int, y: Int) {
        println("moving to $x , $y")

    }

}