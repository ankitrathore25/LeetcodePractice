package find_k_closest_elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MySolution {

	public static List<Integer> findClosestElements1(int[] arr, int k, int x) {
		/*
		 * Time Complexity: O(nlogn)
		 */

		PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				int bDiff = Math.abs(b - x);
				int aDiff = Math.abs(a - x);
				if (aDiff == bDiff)
					return -1;
				return Integer.compare(bDiff, aDiff);
			}
		});

		for (int i = 0; i < arr.length; i++) {
			pq.add(arr[i]);
			if (pq.size() > k)
				pq.poll();
		}

		List<Integer> result = new ArrayList<>();
		while (!pq.isEmpty()) {
			result.add(pq.poll());
		}
		Collections.sort(result);
		return result;
	}

	public static void main(String[] args) {
//		List<Integer> arrList = Arrays.asList(0,1,1,1,2,3,6,7,8,9,11,13,15,16,17,19,20,22,24,26,27,29,30,34,36,37,38);
//		List<Integer> arrList = Arrays.asList(0,0,0,1,3,5,6,7,8,8);
		List<Integer> arrList = Arrays.asList(1, 1, 1, 10, 10, 10);
		int[] arr = new int[arrList.size()];
		for (int i = 0; i < arrList.size(); i++)
			arr[i] = arrList.get(i);

		int k = 1;
		int x = 9;
		List<Integer> ans1 = findClosestElements1(arr, k, x);
		for (int i = 0; i < ans1.size(); i++) {
			System.out.print(ans1.get(i) + ",");
		}
	}

}
