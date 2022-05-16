package trees;

public class Tree<T> {

    public static void main(String[] args) {
        Node<Integer> tree = new Node<>(7, new Node(
                5, new Node(3, new Node(1), null), new Node(6)
        ), new Node(10, new Node(8, null, new Node(9)), new Node(12)));
        tree.walk();
        System.out.println();
        tree.leftWalk();
    }

    Node<T> root;
    int size;

    static class Node<T> {
        T data;
        Node<T> left;
        Node<T> right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public void walk(){
            System.out.print(data + ", ");
            if (left != null){
                left.walk();
            }
            if (right != null){
                right.walk();
            }
        }
        public void leftWalk(){
            if (left != null){
                left.walk();
            }
            System.out.print(data);
            if (right != null){
                right.walk();
            }
        }
    }
}
