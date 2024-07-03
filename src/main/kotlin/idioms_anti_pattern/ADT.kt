package org.example.idioms_anti_pattern


/**
 * ADT is a shortcut for Algebraic Data type
 */

fun main() {
    val tree = Node(
        data = 1,
        left = Empty,
        right = Node(
            2,
            Node(data = 3)
        )
    )
    println(tree.sum())

}

sealed interface Tree<out T>

object Empty : Tree<Nothing> {
    override fun toString(): String = "Empty"
}

data class Node<T>(
    val data: T,
    val left: Tree<T> = Empty,
    val right: Tree<T> = Empty
) : Tree<T>

fun Tree<Int>.sum(): Long = when (this) {
    Empty -> 0
    is Node -> data + left.sum() + right.sum()
}