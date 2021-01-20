package critical_connections_in_a_network;

import java.util.ArrayList;
import java.util.List;

public class Solution1_UnionFind {
	/* Union & Find Method, but this will give TLE on leetcode */
	class UnionFind {
		int[] parent;
		int[] rank;
		int count;

		public UnionFind(int n) {
			parent = new int[n];
			rank = new int[n];
			count = n;
			for (int i = 0; i < n; i++)
				parent[i] = i;
		}

		public int find(int n) {
			while (n != parent[n]) {
				parent[n] = parent[parent[n]];
				n = parent[n];
			}
			return n;
		}

		public void union(int a, int b) {
			int rootA = find(a);
			int rootB = find(b);
			if (rootA == rootB) {
				return;
			} else {
				if (rank[rootA] < rank[rootB]) {
					parent[rootA] = rootB;
				} else {
					parent[rootB] = rootA;
					if (rank[rootA] == rank[rootB])
						rank[rootA]++;
				}
			}
			count--;
		}

		public int count() {
			return count;
		}
	}

	public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		List<List<Integer>> ans = new ArrayList<>();
		Solution1_UnionFind obj = new Solution1_UnionFind();
		for (int i = 0; i < connections.size(); i++) {
			UnionFind uf = obj.new UnionFind(n);
			for (int j = 0; j < connections.size(); j++) {
				if (j == i)
					continue;
				uf.union(connections.get(j).get(0), connections.get(j).get(1));
			}
			if (uf.count != 1) {
				ans.add(connections.get(i));
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		int n = 7;
		int[][] arr = new int[][] { { 0, 1 }, { 1, 2 }, { 3, 4 }, { 2, 5 }, { 3, 2 }, { 4, 5 }, { 2, 6 } };
		List<List<Integer>> connections = new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			List<Integer> t = new ArrayList<>();
			t.add(arr[i][0]);
			t.add(arr[i][1]);
			connections.add(t);
		}
		List<List<Integer>> ans = criticalConnections(n, connections);
		System.out.println("heer");
	}
}
