public class Stack implements Data  {

    // Data members
    public int size;
    public StackNode top;

    public Stack () {
        size = 0;
        top = null;
    }

    // Constructor
    public Stack (int i)  {
        size = 0;
        top = null;
    }

    public void initialize (int i) {
        for (int k = 0; k < i; k++) {
            for (int j = 0; j < i; j++) {
                StackNode node = new StackNode(k, j);
                push(node);
            }
        }
    }

    public Node at (int i, int j) {
        Stack temp = new Stack();
        StackNode current = this.pop();

        while ((current.start != i || current.end != j) && current != null) {
            temp.push(current);
            current = this.pop();
        }

        StackNode toReturn = current;

        if (current != null) {
            this.push(current);
        }
        if (temp.size != 0) {
            current = temp.pop();
            while (current != null) {
                this.push(current);
                current = temp.pop();
            }
        }

        return toReturn;
    }

    public void RemoveIn (int i) {
        Stack temp = new Stack();
        StackNode current = this.pop();

        int num = i - i%6;

        while (current != null) {
            if (current.end == num || current.end == num+1 || current.end == num+2 || current.end == num+3 || current.end == num+4
            || current.end == num+5) {
                current.exist = false;
                current.tonal = 0;
                current.resolution = 0;
                current.prob = 0;
            }
            temp.push(current);
            current = this.pop();
        }

        if (temp.size != 0) {
            current = temp.pop();
            while (current != null) {
                this.push(current);
                current = temp.pop();
            }
        }

    }

    public void push (StackNode n) {
        n.bottom = top;
        top = n;
        size++;
    }

    public StackNode pop() {
        StackNode temp = top;
        if (size != 0) {
            top = temp.bottom;
            size--;
        }
        return temp;
    }

}