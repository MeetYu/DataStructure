package datastructure.tree;

import java.util.Random;

/**
 * Created by Administrator on 2015/3/6.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T node) {
        return contains(node, root);
    }

    public T findMax() {
        return findMax(root).element;
    }

    public T findMin() {
        return findMin(root).element;
    }

    public void insert(T element) {
        root = insert(element, root);
    }

    public void remove(T element) {
        root = remove(element, root);
    }

    public void printTreeByPreorder() {
        printTreeByPreorder(root);
    }

    public void printTreeByPostorder() {
        printTreeByPostorder(root);
    }

    public void printTreeByInorder() {
        printTreeByInorder(root);
    }

    /**
     * Internal method to find an element in a tree
     * @param element the item to search for
     * @param node the node that roots the tree
     * @return true if the node contains the matched item.Otherwise, false
     */
    private boolean contains(T element, BinaryNode<T> node) {
        if (node == null)
            return false;

        int compareResult = element.compareTo(node.element);

        if (compareResult < 0)
            return contains(element, node.leftNode);
        else if (compareResult > 0)
            return contains(element, node.rightNode);
        else
            return true;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param node the node that roots the subtree
     * @return the node containing the smallest item. If the
     *         subtree is null, return null.
     */
    private BinaryNode<T> findMin(BinaryNode<T> node) {
        if (node == null)
            return null;
        else if (node.leftNode == null)
            return node;

        return findMin(node.leftNode);
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param node the node that roots the subtree
     * @return the node containing the smallest item. If the
     *         subtree is null, return null.
     */
    private BinaryNode<T> findMax(BinaryNode<T> node) {
        //递归实现
        if (node == null)
            return null;
        else if (node.rightNode == null)
            return node;

        return findMax(node.rightNode);

        /*非递归实现
        if (tree != null)
        {
            while (tree.getRightNode() != null)
                tree = tree.getRightNode();
        }

        return tree;*/
    }

    /**
     * Internal method to insert an element into the tree
     * @param element the item to insert
     * @param node the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<T> insert(T element, BinaryNode<T> node) {
        if (node == null)
            return new BinaryNode<T>(element, null, null);

        int compareResult = element.compareTo(node.element);

        if (compareResult < 0)
            node.leftNode = insert(element, node.leftNode);
        else if (compareResult > 0)
            node.rightNode = insert(element, node.rightNode);
        else
            ; //Duplicate; do nothing

        return node;
    }

    /**
     * Internal method to remove an element from a tree
     * @param element the item to remove
     * @param node the node that roots the subtree
     * @return the new root of the subtree
     */
    private BinaryNode<T> remove(T element, BinaryNode<T> node) {
        if (node == null)
            return node;

        int cmp = element.compareTo(node.element);

        if (cmp < 0)
            node.leftNode = remove(element, node.leftNode);
        else  if (cmp > 0 )
            node.rightNode = remove(element, node.rightNode);
        else if (node.leftNode != null && node.rightNode != null) { //Two children
            node.element = findMin(node.rightNode).element;
            node.rightNode = remove(node.element, node.rightNode);
        }
        else
            node = (node.leftNode != null) ? node.leftNode : node.rightNode;

        return node;
    }

    /**
     * Internal method to traverse the tree by pre-order.
     * @param node the tree to be traverse
     */
    private void printTreeByPreorder(BinaryNode<T> node) {
        if (node == null)
            return;

        System.out.println(node.element);
        printTreeByPreorder(node.leftNode);
        printTreeByPreorder(node.rightNode);
    }

    /**
     * Internal method to traverse the tree by post-order.
     * @param node the tree to be traverse
     */
    private void printTreeByPostorder(BinaryNode<T> node) {
        if (node == null)
            return;

        printTreeByPostorder(node.leftNode);
        printTreeByPostorder(node.rightNode);
        System.out.println(node.element);
    }

    /**
     * Internal method to traverse the tree by in-order.
     * @param node the tree to be traverse.
     */
    private void printTreeByInorder(BinaryNode<T> node) {
        if (node == null)
            return;
        printTreeByInorder(node.leftNode);
        System.out.println(node.element);
        printTreeByInorder(node.rightNode);
    }

    /**
     * Created by Administrator on 2015/3/6.
     */
    private static class BinaryNode<T> {
        T element;
        BinaryNode<T> leftNode;
        BinaryNode<T> rightNode;

        public BinaryNode(T element) {
            this(element, null, null);
        }

        public BinaryNode(T element, BinaryNode<T> rightNode, BinaryNode<T> leftNode) {
            this.element = element;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }


    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Random rand = new Random();
        int tmp = 0;
        for (int i = 0; i < 10; i++) {
            tmp = rand.nextInt(70);
            System.out.print(tmp + " ");
            tree.insert(tmp);
        }
        System.out.println("Pre-order:");
        tree.printTreeByPreorder();
        System.out.println("Post-order:");
        tree.printTreeByPostorder();
        System.out.println("In-order:");
        tree.printTreeByInorder();
        tree.remove(tmp);
        System.out.println("After remove:");
        System.out.println("Pre-order:");
        tree.printTreeByPreorder();
        System.out.println("Post-order:");
        tree.printTreeByPostorder();
        System.out.println("In-order:");
        tree.printTreeByInorder();
    }
}
