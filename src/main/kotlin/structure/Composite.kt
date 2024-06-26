package structure

fun main() {
    val marchTroopers = StormTroopers(FireWeapon(), MoreSpeed())

    val units = Squad(marchTroopers.copy(), marchTroopers.copy(), marchTroopers.copy())
    units.move(1, 2)
    val platoon = Squad(units, units)
    platoon.attackRebel(2, 2)
}

class Squad(private val units: List<Trooper>) : Trooper {
    constructor(vararg trooper: Trooper) : this(trooper.toList())

    override fun move(x: Int, y: Int) {
        for (u in units) {
            u.move(x, y)
        }
    }

    override fun attackRebel(x: Int, y: Int) {
        units.forEach {
            it.attackRebel(x, y)
        }
    }
}