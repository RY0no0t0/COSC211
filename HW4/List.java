class List {
    private AnchorNode first;
    private Node last;
    private int count;

    public List() {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
        count = 0;
        first = new AnchorNode();
        last = first;
    }

    public int size() {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
        return count;
    }

    public void add(String toAdd) {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
        Node Adding = new Node(toAdd);
        last.next = Adding;
        Adding.previous = last;
        last = Adding;
        count++;
    }

    public Node find(String toFind) {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
        Node current = first.next;
        while (current != null) {
            // System.out.println(current.value);
            if (current.value.equals(toFind)) {
                return current;
            } else {
                current = current.next;
            }
        }
        return null;

    }

    public void remove(String toRemove) {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
        Node Removing = find(toRemove);
        if (Removing != null) {
            Removing.previous.next = Removing.next;
            if (Removing.next != null) {
                Removing.next.previous =Removing.previous;
            } else {
                last = Removing.previous;
            }
            count--;
        }
    }
}