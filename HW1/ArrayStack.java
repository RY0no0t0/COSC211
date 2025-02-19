public class ArrayStack implements Stack{

    private int[] _array;
    private int _head;
    private int _tail;
    private int _size;

    public ArrayStack () {
        _array = new int[10];
        _head = 0;
        _tail = 0;
        _size = 0;
    }

    public int size() {
        return _size;
    }

    public void push (int toPush) {
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

    public int pop() {

        int toPop = _array[idx(_tail)];
        _size--;
        if (_size!=0) {
            _tail--;
        }
        
        if ((_size <= (_array.length/3)) && (_size > 10)) {
            shrink();
        }

        return toPop;
    }

    public void insertBottom (int toInsert) {
        if (_size !=0){
            _head--;
            if (idx(_head) == idx(_tail)) {
                resize();
                _head--;
            }
        }

        _array[idx(_head)] = toInsert;
        _size++;
    }

    public int extractBottom () {
        int toExtract = _array[idx(_head)];
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
            System.out.print(_array[idx(_head+i)]+", ");
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
        int[] temp = new int[2*_array.length];
        System.arraycopy(_array, idx(_head), temp, 0, _array.length - idx(_head));
        System.arraycopy(_array, 0, temp, _array.length - idx(_head), idx(_head));

        _head = 0;
        _tail = _size-1;
        _array = temp;
    }

    public void shrink () {
        int[] temp = new int[_array.length/2];
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