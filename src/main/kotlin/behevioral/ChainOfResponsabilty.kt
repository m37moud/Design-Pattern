package behevioral

fun main() {
    val request = Request("example@example.com", question = "how are you ??")
    val chain = basicValidation(authentication(finalResponse()))
    val response = chain(request)
    println("Response = $response")
}


data class Request(val email: String, val question: String) {
    fun isKnownEmail() = true
    fun isJuniorDeveloper() = false
}

data class Respond(val answer: String)
/**
 * java way
 */
//interface Handler {
//    fun handle(request: Request): Response
//}
//
//class BasicValidation(private val next: Handler) : Handler {
//    override fun handle(request: Request): Response {
//        if (request.email.isEmpty()) {
//            if (request.question.isEmpty()) {
//                throw IllegalArgumentException()
//            }
//        }
//        return next.handle(request)
//    }
//}
typealias Handler = (request: Request) -> Respond

/**
 * act like interface have one function
 * this function take a parameter and return data
 * we do that as java way
 * interface Handler {
 *     fun handle(request: Request): Response
 * }
 * or replace it with kotlin way
 */
val basicValidation = fun(next: Handler) =
    fun(request: Request): Respond {
        if (request.email.isEmpty() || request.question.isEmpty())
            throw IllegalArgumentException()
        return next(request)
    }

val authentication = fun(next: Handler) =
    fun(request: Request): Respond {
        if (!request.isKnownEmail())
            throw IllegalArgumentException()
        return next(request)
    }

val finalResponse = fun() =
    fun(request: Request): Respond {
        return Respond("Any Message")
    }

/**
 * act as a function take function parameter and return function
 *
 */

