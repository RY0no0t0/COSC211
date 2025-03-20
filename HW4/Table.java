class Table {
    private int size;
    private int hashFn;
    private List[] table;

    public Table(int tableSize, int whichHash) {
        size = tableSize;
        table = new List[size];
        for (int i = 0; i < size; i++)
            table[i] = new List();
        hashFn = whichHash;
    }

    private int hash1(String s) {
        // INDIVIDUAL ASSIGNMENT: IMPLEMENT THIS
        return 0; // for testing the rest during collaboration
    }

    private int hash2(String s) {
        // INDIVIDUAL ASSIGNMENT: IMPLEMENT THIS
        return 0; // for testing the rest during collaboration
    }

    private int hash(String s) {
        int hashCode = 0;
        switch (hashFn) {
            case 1: hashCode = hash1(s); break;
            case 2: hashCode = hash2(s); break;
        }
        return hashCode;
    }

    public void add(String s) {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
        table[hash(s)].add(s);
    }

    public boolean find(String s) {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
        
    }

    public void remove(String s) {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
    }

    public int maxInSlot() {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
    }
}