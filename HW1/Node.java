public class Node {

    private Node prev;
    private Node next;
    private int data;

    public Node () {
        prev = null;
        next = null;
    }

    public Node (int num) {
        prev = null;
        next = null;
        data = num;
    }

    public void setNext (Node another) {
        next = another;
    }

    public void setPrev (Node another) {
        prev = another;
    }

    public void setValue (int num) {
        data = num;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev(){
        return prev;
    }

    public int getValue(){
        return data;
    }

}