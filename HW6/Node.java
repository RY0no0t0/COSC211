public class Node {

    private Node _next;
    private Node _prev;
    private int   _value;

    public Node (int value) {
	_next = null;
    _prev = null;
	_value = value;
    }

    public Node getNext () {
	    return _next;
    }

    public void setNext (Node next) {
	    _next = next;
    }

    public Node getPrev () {
        return _prev;
    }

    public void setPrev (Node prev) {
        _prev = prev;
    }

    public int getValue () {
	return _value;
    }

    public void setValue (int value) {
	_value = value;
    }

} // class NiceLink
