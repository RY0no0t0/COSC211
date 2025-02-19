public class ListStack implements Stack {

    private int _size;
    private Node _head;
    private Node _tail;

    public ListStack () {
        _size = 0;
        _head = new Node();
        _tail = new Node();

        _head.setNext(_tail);
        _tail.setPrev(_head);
    }

    public int size() {
        return _size;
    }

    public void push (int toPush) {
        Node current = new Node(toPush);
        current.setNext(_tail);
        current.setPrev(_tail.getPrev());
        _tail.getPrev().setNext(current);
        _tail.setPrev(current);

        _size++;
    }

    public int pop() {
        Node current = _tail.getPrev();
        current.getPrev().setNext(_tail);
        _tail.setPrev(current.getPrev());

        // current.setNext(null);
        // current.setPrev(null);

        _size--;

        return current.getValue();
    }

    public void insertBottom(int toInsert) {
        Node current = new Node(toInsert);

        current.setPrev(_head);
        current.setNext(_head.getNext());
        _head.getNext().setPrev(current);
        _head.setNext(current);

        _size++;
    }
    public int extractBottom() {
        Node current = _head.getNext();
        current.getNext().setPrev(_head);
        _head.setNext(current.getNext());

        _size--;

        return current.getValue();
    }

    public void print(){
        System.out.print("[");
        Node current = _head;

        for (int i = 0; i<_size; i++) {
            current = current.getNext();
            System.out.print(current.getValue()+", ");
        }

        System.out.println("]");
    } // actually just for debugging
}
