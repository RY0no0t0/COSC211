public class List {

    private Node _head;
    private Node _tail;
    private int _length;
  
    public List () {
	    _head   = null;
        _tail = null;
	    _length = 0;
    }

    public void insert (int value) {

        Node current = new Node(value);
    
        if (_length == 0) {;
            _head = current;
            _tail = current;
        } else {
            _tail.setNext(current);
            current.setPrev(_tail);
            _tail = current;
        }
        
        _length += 1;

    }

    public void remove(int value) {

        Node current = find(value);
        
        if (current != null){
            if (_length == 1) {
                _head = null;
                _tail = null;
            } 
            else if (current == _head) {
                _head = current.getNext();
                _head.setPrev(null);
            } else if (current == _tail) {
                _tail = current.getPrev();
                _tail.setNext(null);
            }else {
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
            }

            _length -= 1;
        }
    }

    public int pop() {
        Node current = _head;
        if (_length == 1) {
            _head = null;
            _tail = null;
        } else {
            _head = current.getNext();
            _head.setPrev(null);
        }
        _length -= 1;
        return current.getValue();
    }

    public void set (int index, int value) {
	    Node current = walkTo(index);
	    current.setValue(value);
    }

    public int get (int index) {
	    Node current = walkTo(index);
	    return current.getValue();
    }

    public int length () {
	    return _length;
    }

    private Node walkTo (int index) {

	    if (! (0 <= index && index < _length)) {
	        throw new IndexOutOfBoundsException(Integer.toString(index));
	    }

	    Node current = _head;
	    for (int i = 0; i < index; i += 1) {
	        current = current.getNext();
	    }
	    return current;
    }

    public Node find(int value) {
        Node current = _head;
        while (current != null){
            if (current.getValue() == value) {
                return current;
            }
            current = current.getNext();
        }

        return null;
    }

    public boolean exist(int value) {
        return (find(value)!=null);
    }

    public void print() {
        Node current = _head;
        System.out.print("{");
        while (current != null) {
            System.out.print(current.getValue()+", ");
            current = current.getNext();
        }
        System.out.println("}");
    }
}

