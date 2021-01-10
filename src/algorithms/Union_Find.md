# Union & Find


```
class UnionFind {
    private int count = 0;
    private int[] parent, rank;
    
    public UnionFind(int n) {
        count = n;
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    public int find(int p) {
    	while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
    }
    
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        if (rank[rootQ] > rank[rootP]) {
            parent[rootP] = rootQ;
        }
        else {
            parent[rootQ] = rootP;
            if (rank[rootP] == rank[rootQ]) {
                rank[rootP]++;
            }
        }
        count--;
    }
    
    public int count() {
        return count;
    }
}
```


### practice questions
- [robot return to origin](https://leetcode.com/problems/robot-return-to-origin/)
- [sentence similarity](https://leetcode.com/problems/sentence-similarity/)
- [sentence similarity-ii](https://leetcode.com/problems/sentence-similarity-ii/)
- [number of provinces](https://leetcode.com/problems/number-of-provinces)
- [graph valid tree](https://leetcode.com/problems/graph-valid-tree/)
- [number of connected components in an undirected graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/)
- [the earliest moment when everyone become friends](https://leetcode.com/problems/the-earliest-moment-when-everyone-become-friends/)
- [Video lectures](https://www.youtube.com/playlist?list=PL2q4fbVm1Ik4JdzE2Bv_UUGBz0TXEIrai)
- [Video By Tushar Roy](https://www.youtube.com/watch?v=ID00PMy0-vE)
- [Reading](https://medium.com/@RamkrishnaKulka/disjoint-set-union-union-find-the-same-blood-type-e67c51b1d2)
