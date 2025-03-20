class Node {
    String value;
    Node next, previous;

    public Node(String v) {
        value = v;
        next = null;
        previous = null;
    }
}

class AnchorNode extends Node {

    public AnchorNode () {
        super("THIS_IS_ANCHOR");
    }
}