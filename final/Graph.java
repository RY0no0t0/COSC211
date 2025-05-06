public class Graph implements Data{

    // data members
    public Node[][] matrix;
    public int size;

    // Constructors
    public Graph (int i) {
        matrix = new Node[i][i];
        size = i;
    }

    public void initialize () {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = new Node();
            }
        }
    } 

    public Node at (int i, int j) {
        return matrix[i][j];
    }

    public void RemoveIn (int i) {
        int rownum = i - i%6;
        for (int k = 0; k < 6; k++) {
            for (int j = 0; j < size; j++) {
                matrix[j][k+rownum].exist = false;
                matrix[j][k+rownum].tonal = 0;
                matrix[j][k+rownum].resolution = 0;
                matrix[j][k+rownum].prob = 0;
            }
        }
    }
}