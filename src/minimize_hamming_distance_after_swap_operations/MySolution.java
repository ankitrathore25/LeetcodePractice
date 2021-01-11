package minimize_hamming_distance_after_swap_operations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MySolution {

	class UnionFind {

		/*
		 * I can use hashmap when there is unique nodes. If multiple nodes have same
		 * name(or number) then we will use array and work using its index as index will
		 * be continuous number from 0-n-1
		 */
		int[] parent;
		int[] rank;
		int count;

		public UnionFind(int n) {
			parent = new int[n];
			rank = new int[n];
			count = n;
			Arrays.fill(rank, 0);
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public boolean isConnected(int a, int b) {
			return findRoot(a) == findRoot(b);
		}

		public void union(int a, int b) {
			int rootA = findRoot(a);
			int rootB = findRoot(b);

			if (rank[rootA] < rank[rootB]) {
				parent[rootA] = rootB;
			} else {
				parent[rootB] = rootA;
				if (rank[rootA] == rank[rootB]) {
					rank[rootA]++;
				}
			}
			count--;
		}

		public int count() {
			return count;
		}

		public int[] getParentMap() {
			return parent;
		}

		public int findRoot(int n) {
			while (parent[n] != n) {
				parent[n] = parent[parent[n]];
				n = parent[n];
			}
			return n;
		}
	}

	public static int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
		MySolution obj = new MySolution(); // remove this
		int n = source.length;
		UnionFind uf = obj.new UnionFind(n); // and change this

		for (int i = 0; i < allowedSwaps.length; i++) {
			uf.union(allowedSwaps[i][0], allowedSwaps[i][1]);
		}

		int same = 0;
		Map<Integer, Map<Integer, Integer>> group = new HashMap<>();
		for (int sourceIndex = 0; sourceIndex < n; sourceIndex++) {
			int srcVal = source[sourceIndex];
			int rootIndex = uf.findRoot(sourceIndex);
			int rootVal = source[rootIndex];
			if (!group.containsKey(rootVal)) {
				group.put(rootVal, new HashMap<>());
			}
			Map<Integer, Integer> t = group.get(rootVal);
			t.put(srcVal, t.getOrDefault(srcVal, 0) + 1);
		}
		for (int i = 0; i < n; i++) {
			int s = source[uf.findRoot(i)];
			int t = target[i];
			if (group.containsKey(s) && group.get(s).containsKey(t) && group.get(s).get(t) > 0) {
				Map<Integer, Integer> temp = group.get(s);
				temp.put(t, temp.get(t) - 1);
				same++;
			} else {
				continue;
			}
		}
		return n - same;
	}

	public static void main(String[] args) {
		/* Test Case */
		int[] source = new int[] { 77, 86, 78, 34, 39, 50, 3, 92, 77, 66, 48, 11, 42, 91, 36, 95, 90, 93, 12, 51, 46,
				49, 85, 59, 48, 58, 34, 6, 31, 35, 67, 94, 97, 18, 60, 37, 22, 77, 41, 60, 42, 52, 92, 74, 82, 97, 52,
				93, 29, 7, 12, 45, 45, 25, 13, 11, 90, 11, 73, 24, 32, 90, 99, 43, 40, 40, 66, 13, 99, 48, 49, 7, 94, 8,
				68, 10, 34, 15, 56, 51, 28, 12, 76, 50, 82, 44, 8, 25, 77, 5, 90 };
		int[] target = new int[] { 77, 86, 62, 97, 93, 55, 9, 92, 21, 29, 48, 48, 84, 74, 36, 43, 30, 76, 82, 51, 38,
				68, 25, 13, 11, 81, 34, 6, 4, 100, 49, 68, 20, 18, 60, 37, 25, 77, 81, 88, 63, 52, 84, 74, 43, 87, 47,
				98, 15, 29, 33, 56, 56, 25, 97, 68, 52, 11, 25, 24, 17, 26, 30, 8, 74, 47, 64, 13, 15, 48, 6, 8, 49, 72,
				17, 62, 10, 59, 56, 18, 45, 89, 19, 90, 1, 44, 21, 21, 11, 66, 57 };
		int[][] allowedSwaps = new int[][] { { 24, 90 }, { 87, 86 }, { 20, 86 }, { 46, 27 }, { 75, 55 }, { 81, 19 },
				{ 17, 1 }, { 35, 34 }, { 74, 47 }, { 86, 38 }, { 55, 54 }, { 1, 11 }, { 67, 0 }, { 84, 65 }, { 45, 75 },
				{ 59, 53 }, { 64, 17 }, { 36, 78 }, { 22, 11 }, { 66, 61 }, { 87, 42 }, { 29, 47 }, { 20, 18 },
				{ 71, 4 }, { 68, 36 }, { 49, 25 }, { 62, 46 }, { 4, 82 }, { 51, 77 }, { 3, 85 }, { 34, 64 }, { 53, 23 },
				{ 48, 53 }, { 87, 31 }, { 17, 80 }, { 75, 19 }, { 46, 35 }, { 25, 86 }, { 23, 13 }, { 21, 65 },
				{ 51, 10 }, { 51, 59 }, { 84, 49 }, { 16, 60 }, { 42, 20 }, { 69, 38 }, { 32, 61 }, { 54, 62 },
				{ 34, 48 }, { 37, 23 }, { 14, 63 }, { 79, 39 }, { 56, 74 }, { 15, 67 }, { 26, 22 }, { 38, 41 },
				{ 74, 79 }, { 69, 33 }, { 11, 52 }, { 68, 86 }, { 46, 43 }, { 76, 6 }, { 57, 11 }, { 14, 22 },
				{ 46, 21 }, { 28, 63 }, { 51, 9 }, { 55, 74 }, { 65, 39 }, { 29, 62 }, { 53, 34 }, { 30, 61 },
				{ 24, 17 }, { 43, 41 }, { 47, 71 }, { 67, 4 }, { 82, 18 }, { 69, 86 }, { 71, 21 }, { 82, 53 },
				{ 31, 20 }, { 67, 40 }, { 77, 88 }, { 64, 89 }, { 2, 43 }, { 11, 66 }, { 44, 27 }, { 49, 33 },
				{ 48, 60 }, { 7, 31 }, { 69, 82 }, { 26, 80 }, { 88, 16 }, { 59, 20 }, { 63, 51 }, { 6, 4 }, { 80, 74 },
				{ 67, 72 }, { 36, 31 }, { 87, 61 }, { 39, 8 }, { 3, 49 }, { 58, 89 }, { 32, 42 }, { 48, 81 },
				{ 54, 84 }, { 42, 16 }, { 72, 87 }, { 5, 9 }, { 26, 64 }, { 57, 83 }, { 34, 87 }, { 23, 78 },
				{ 33, 63 }, { 47, 86 }, { 38, 75 }, { 34, 43 }, { 55, 16 }, { 63, 81 }, { 65, 90 }, { 29, 78 },
				{ 27, 63 }, { 32, 72 }, { 39, 40 }, { 34, 22 }, { 22, 87 }, { 43, 28 }, { 21, 25 }, { 46, 89 },
				{ 75, 54 }, { 84, 57 }, { 13, 43 }, { 57, 90 }, { 68, 60 }, { 29, 24 }, { 21, 32 }, { 6, 0 },
				{ 63, 77 }, { 54, 4 }, { 86, 51 }, { 79, 66 }, { 8, 70 }, { 56, 64 }, { 43, 83 }, { 85, 88 },
				{ 49, 79 }, { 46, 60 }, { 3, 25 }, { 21, 54 }, { 43, 60 }, { 55, 23 }, { 48, 79 }, { 59, 8 },
				{ 30, 56 }, { 2, 9 }, { 70, 51 }, { 14, 89 }, { 84, 34 }, { 44, 0 }, { 42, 50 }, { 42, 65 }, { 28, 39 },
				{ 37, 6 }, { 31, 55 }, { 12, 21 }, { 2, 16 }, { 79, 44 }, { 54, 86 }, { 17, 32 }, { 58, 86 },
				{ 59, 36 }, { 18, 39 }, { 58, 46 }, { 55, 42 }, { 43, 39 }, { 22, 35 }, { 67, 39 }, { 4, 39 },
				{ 1, 22 }, { 22, 45 }, { 76, 39 }, { 84, 52 }, { 63, 40 }, { 56, 68 }, { 15, 58 }, { 32, 25 },
				{ 31, 33 }, { 6, 79 } };

		System.out.println(minimumHammingDistance(source, target, allowedSwaps));
	}

}
