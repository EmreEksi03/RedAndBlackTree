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

    public static void main(String[] args) {
        RedAndBlackTree t = new RedAndBlackTree();
        int[] arr = {1, 4, 6, 3, 5, 7, 8, 2, 9};
        for (int i = 0; i < 9; i++) {
            t.insert(arr[i]);
            System.out.println();
            t.inorderTraversal();
        }
    }
}
