public class Tree {

    // Binary Tree class with root as a data member

    public Node r;

    public Tree () {
        r = null;
    }

    // add() method
    public void add (int i) {
        Node node = new Node(i);
        Node Parent = FindParent(i);
        AddChild(node, Parent);
    }

    // find() method
    public boolean find (int i) {
        // find the candidate
        Node closest = FindParent(i);
        if (closest.value == i) {
            return true; // if the candidate has the desired value, return true
        } else {return false;}
    }

    // remove() method
    public void remove (int i) {
        if (find(i)) {
            Node removed = FindParent(i); //find the corresponding node
            if (removed.left == null || removed.right == null) {
                removeSimple(removed); //when at most one child is null
            } else {
                Node current = removed.left;
                while (current.right != null) {
                    current = current.right; //find the new root (biggest in the left subtree)
                }
                removed.value = current.value;
                removeSimple(current);
            }
        }
    }

    public static Node deepest(Node node) {
        // find by doing breadth first search
        ArrayQueue q = new ArrayQueue();
        if (node == null) { return node; } // if node is null
        q.add(node);
        Node current = node; // start from the input node
        while (q.size() > 0) {
            current = q.remove();
            if (current.left != null) {
                q.add(current.left);
            }
            if (current.right != null) {
                q.add(current.right);
            }
        }
        return current; // return the last node (one of the bottom leaves)
    }

    public static int total(Node node) {
        // recursive method
        if (node == null) {
            return 0;
        }
        return node.value + total(node.left) + total(node.right);
    }

    public Node FindParent(int i) {
        Node current = r; Node prev = null;
        while (current != null) {
            prev = current;
            // determine where to go next
            if (i < current.value) {
                current = current.left;
            } else if (i > current.value) {
                current = current.right;
            } else {
                return current;
            }
        }
        return prev;
    }

    public void AddChild(Node node, Node Parent) {
        // add the node as a child unless child = parent
        if (Parent == null) {
            r = node;
        } else if (Parent.value < node.value ) {
            Parent.right = node;
            node.parent = Parent;
        } else if (Parent.value > node.value) {
            Parent.left = node;
            node.parent = Parent;
        }
    }

    public void removeSimple (Node removed) {
        //simple replacement of nodes when parent is removed
        Node child = null; Node Parent = null;
        if (removed.left != null) { // child will replace or it will just be empty if no children
            child = removed.left;
        } else {
            child = removed.right;
        }
        if (removed == r) { //when removing the root
            r = child;
            Parent = null;
        } else {
            if (removed.parent.left == removed) { // swapping
                removed.parent.left = child;
            } else {
                removed.parent.right = child; 
            }
            Parent = removed.parent;
        }
        if (child != null) {child.parent = Parent;}
    }

}