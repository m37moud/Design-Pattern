package org.example.threads_coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.example.threads_coroutine.Async.Companion.getBio
import org.example.threads_coroutine.Async.Companion.getFriends
import org.example.threads_coroutine.Async.Companion.getProfileImage
import kotlin.system.measureTimeMillis

fun main() {
    /**
     * run blocking it act Bridge Design pattern
     * because it allows to connect between regular code and another coroutine code
     */
    runBlocking {
        val t1 = measureTimeMillis {
            Blocking.profile()
        }
        val t2 = measureTimeMillis {
            Async.profile()
        }
        val t3 = measureTimeMillis {
            Sysbend.profile()
        }
        println("Blocking code: $t1")
        println("Async: $t2")
        println("Suspend: $t3")
    }

}

data class Profile(val bio: String, val img: ByteArray?, val friends: List<String>)
class Blocking {
    companion object {
        fun profile(): Profile {
            val bio = getBio()
            val img = getProfileImage()
            val friends = getAllFriendsFromDb()
            return Profile(bio, img, friends)

        }

        private fun getBio(): String {
            Thread.sleep(1000)
            return "mahmoud is the best"
        }

        private fun getProfileImage(): ByteArray? {
            Thread.sleep(100)
            return null
        }

        private fun getAllFriendsFromDb(): List<String> {
            Thread.sleep(500)
            return emptyList()
        }
    }


}

class Async {
    companion object {
        suspend fun profile(): Profile {
            val bio = getBio()
            val img = getProfileImage()
            val friends = getFriends()
            return Profile(bio.await(), img.await(), friends.await())
        }

        private fun getBio() = GlobalScope.async {
            delay(1000)
            "Mahmoud is the best"
        }

        private fun getProfileImage() = GlobalScope.async {
            delay(100)
            null
        }

        private fun getFriends() = GlobalScope.async {
            delay(500)
            emptyList<String>()
        }
    }
}

class Sysbend {
    companion object {
        suspend fun profile(): Profile {
            val bio = getBio()
            val img = getProfileImage()
            val friends = getFriends()
            return Profile(bio, img, friends)
        }

        private suspend fun getBio(): String {
            delay(1000)
            return "Mahmoud is the best"
        }

        private suspend fun getProfileImage(): ByteArray? {
            delay(100)
            return null
        }

        private suspend fun getFriends(): List<String> {
            delay(500)
            return emptyList()
        }
    }
}
/**
 * compiler translate suspend function to this code it act State Design Pattern
 */
/*
suspend fun profile(state: Int, id: String, context: ArrayList<Any>): Profile {
    when (state) {
        0 -> {
            context += fetchBioOverHttp(id) // takes 1s
            profile(1, id, context)
        }
        1 -> {
            context += fetchPictureFromDB(id) // takes 100ms
            profile(2, id, context)
        }
        2 -> {
            context += fetchFriendsFromDB(id) // takes 500ms
            profile(3, id, context)
        }
        3 -> {
            val (bio, picture, friends) = context
            return Profile(bio, picture, friends)
        }
    }
}*/