public class Node {
    private Node parent;
    private Node lChild;
    private Node rChild;
    private int color;
    private int element;
    public Node(Node parent, Node lChild, Node rChild,int color,int element) {
        this.parent = parent;
        this.lChild = lChild;
        this.rChild = rChild;
        this.color = color;
        this.element = element;
    }
    public Node() {
    }
    public Node(int element) {
        this.element = element;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getlChild() {
        return lChild;
    }

    public void setlChild(Node lChild) {
        this.lChild = lChild;
    }

    public Node getrChild() {
        return rChild;
    }

    public void setrChild(Node rChild) {
        this.rChild = rChild;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }
}
