package critical_connections_in_a_network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2_Tarjans_Algorithm {
//https://leetcode.com/problems/critical-connections-in-a-network/discuss/399827/Java-DFS-Solution-similar-to-Tarjan-maybe-easier-to-understand
	// https://www.youtube.com/watch?v=RYaakWv5m6o
	private static int T = 0;

	private static int dfs(int n, List[] graph, int[] timestamp, int i, int parent, List<List<Integer>> criticalConns) {
		if (timestamp[i] != 0)
			return timestamp[i];
		timestamp[i] = T++;

		int minTimestamp = Integer.MAX_VALUE;
		for (int neighbor : (List<Integer>) graph[i]) {
			if (neighbor == parent)
				continue; // no need to check the parent
			int neighborTimestamp = dfs(n, graph, timestamp, neighbor, i, criticalConns);
			minTimestamp = Math.min(minTimestamp, neighborTimestamp);
		}

		if (minTimestamp >= timestamp[i]) {
			if (parent >= 0)
				criticalConns.add(Arrays.asList(parent, i));
		}
		return Math.min(timestamp[i], minTimestamp);
	}

	public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		List<List<Integer>> criticalConns = new ArrayList<>();

		List[] graph = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for (List<Integer> conn : connections) {
			graph[conn.get(0)].add(conn.get(1));
			graph[conn.get(1)].add(conn.get(0));
		}

		int[] timestamp = new int[n];

		dfs(n, graph, timestamp, 0, -1, criticalConns);

		return criticalConns;
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
