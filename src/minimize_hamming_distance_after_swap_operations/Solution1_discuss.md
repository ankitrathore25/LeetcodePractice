[Link]( https://leetcode.com/problems/minimize-hamming-distance-after-swap-operations/discuss/1009771/Java-Detailed-Explanation-Union-Find-+-Greedy)

```
public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        
	int N = source.length;

	int[] UNION = new int[N];
	for (int i = 0; i < N; ++i) UNION[i] = i;

	// union-find
	for (int[] swap : allowedSwaps) {

		int indexA = swap[0], indexB = swap[1];
		int parentA = find(UNION, indexA), parentB = find(UNION, indexB);
		// union A and B
		if (parentA != parentB) UNION[parentA] = parentB;
	}

	// count element for each union-find group -> key: root of each union group, value: map
	Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
	for (int i = 0; i < N; ++i) {
		int num = source[i];
		int root = find(UNION, i);

		map.putIfAbsent(root, new HashMap<>());
		Map<Integer, Integer> store = map.get(root);
		store.put(num, store.getOrDefault(num, 0) + 1);
	}

	// greedy fit the target, if not, diff++
	int res = 0;
	for (int i = 0; i < N; ++i) {
		int num = target[i];
		int root = find(UNION, i);

		Map<Integer, Integer> store = map.get(root);

		if (store.getOrDefault(num, 0) == 0) res++;
		else store.put(num, store.get(num) - 1);
	}

	return res;
}

// union-find helper
private int find(int[] UNION, int node) {
	if (UNION[node] == node) return node;
	return UNION[node] = find(UNION, UNION[node]);
}
```