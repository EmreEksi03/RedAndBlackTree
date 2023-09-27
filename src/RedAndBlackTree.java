import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RedAndBlackTree {
    private Node root;
    private static final int RED = 0;
    private static final int BLACK = 1;

    public RedAndBlackTree() {
        root = null;
    }

    public void insert(int element) {
        Node newNode = new Node(element);
        if (root == null) {
            root = newNode;
            root.setColor(BLACK);
        } else {
            root = insertNode(root, newNode);
        }
    }

    private Node insertNode(Node root, Node newNode) {
        if (root == null) {
            return newNode;
        }

        if (newNode.getElement() < root.getElement()) {
            root.setlChild(insertNode(root.getlChild(), newNode));
            root.getlChild().setParent(root);
        } else if (newNode.getElement() > root.getElement()) {
            root.setrChild(insertNode(root.getrChild(), newNode));
            root.getrChild().setParent(root);
        }

        // Perform Red-Black Tree balancing
        if (isRed(root.getrChild()) && !isRed(root.getlChild())) {
            root = rotateLeft(root);
        }
        if (isRed(root.getlChild()) && isRed(root.getlChild().getlChild())) {
            root = rotateRight(root);
        }
        if (isRed(root.getlChild()) && isRed(root.getrChild())) {
            flipColors(root);
        }

        return root;
    }

    private Node rotateLeft(Node node) {
        Node x = node.getrChild();
        node.setrChild(x.getlChild());
        x.setlChild(node);
        x.setColor(node.getColor());
        node.setColor(RED);
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.getlChild();
        node.setlChild(x.getrChild());
        x.setrChild(node);
        x.setColor(node.getColor());
        node.setColor(RED);
        return x;
    }

    private void flipColors(Node node) {
        node.setColor(RED);
        node.getlChild().setColor(BLACK);
        node.getrChild().setColor(BLACK);
    }

    private boolean isRed(Node node) {
        return node != null && node.getColor() == RED;
    }

    public void inorderTraversal() {
        inorderTraversalHelper(root);
    }

    private void inorderTraversalHelper(Node node) {
        if (node != null) {
            inorderTraversalHelper(node.getlChild());
            System.out.printf("%d ", node.getElement());
            inorderTraversalHelper(node.getrChild());
        }
    }

    public int[] inorderTraversalToArray() {
        List<Integer> resultList = new ArrayList<>();
        inorderTraversalToArrayHelper(root, resultList);

        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            resultArray[i] = resultList.get(i);
        }

        return resultArray;
    }

    private void inorderTraversalToArrayHelper(Node node, List<Integer> resultList) {
        if (node != null) {
            inorderTraversalToArrayHelper(node.getlChild(), resultList);
            resultList.add(node.getElement());
            inorderTraversalToArrayHelper(node.getrChild(), resultList);
        }
    }

    public static void main(String[] args) {
        RedAndBlackTree t = new RedAndBlackTree();

        int[] arr = {1, 4, 6, 3, 5, 7, 8, 2, 9};

        // Insert elements into the Red-Black Tree
        for (int i = 0; i < arr.length; i++) {
            t.insert(arr[i]);
        }

        // Perform an inorder traversal and print the sorted order
        int[] result = t.inorderTraversalToArray();
        System.out.print("Inorder Traversal: ");
        for (int element : result) {
            System.out.print(element + " ");
        }
        System.out.println();

        // Check if the order is maintained
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        boolean orderIsKept = Arrays.equals(result, expected);
        System.out.println("Order is maintained: " + orderIsKept);

        // Test additional operations
        System.out.println("Additional Test Operations:");
        System.out.println("----------------------------");

        // Insert more elements
        int[] additionalElements = {10, 0, -1};
        for (int element : additionalElements) {
            t.insert(element);
        }

        // Perform another inorder traversal and print the sorted order
        result = t.inorderTraversalToArray();
        System.out.print("Inorder Traversal after adding more elements: ");
        for (int element : result) {
            System.out.print(element + " ");
        }
        System.out.println();

        // Check if the order is maintained
        int[] updatedExpected = {-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        orderIsKept = Arrays.equals(result, updatedExpected);
        System.out.println("Order is maintained after adding more elements: " + orderIsKept);
    }

}
