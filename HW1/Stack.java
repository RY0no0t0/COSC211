public interface Stack {
    public int size();
    public void push (int toPush);
    public int pop();
    public void insertBottom(int toInsert);
    public int extractBottom();
    public void print(); // actually just for debugging
}