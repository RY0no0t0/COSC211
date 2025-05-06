public class StackNode extends Node {

    // Additional data members
    public int start;
    public int end;
    public StackNode bottom;

    public StackNode (int i, int j) {
        super();
        start = i;
        end = j;
        bottom = null;
    }
}