
public class run {

    public static void main(String[] args) {
        BST binarySearchTree = new BST();
        binarySearchTree.put(1,"Mark");
        binarySearchTree.put(1,"George");

       System.out.println( binarySearchTree.get(1));
    }
}
