class Graph {
    int n;
    List[] _array;

    public Graph (int numVertices) {
        n = numVertices;
        _array = new List[n];
        
        for (int i = 0; i<n; i++) {
            _array[i] = new List();
        }
    }

    public void addEdge(int i, int j) {
        _array[i].insert(j);
    }

    public void removeEdge(int i, int j) {
        _array[i].remove(j);
    }

    public boolean hasEdge(int i, int j) {
        return _array[i].exist(j);
    }

    public List outEdges(int i) {
        return _array[i];
    }

    public List inEdges(int i) {
        List results = new List();
        for (int j = 0; j<n; j++) {
            if(_array[j].exist(i)){ results.insert(j); }
        }
        return results;
    }

    boolean reachable(int source, int destination) {
        List q = new List();
        boolean[] seen = new boolean[n];
        q.insert(source);
        seen[source] = true;

        while(q.length() != 0){
            int i = q.pop();
            List checking = outEdges(i);
            for (int j = 0; j < checking.length(); j++) {
                int k = checking.get(j);
                if (k==destination){
                    return true;
                } else if (!seen[k]) {
                    q.insert(k);
                    seen[k] = true;
                }
            }
        }
        return false;
    }
}
