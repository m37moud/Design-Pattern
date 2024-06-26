package structure

interface Trooper {
    fun move(x: Int, y: Int)
    fun attackRebel(x: Int, y: Int)
}

typealias longmeter = Long
typealias longint = Int

interface Weapon {
    fun attack(): longmeter
}

interface Legs {
    fun move(x: Int, y: Int): longint
}

const val POWER_WEAPON = 1L

class NormalWeapon() : Weapon {
    override fun attack(): longmeter = POWER_WEAPON
}

class FireWeapon : Weapon {
    override fun attack(): longmeter = POWER_WEAPON * 2
}

const val NORMAL_MOVE = 2

class NormalSpeed : Legs {
    override fun move(x: Int, y: Int) = NORMAL_MOVE
}

class MoreSpeed : Legs {
    override fun move(x: Int, y: Int) = NORMAL_MOVE * 2
}

data class StormTroopers(val weapon: Weapon, val legs: Legs) : Trooper {
    override fun move(x: Int, y: Int) {
        println("StormTroopers Moving")
    }

    override fun attackRebel(x: Int, y: Int) {
        println("StormTroopers Attackking")
    }
}

fun main() {
    val riotStorm = StormTroopers(NormalWeapon(), NormalSpeed())
}