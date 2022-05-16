package trees


fun main() {
    val tree = Tree.Node(
        7, Tree.Node(
            5, Tree.Node(3, Tree.Node(1), null), Tree.Node(6)
        ), Tree.Node(10, Tree.Node(8, null, Tree.Node(9)), Tree.Node<Any?>(12))
    )
    tree.walk()
    println()
    tree.leftWalk()
}