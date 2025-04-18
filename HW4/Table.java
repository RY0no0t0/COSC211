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
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            if (s.charAt(j) != 'e'){
                i += (s.charAt(j))*(j+1)*(j+1);
            }
        }
        //System.out.println(i+" "+i%100);
        return i % 100;
        // return 0; // for testing the rest during collaboration
    }

    private int hash2(String s) {
        // INDIVIDUAL ASSIGNMENT: IMPLEMENT THIS
        float i = 0;
        for (int j = 0; j < s.length(); j++) {
            i += (s.charAt(j)-48)*j*j;
            //System.out.println("oh "+i);
        }
        //System.out.println(i+" "+i%100);
        return ((int)i/s.length())%100;
        // return 0; // for testing the rest during collaboration
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
        int i = 0;
        while (i < size) {
            if (table[i].find(s)!=null) {
                return true;
            }
            i++;
        }
        return false;
    }

    public void remove(String s) {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
        int i = 0;
        while (i < size) {
            table[i].remove(s);
            i++;
        }
    }

    public int maxInSlot() {
        // COLLABORATIVE ASSIGNMENT: IMPLEMENT THIS
        int maxnum = 0;
        int i = 0;
        while (i<size) {
            if (table[i].size() > maxnum) {
                maxnum = table[i].size();
            }
            i++;
        }
        return maxnum;
    }
}