package behevioral

fun main() {
    val sql = select("id , name") {
        from("users") {
            where("id > 5")
        } //closes from
    } // closes select

    println(sql)
}

fun select(columns: String, from: SelectClause.() -> Unit): SelectClause {
    return SelectClause(columns).apply(from)

}

class SelectClause(private val columns: String) {
    lateinit var from: FromClause
    fun from(
        table: String,
        where: FromClause.() -> Unit
    ): FromClause {
        this.from = FromClause(table)
        return this.from.apply(where)
    }

    override fun toString(): String {
        return "SELECT $columns $from"
    }
}

class FromClause(private val table: String) {
    private lateinit var where: WhereClause

    fun where(condition: String) = this.apply {
        where = WhereClause(condition)
    }

    override fun toString(): String {
        return "FROM $table $where"
    }
}

class WhereClause(private val condition: String) {
    override fun toString(): String {
        return "WHERE $condition"
    }

}

