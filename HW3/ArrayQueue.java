public class ArrayQueue {

    // Queue class to use for breadth first search in deepest() method.

    private Node[] _array;
    private int _head;
    private int _tail;
    private int _size;

    public ArrayQueue () {
        _array = new Node[10];
        _head = 0;
        _tail = 0;
        _size = 0;
    }

    public int size() {
        return _size;
    }

    public void add (Node toPush) {
        if (_size!=0) {
            _tail++;
            if (idx(_tail) == idx(_head)) {
                resize();
                _tail++;
            }
        }
        _array[idx(_tail)] = toPush;
        _size++;
    }

    public Node remove () {
        Node toExtract = _array[idx(_head)];
        _size--;

        if (_size != 0) {
            _head++;
        }

        if ((_size <= (_array.length/3)) && (_size > 10)) {
            shrink();
        }

        return toExtract;
    }

    public void print() {
        System.out.print("[");
        for (int i=0; i<_size; i++) {
            System.out.print(_array[idx(_head+i)].value+", ");
        }
        System.out.println("]");
    } 

    public int idx(int num) {
        if (num >= 0) {
            return num % _array.length;
        } else {
            return (num % _array.length) + _array.length;
        }
    }

    public void resize () {
        Node[] temp = new Node[2*_array.length];
        System.arraycopy(_array, idx(_head), temp, 0, _array.length - idx(_head));
        System.arraycopy(_array, 0, temp, _array.length - idx(_head), idx(_head));

        _head = 0;
        _tail = _size-1;
        _array = temp;
    }

    public void shrink () {
        Node[] temp = new Node[_array.length/2];
        if (idx(_tail) > idx(_head)) {
            System.arraycopy(_array, idx(_head), temp, 0, _size);
        } else {
            System.arraycopy(_array, idx(_head), temp, 0, _array.length-idx(_head));
            System.arraycopy(_array, 0, temp, _array.length - idx(_head), idx(_tail)+1);
        }

        _head = 0;
        _tail = _size-1;
        _array = temp;
    }
}