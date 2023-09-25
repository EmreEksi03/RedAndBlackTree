public class RedAndBlackTree {
    Node root;
    public RedAndBlackTree(Node root) {
        this.root = root;
        root.setColor(1);
    }
    public void insert(Node node,Node root){
        boolean isLeaf = root.getlChild() == null && root.getrChild() == null;
        if (node.getElement()> root.getElement() && !isLeaf)
            insert(node,root.getrChild());
        else if (node.getElement()< root.getElement() && !isLeaf)
            insert(node,root.getlChild());
        if (root.getElement()> node.getElement())
            root.setrChild(node);
        else if (root.getElement()< node.getElement())
            root.setlChild(node);
        node.setParent(root);
        node.setColor(0);
        uncleIsRed(node);
        uncleIsBlackCase_LR(node);
        uncleIsBlackCase_LL(node);
        uncleIsBlackCase_RR(node);
        uncleIsBlackCase_RL(node);
    }
    public void rightRotation(Node root){
        Node LR = root.getlChild().getrChild();
        Node L = root.getlChild();
        root.setlChild(LR);
        LR.setParent(root);
        L.setrChild(root);
        root.setParent(L);
    }
    public void leftRotation(Node root){
        Node R = root.getrChild();
        Node RL = root.getrChild().getlChild();
        root.setrChild(RL);
        RL.setParent(root);
        R.setlChild(root);
        root.setParent(R);
    }
    public void uncleIsRed(Node node){
        Node uncle;
        Node grandparent = node.getParent().getParent();
        if (grandparent == null)
            return;
        if (node.getParent().getElement() != grandparent.getrChild().getElement())
            uncle = node.getParent().getParent().getrChild();
        else
            uncle = grandparent.getlChild();
        if (uncle == null)
            return;
        if (uncle.getColor() == 0) {
            uncle.setColor(1);
            node.getParent().setColor(1);
            grandparent.setColor(0);
        }
    }
    public void uncleIsBlackCase_LL(Node node){
        Node uncle;
        Node grandparent = node.getParent().getParent();
        if (grandparent == null)
            return;
        if (grandparent !=null &&  node.getParent().getElement() != grandparent.getrChild().getElement())
            uncle = node.getParent().getParent().getrChild();
        else
            uncle = grandparent.getlChild();
        if (uncle == null)
            return;
        if (uncle.getColor() == 1 && node.getElement() == grandparent.getlChild().getlChild().getElement()){
            rightRotation(grandparent);
            int temp = node.getParent().getColor();
            node.getParent().setColor(grandparent.getColor());
            grandparent.setColor(temp);
        }
    }
    public void uncleIsBlackCase_LR(Node node){
        Node uncle;
        Node grandparent = node.getParent().getParent();
        if (grandparent == null)
            return;
        if (node.getParent().getElement() != grandparent.getrChild().getElement())
            uncle = node.getParent().getParent().getrChild();
        else
            uncle = grandparent.getlChild();
        if (uncle == null)
            return;
        if (uncle.getColor() == 1 && node.getElement() == grandparent.getlChild().getrChild().getElement()){
            leftRotation(node.getParent());
            uncleIsBlackCase_LL(node.getParent());
        }
    }
    public void uncleIsBlackCase_RR(Node node){
        Node uncle;
        Node grandparent = node.getParent().getParent();
        if (grandparent == null)
            return;
        if (node.getParent().getElement() != grandparent.getrChild().getElement())
            uncle = node.getParent().getParent().getrChild();
        else
            uncle = grandparent.getlChild();
        if (uncle == null)
            return;
        if (uncle.getColor() == 1 && node.getElement() == grandparent.getrChild().getrChild().getElement()){
            leftRotation(grandparent);
            int temp = node.getParent().getColor();
            node.getParent().setColor(grandparent.getColor());
            grandparent.setColor(temp);
        }
    }
    public void uncleIsBlackCase_RL(Node node){
        Node uncle;
        Node grandparent = node.getParent().getParent();
        if (grandparent == null)
            return;
        if (node.getParent().getElement() != grandparent.getrChild().getElement())
            uncle = node.getParent().getParent().getrChild();
        else
            uncle = grandparent.getlChild();
        if (uncle == null)
            return;
        if (uncle.getColor() == 1 && node.getElement() == grandparent.getrChild().getlChild().getElement()){
            leftRotation(grandparent);
            rightRotation(node.getParent());
            uncleIsBlackCase_RR(node.getParent());
        }
    }
    public void inOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.getlChild());
        System.out.print(node.getElement() + " ");
        inOrderTraversal(node.getrChild());
    }
    public static void main(String[] args) {
        Node root = new Node(null, null, null, 1, 10);
        RedAndBlackTree rbTree = new RedAndBlackTree(root);

        rbTree.insert(new Node(7), rbTree.root);
        rbTree.insert(new Node(11), rbTree.root);
        rbTree.insert(new Node(2), rbTree.root);
        rbTree.insert(new Node(43), rbTree.root);
        rbTree.insert(new Node(2), rbTree.root);
        rbTree.insert(new Node(55), rbTree.root);

        System.out.println("In-order traversal of the Red-Black Tree:");
        rbTree.inOrderTraversal(rbTree.root);
    }
}
