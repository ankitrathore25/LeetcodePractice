# Union & Find
[Recommended Reading here](https://www.cs.princeton.edu/courses/archive/spr09/cos226/lectures/01UnionFind.pdf)  
[Recommended Video Tutorial](https://www.youtube.com/watch?v=ID00PMy0-vE)  

```
/*
 * Union & Rank method with path compression
 */
class UnionFind {

	/*
	 * I can use hashmap when there is unique nodes. If multiple nodes have same
	 * name(or number) then we will use array and work using its index as index will
	 * be continuous number from 0-n-1
	 */
	Map<Integer, Integer> parent;
	Map<Integer, Integer> rank;
	int count;

	public UnionFind(int[] sourceNodes, int[][] allowedSwaps) {
		int n = sourceNodes.length;
		parent = new HashMap<>();
		rank = new HashMap<>();
		count = n;

		for (int i = 0; i < n; i++) {
			parent.put(sourceNodes[i], sourceNodes[i]);
			rank.put(sourceNodes[i], 0);
		}

		for (int i = 0; i < allowedSwaps.length; i++) {
			union(sourceNodes[allowedSwaps[i][0]], sourceNodes[allowedSwaps[i][1]]);
		}
	}

	public boolean isConnected(int a, int b) {
		return findRoot(a) == findRoot(b);
	}

	public void union(int a, int b) {
		int rootA = findRoot(a);
		int rootB = findRoot(b);

		if (rank.get(rootA) < rank.get(rootB)) {
			parent.put(rootA, rootB);
		} else {
			parent.put(rootB, rootA);
			if (rank.get(rootA) == rank.get(rootB)) {
				rank.put(rootA, rank.get(rootA) + 1);
			}
		}
		count--;
	}

	public int count() {
		return count;
	}

	public Map<Integer, Integer> getParentMap() {
		return parent;
	}

	public int findRoot(int n) {
		while (parent.get(n) != n) {
			parent.put(n, parent.get(parent.get(n)));
			n = parent.get(n);
		}
		return n;
	}
}


```
> Note to self: I was trying to understand how we will get the final corrected parent map where level of tree is 1 (i.e. root node and its leaf nodes). After the last union there might be a situation where tree level is more. Answer is we use getRoot(n) function to get the value of parent of a node which eventually correct the whole tree.

**Another version**

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
- [minimize hamming distance after swap operations](https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/) (**Try this question**)  

### Resources
- [Video lectures](https://www.youtube.com/playlist?list=PL2q4fbVm1Ik4JdzE2Bv_UUGBz0TXEIrai)
- [Video By Tushar Roy](https://www.youtube.com/watch?v=ID00PMy0-vE)
- [Reading](https://medium.com/@RamkrishnaKulka/disjoint-set-union-union-find-the-same-blood-type-e67c51b1d2)
